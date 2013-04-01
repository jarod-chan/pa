package cn.fyg.pa.application.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.UsershipService;
import cn.fyg.pa.domain.model.usership.Usership;
import cn.fyg.pa.domain.model.usership.UsershipRepository;

@Service("usershipService")
public class UsershipServiceImpl implements UsershipService {
	
	@Resource
	UsershipRepository usershipRepository;

	@Override
	public String parent(String tree, String userKey) {
		Usership usership = usershipRepository.find(tree, userKey);
		return usership==null? null:usership.getParentKey();
	}

	@Override
	public String userTag(String tree, String userKey) {
		Usership usership = usershipRepository.find(tree, userKey);
		return usership.getTag();
	}

	@Override
	@Transactional
	public Usership save(Usership usership) {
		return  usershipRepository.save(usership);
	}

}
