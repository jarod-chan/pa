package cn.fyg.pa.domain.help.rptComparator;

import java.util.Comparator;

import cn.fyg.pa.application.page.Point;

public class PointAscComparator<T> implements Comparator<T> {

	public int compare(T o1, T o2) {
		Point p1=(Point)o1;
		Point p2=(Point)o2;
		return p1.getResult().compareTo(p2.getResult());
	}


}
