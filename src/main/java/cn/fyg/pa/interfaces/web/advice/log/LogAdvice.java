package cn.fyg.pa.interfaces.web.advice.log;

import org.aspectj.lang.JoinPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogAdvice  {
    
    public void before(JoinPoint jp){
    	Logger logger = LoggerFactory.getLogger(jp.getTarget().getClass());
    	logger.info(jp.getSignature().getName()); 
    }
    
}