package cn.fyg.pa.interfaces.module.shared.personin.bean;

import javax.annotation.Resource;

import org.aspectj.lang.ProceedingJoinPoint;
import org.springframework.stereotype.Component;

import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.interfaces.module.shared.personin.annotation.PersonIn;
import cn.fyg.pa.interfaces.module.shared.session.SessionUtil;
import cn.fyg.pa.interfaces.module.system.login.LoginRetBean;

@Component
public class PersonInAdvice {
	
	@Resource
	PersonRepository personRepository;
	@Resource
	SessionUtil sessionUtil;
	
	public Object injectPerson(ProceedingJoinPoint pjp,PersonIn personIn) throws Throwable{
		Object[] args = pjp.getArgs();
		if(personIn.value()>=0){
			LoginRetBean loginRetBean=sessionUtil.getValue("loginRet");
			Person person = this.personRepository.find(Long.valueOf(loginRetBean.getPersonid()));
			args[personIn.value()]=person;
		}
		return pjp.proceed(args);
	}

}
