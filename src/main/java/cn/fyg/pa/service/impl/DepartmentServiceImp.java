package cn.fyg.pa.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.dao.DepartmentDao;
import cn.fyg.pa.model.Department;
import cn.fyg.pa.service.DepartmentService;

@Service
public class DepartmentServiceImp implements DepartmentService {

	@Resource
	DepartmentDao departmentDao;
	
	@Override
	public Department findByName(String name) {
		return departmentDao.findByName(name);
	}

}
