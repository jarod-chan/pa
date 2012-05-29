package cn.fyg.pa.domain.model.companykpi;

public interface IdrYearCompanyRepository {
	
	IdrYearCompany find(Long id);

	IdrYearCompany save(IdrYearCompany idrYearCompany);


}
