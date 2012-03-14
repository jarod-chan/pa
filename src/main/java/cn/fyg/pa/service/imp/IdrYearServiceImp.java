package cn.fyg.pa.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.dao.IdrYearDao;
import cn.fyg.pa.model.IdrYear;
import cn.fyg.pa.service.IdrYearService;

@Service
public class IdrYearServiceImp implements IdrYearService {
	
	@Resource
	IdrYearDao idrYearDao;
	
	@Override
	public IdrYear findByYear(Long year) {
		IdrYear idrYear=idrYearDao.find(year);
		if(idrYear==null){
			idrYear=initIdrYear(year);
		}
		return idrYear;
	}

	private IdrYear initIdrYear(Long year) {
		IdrYear idrYear=new IdrYear();
		idrYear.setYear(year);
		return idrYear;
	}

	@Override
	@Transactional
	public IdrYear save(IdrYear idrYear) {
		return idrYearDao.save(idrYear);
	}

}
