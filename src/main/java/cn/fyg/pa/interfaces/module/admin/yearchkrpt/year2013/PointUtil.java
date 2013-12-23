package cn.fyg.pa.interfaces.module.admin.yearchkrpt.year2013;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import cn.fyg.pa.infrastructure.persistence.jpa.RptJpa;
import cn.fyg.pa.interfaces.module.admin.yearchkrpt.common.AbstractPointUtil;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

public class PointUtil extends AbstractPointUtil<Point> {
	
	@Override
	public void initOriginalData(RptJpa rptJpa,Long year) {
		
		//部门领导评价
		List<Object[]> personInfo_deptScore=rptJpa.getCheckPoint(year);
		for (int i = 0; i < personInfo_deptScore.size(); i++) {
			Object[] arr=personInfo_deptScore.get(i);
			Point point=new Point().personId(((Integer)arr[0]).longValue())
					.personName(arr[1].toString())
					.personDept(arr[2].toString())
					.scheck((BigDecimal)arr[3]);
			this.points().add(point);
		}
		
		//员工互相评价
		List<Object[]> personScore=rptJpa.getVal(year);
		Map<Long, BigDecimal> temp = getPersonValueMap(personScore);
		for(Point point:this.points()){
			BigDecimal val2 = temp.get(point.getPersonId());
			point.val(val2==null?new BigDecimal("0"):val2);
		}
		
		//参与度
		BigDecimal participationAvg = rptJpa.getParticipationAvg(year);
		List<Object[]> personParticipation = rptJpa.getParticipationAbs(year);
		temp=getPersonValueMap(personParticipation);
		for(Point point:this.points()){
			BigDecimal val2 = temp.get(point.getPersonId());
			point.getPtn().setIabs(val2==null?new BigDecimal("0"):val2);
		}
		
		for(Point point:this.points()){
			point.getPtn().setIavg(participationAvg);
		}
	}

	private Map<Long, BigDecimal> getPersonValueMap(List<Object[]> personValueList) {
		Map<Long,BigDecimal> temp=new HashMap<Long,BigDecimal>();
		for (int i = 0; i < personValueList.size(); i++) {
			Object[] arr=personValueList.get(i);
			temp.put(((Integer)arr[0]).longValue(), (BigDecimal)arr[1]);
		}
		return temp;
	}
		
	public void calculate(){
		calculateMdep();
		calculateMall();
		calculateDamp();
		calculateMamp();
		calculateS();
		calculateSavg();
		calculatU();
		calculatVavg();
		calculatParticipation();
		calculatResult();
		
		this.setHasCalculate();
	}

	/**
	 * 计算参与度值
	 */
	private void calculatParticipation() {
		for (Point point:this.points()) {
			point.getPtn().calculat();
		}
	}

	/**
	 * 计算个人评价平均值
	 */
	private void calculatVavg() {
		BigDecimal total=new BigDecimal("0");
		for (Point point:this.points()) {
			total=total.add(point.getVal());
		}
		BigDecimal vavg=total.divide(new BigDecimal(this.points().size()),Constant.SCALE,Constant.ROUND_MODEL);
		for(Point point:this.points()){
			point.getPtn().setVavg(vavg);
		}
		
	}

	private void calculateSavg() {
		BigDecimal Stotal = Constant.ZERO;
		int count=0;
		for(Point point:this.points()){
			Stotal=Stotal.add(point.getS());
			count++;
		}
		BigDecimal Savg=Stotal.divide(new BigDecimal(count), Constant.ROUND_MODEL);
		for (Point  point : this.points()) {
			point.savg(Savg);
		}
	}

	private void calculatResult() {
		for(Point point:this.points()){
			point.calculatResult();
		}
	}

	private void calculatU() {
		for(Point point:this.points()){
			point.calculatUpsilon();
		}
	}


	//计算s值
	private void calculateS() {
		for (Point point : this.points()) {
			point.calculatS();
		}
		BigDecimal Smean = Constant.ZERO;
		int count = 0;
		for (Point point : this.points()) {
			if(point.getS()!=null){
				Smean=Smean.add(point.getS());
				count++;
			}
		}
		Smean=Smean.divide(new BigDecimal(count),Constant.SCALE,Constant.ROUND_MODEL);
		for(Point point : this.points()){
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
		for(Point point:this.points()){
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
		for(Point point:this.points()){
			point.damp(depAmp.get(point.getPersonDept()));
		}
	}
	
	//计算所有部门的考核幅度
	private Map<String, BigDecimal> calculateEveryDepAmp() {
		Map<String,BigDecimal> depMax=new HashMap<String,BigDecimal>();
		Map<String,BigDecimal> depMin=new HashMap<String,BigDecimal>();
		for(Point point:this.points()){
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
		for (Point point : this.points()) {
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
		for(Point point:this.points()){
			point.mdep(depMean.get(point.getPersonDept()));
		}
	}

	//总体考核平均值
	private void calculateMall(){
		BigDecimal total=new BigDecimal("0");
		for (Point point:this.points()) {
			total=total.add(point.getScheck());
		}
		BigDecimal mall=total.divide(new BigDecimal(this.points().size()),Constant.SCALE,Constant.ROUND_MODEL);
		for(Point point:this.points()){
			point.mall(mall);
		}
	}



}
