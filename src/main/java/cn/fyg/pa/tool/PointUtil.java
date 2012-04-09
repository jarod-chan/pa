package cn.fyg.pa.tool;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.application.page.Point;
import cn.fyg.pa.domain.help.rptComparator.PointDescComparator;

public class PointUtil {
	
	private List<Point> rptList;
	private boolean hasCalculate=false;
	
	public PointUtil(List<Object[]> checkPoint,List<Object[]> val){
		rptList=new ArrayList<Point>(checkPoint.size());
		for (int i = 0; i < checkPoint.size(); i++) {
			Object[] arr=checkPoint.get(i);
			Point point=new Point().personId(((Integer)arr[0]).longValue())
					.personName(arr[1].toString())
					.personDept(arr[2].toString())
					.scheck((BigDecimal)arr[3]);
			rptList.add(point);
		}
		Map<Long,BigDecimal> temp=new HashMap<Long,BigDecimal>();
		for (int i = 0; i < val.size(); i++) {
			Object[] arr=val.get(i);
			temp.put(((Integer)arr[0]).longValue(), (BigDecimal)arr[1]);
		}
		for(Point point:rptList){
			point.val(temp.get(point.getPersonId()));
		}
	}
	
	public void calculate(){
		calculateMdep();
		calculateMall();
		calculateDamp();
		calculateMamp();
		calculateS();
		calculateSmaxAndSmainAndSamp();
		calculatU();
		calculatResult();
		hasCalculate=true;
	}

	private void calculatResult() {
		for(Point point:rptList){
			point.calculatResult();
		}
	}

	private void calculatU() {
		for(Point point:rptList){
			point.calculatUpsilon();
		}
	}

	private void calculateSmaxAndSmainAndSamp() {
		BigDecimal Smax=rptList.get(0).getS();
		BigDecimal Smin=rptList.get(0).getS();
		BigDecimal Samp=Constant.ZERO;
		for(Point point : rptList){
			BigDecimal s = point.getS();
			Smax = s.compareTo(Smax) > 0 ? s : Smax;
			Smin = s.compareTo(Smin) < 0 ? s : Smin;
		}
		Samp = Smax.subtract(Smin);
		for(Point point:rptList){
			point.samp(Samp).maxs(Smax).mins(Smin);
		}
	}

	//计算s值
	private void calculateS() {
		for (Point point : rptList) {
			point.calculatS();
		}
		BigDecimal Smean = Constant.ZERO;
		int count = 0;
		for (Point point : rptList) {
			if(point.getS()!=null){
				Smean=Smean.add(point.getS());
				count++;
			}
		}
		Smean=Smean.divide(new BigDecimal(count),Constant.SCALE,Constant.ROUND_MODEL);
		for(Point point : rptList){
			if(point.getS()==null){
				point.s(Smean);
			}
		}
	}

	//考核幅度平均值
	private void calculateMamp() {
		Map<String, BigDecimal> depAmp = calculateEveryDepAmp();
		filterZero(depAmp);
		BigDecimal total=new BigDecimal("0");
		int count=0;
		for(Map.Entry<String, BigDecimal> entry:depAmp.entrySet()){
			total=total.add(entry.getValue());
			count++;
		}
		BigDecimal mamp=total.divide(new BigDecimal(count),Constant.SCALE,Constant.ROUND_MODEL);
		for(Point point:rptList){
			point.mamp(mamp);
		}
	}

	//过滤是0的键，不参与计算
	private void filterZero(Map<String, BigDecimal> depAmp) {
		Iterator<String> it=depAmp.keySet().iterator();
		while(it.hasNext()){
			String key=it.next();
			if(depAmp.get(key).compareTo(Constant.ZERO)==0){
				it.remove();
			}
		}
	}

	//计算部门考核幅度，如果部门只有一个人，那么考核幅度就为0
	private void calculateDamp() {
		Map<String, BigDecimal> depAmp = calculateEveryDepAmp();
		for(Point point:rptList){
			point.damp(depAmp.get(point.getPersonDept()));
		}
	}
	
	//计算所有部门的考核幅度
	private Map<String, BigDecimal> calculateEveryDepAmp() {
		Map<String,BigDecimal> depMax=new HashMap<String,BigDecimal>();
		Map<String,BigDecimal> depMin=new HashMap<String,BigDecimal>();
		for(Point point:rptList){
			String personDept=point.getPersonDept();
			BigDecimal scheck = point.getScheck();
			updateDepMax(depMax, personDept, scheck);
			updateDepMin(depMin, personDept, scheck);
		}
		for(String key:depMax.keySet()){
			depMax.put(key, depMax.get(key).subtract(depMin.get(key)));
		}
		return depMax;
	}

	//获得部门最小值
	private void updateDepMin(Map<String, BigDecimal> depMin,
			String personDept, BigDecimal scheck) {
		if(depMin.containsKey(personDept)){
			if(scheck.compareTo(depMin.get(personDept))<0){
				depMin.put(personDept, scheck);
				return;
			}
			return;
		}
		depMin.put(personDept, scheck);
	}
	
	//获得部门最大值
	private void updateDepMax(Map<String, BigDecimal> depMax,
			String personDept, BigDecimal scheck) {
		if(depMax.containsKey(personDept)){
			if(scheck.compareTo(depMax.get(personDept))>0){
				depMax.put(personDept, scheck);
				return;
			}
			return;
		}
		depMax.put(personDept, scheck);
	}

	//部门考核平均值
	private void calculateMdep() {
		Map<String,BigDecimal> depMean=new HashMap<String,BigDecimal>();
		Map<String,Integer> depCount=new HashMap<String,Integer>();
		for (Point point : rptList) {
			String personDept=point.getPersonDept();
			if(depMean.containsKey(personDept)){
				depMean.put(personDept, depMean.get(personDept).add(point.getScheck()));
				depCount.put(personDept, depCount.get(personDept)+1);
			}else{
				depMean.put(personDept, point.getScheck());
				depCount.put(personDept, new Integer(1));
			}
		}
		for(String key:depMean.keySet()){
			BigDecimal count=new BigDecimal(depCount.get(key));
			depMean.put(key,depMean.get(key).divide(count,Constant.SCALE,Constant.ROUND_MODEL));
		}
		for(Point point:rptList){
			point.mdep(depMean.get(point.getPersonDept()));
		}
	}

	//总体考核平均值
	private void calculateMall(){
		BigDecimal total=new BigDecimal("0");
		for (Point point:rptList) {
			total=total.add(point.getScheck());
		}
		BigDecimal mall=total.divide(new BigDecimal(rptList.size()),Constant.SCALE,Constant.ROUND_MODEL);
		for(Point point:rptList){
			point.mall(mall);
		}
	}
	
	public List<Point> getResult() throws Exception{
		if(!hasCalculate) throw new Exception("point dont  calculate!");
		return rptList;
	}
	
	public void orderByPoint(String order) throws Exception{
		if(!hasCalculate) throw new Exception("point dont  calculate!");
		Collections.sort(rptList, new PointDescComparator<Point>());
		int i=1;
		for (Point point : rptList) {
			point.ranking(i++);
		}
		if(order.equals("asc")){
			Collections.reverse(rptList);
		}
	}

}
