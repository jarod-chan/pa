package cn.fyg.pa.domain.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.fyg.pa.domain.companykpi.IdrYearCompany;
import cn.fyg.pa.domain.service.IdrYearCompanyService;
import cn.fyg.pa.infrastructure.perisistence.IdrYearCompanyDao;

@Service
public class IdrYearCompanyServiceImp implements IdrYearCompanyService {
	
	@Resource
	IdrYearCompanyDao idrYearCompanyDao;
	
	@Override
	@Transactional
	public IdrYearCompany findByYear(Long year) {
		IdrYearCompany idrYear=idrYearCompanyDao.find(year);
		if(idrYear==null){
			idrYear=initIdrYear(year);
		}
		return idrYear;
	}

	private IdrYearCompany initIdrYear(Long year) {
		IdrYearCompany idrYear=new IdrYearCompany();
		idrYear.setYear(year);
		return idrYear;
	}

	@Override
	@Transactional
	public IdrYearCompany save(IdrYearCompany idrYearCompany) {
		return idrYearCompanyDao.save(idrYearCompany);
	}
	
	@Override
	@Transactional
	public IdrYearCompany sortIdrCompanyByIdrTypeWeight(IdrYearCompany idrYearCompany){
		idrYearCompany=idrYearCompanyDao.save(idrYearCompany);
		idrYearCompany.sortIdrCompanyByIdrTypeWeight();
		return idrYearCompany;
	}

}
