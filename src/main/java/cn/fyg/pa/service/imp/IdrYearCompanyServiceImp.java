package cn.fyg.pa.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import cn.fyg.pa.dao.IdrYearCompanyDao;
import cn.fyg.pa.model.IdrYearCompany;
import cn.fyg.pa.service.IdrYearCompanyService;

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

}
