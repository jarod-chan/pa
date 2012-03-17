package cn.fyg.pa.service.imp;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.dao.IdrYearTypeWeightDao;
import cn.fyg.pa.model.IdrYearTypeWeight;
import cn.fyg.pa.service.IdrYearTypeWeightService;

@Service
public class IdrYearTypeWeightServiceImp implements IdrYearTypeWeightService {

	@Resource
	IdrYearTypeWeightDao idrYearTypeWeightDao;
	
	@Override
	public IdrYearTypeWeight findByYear(Long year) {
		IdrYearTypeWeight idrYearTypeWeight=idrYearTypeWeightDao.find(year);
		if(idrYearTypeWeight==null){
			idrYearTypeWeight=initIdrYearTypeWeight(year);
		}
		return idrYearTypeWeight;
	}

	private IdrYearTypeWeight initIdrYearTypeWeight(Long year) {
		IdrYearTypeWeight idrYearTypeWeight=new IdrYearTypeWeight();
		idrYearTypeWeight.setYear(year);
		return idrYearTypeWeight;
	}

	@Override
	@Transactional
	public IdrYearTypeWeight save(IdrYearTypeWeight idrYearTypeWeight) {
		return idrYearTypeWeightDao.save(idrYearTypeWeight);
	}

}
