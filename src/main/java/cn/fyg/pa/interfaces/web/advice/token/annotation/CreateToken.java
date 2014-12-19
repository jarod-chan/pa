package cn.fyg.pa.interfaces.web.advice.token.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *注解controller的方法，表示产生一个token 
 *用于防止重复提交
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface CreateToken {
	
	/**
	 * map 参数的参数位置  用于向界面注入token
	 * 采用spring aop的时候 ，无法用配置的方式取得controller类里map参数
	 * 所以在注解里指定
	 * 可能可以通过参数名在xml里直接提取map参数，有待进一步验证
	 * @return
	 */
	int mapIndex() default -1;

}
