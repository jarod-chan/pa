package cn.fyg.pa.domain.model.companykpi;

public class IdrYearCompanyFactory {
	
	public static IdrYearCompany createIdrYearCompany(Long year){
		IdrYearCompany idrYearCompany=new IdrYearCompany();
		idrYearCompany.setYear(year);
		return idrYearCompany;
	}

}
