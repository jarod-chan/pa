package cn.fyg.pa.domain.model.yeartypeweight;

public class IdrYearTypeWeightFactory {
	
	public static IdrYearTypeWeight createIdrYearTypeWeight(Long year){
		IdrYearTypeWeight idrYearTypeWeight=new IdrYearTypeWeight();
		idrYearTypeWeight.setYear(year);
		return idrYearTypeWeight;
	}

}
