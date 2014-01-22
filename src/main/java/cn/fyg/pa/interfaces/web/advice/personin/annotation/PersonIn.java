package cn.fyg.pa.interfaces.web.advice.personin.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *注入session中的当前用户
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface PersonIn {
	
	/**
	 * 注入的person参数位置
	 * @return
	 */
	int value() default -1;

}
