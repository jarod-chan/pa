package cn.fyg.pa.domain.help.rptComparator;

import java.util.Comparator;

import cn.fyg.pa.interfaces.page.Point;

public class PointDescComparator<T> implements Comparator<T> {

	public int compare(T o1, T o2) {
		Point p1=(Point)o1;
		Point p2=(Point)o2;
		return p2.getResult().compareTo(p1.getResult());
	}


}
