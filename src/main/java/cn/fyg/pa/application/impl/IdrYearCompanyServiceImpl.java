package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.IdrYearCompanyService;
import cn.fyg.pa.domain.model.companykpi.IdrYearCompany;
import cn.fyg.pa.domain.model.companykpi.IdrYearCompanyFactory;
import cn.fyg.pa.domain.model.companykpi.IdrYearCompanyRepository;

@Service
public class IdrYearCompanyServiceImpl implements IdrYearCompanyService {
	
	@Resource
	IdrYearCompanyRepository idrYearCompanyRepository;
	
	@Override
	@Transactional
	public IdrYearCompany findByYear(Long year) {
		IdrYearCompany idrYear=idrYearCompanyRepository.find(year);
		if(idrYear==null){
			return IdrYearCompanyFactory.createIdrYearCompany(year);
		}
		return idrYear;
	}


	@Override
	@Transactional
	public IdrYearCompany save(IdrYearCompany idrYearCompany) {
		return idrYearCompanyRepository.save(idrYearCompany);
	}
	
	@Override
	@Transactional
	public IdrYearCompany sortIdrCompanyByIdrTypeWeight(IdrYearCompany idrYearCompany){
		idrYearCompany=idrYearCompanyRepository.save(idrYearCompany);
		idrYearCompany.sortIdrCompanyByIdrTypeWeight();
		return idrYearCompany;
	}

}
