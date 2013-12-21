package cn.fyg.pa.interfaces.module.admin.yearchkrpt.common;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import cn.fyg.pa.interfaces.module.admin.yearchkrpt.PointHelp;
import cn.fyg.pa.interfaces.module.admin.yearchkrpt.PointItem;


public abstract class AbstractPointUtil<T extends PointItem> implements PointHelp<T> {
	
	private List<T> points=new ArrayList<T>();
	private boolean hasCalculate=false;
	
	public List<T> points() {
		return points;
	}
	public void setHasCalculate() {
		this.hasCalculate = true;
	}

	public List<T> getResult() throws Exception{
		if(!hasCalculate) throw new Exception("point dont  calculate!");
		return this.points;
	}
	
	public void orderByPoint(String order) throws Exception{
		if(!hasCalculate) throw new Exception("point dont  calculate!");
		Collections.sort(points, new PointDescComparator());
		int i=1;
		for (PointItem point : points) {
			point.setRanking(i++);
		}
		if(order.equals("desc")){
			Collections.reverse(points);
		}
	}
	
	private class PointDescComparator implements Comparator<PointItem> {
		@Override
		public int compare(PointItem p1, PointItem p2) {
			return p2.getResult().compareTo(p1.getResult());
		}
	}

}
