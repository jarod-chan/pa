package cn.fyg.pa.security;   
  
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import cn.fyg.pa.page.LoginRet;
import cn.fyg.pa.tool.SessionUtil;

/**  TODO 这里有重复逻辑
 * 用户访问权限的过滤器  
 * @author viano  
 */  
public class UrlFilter implements Filter {   
	
	private static final Logger logger = LoggerFactory.getLogger(UrlFilter.class);
	
	/**
	 * 非过滤url
	 */
	private static final List<String> noFilterUrl=Arrays.asList("/pa/","/pa/login","/pa/fetchcsr","/pa/fail","/pa/dispatcher");
	
	/**
	 * 资源url
	 */
	private static final List<String> resFilterUrl=Arrays.asList("/pa/resources");
	
	/**
	 * 职员url
	 */
	private static final List<String> personUrl=Arrays.asList("/pa/person");
	
	/**
	 * 经理url
	 */
	private static final List<String> mangeUrl=Arrays.asList("/pa/mange");
	
	/**
	 * 管理员url
	 */
	private static final List<String> adminUrl=Arrays.asList("/pa/admin");

  
    public void destroy() {   
    }   
  
    public void init(FilterConfig filterConfig) throws ServletException {   
    }
    
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 设置请求的字符编码     
        request.setCharacterEncoding("UTF-8");     
        // 设置返回请求的字符编码     
        response.setCharacterEncoding("UTF-8");     
        // 转换ServletRequest为 HttpServletRequest     
        HttpServletRequest req = (HttpServletRequest) request;     
        // 转换ServletResponse为HttpServletRequest     
        HttpServletResponse res = (HttpServletResponse) response;     
        
        // 获取当前请求的URI     
        String url = req.getRequestURI(); 
        String method=req.getMethod();
        logger.info(method+":"+url);
            
        
        if(isNofilterUrl(url)){
        	chain.doFilter(request, response);
        	return;
        }
        if(isIndexOf(url,resFilterUrl)){
        	chain.doFilter(request, response);
        	return;
        }
        SessionUtil session=new SessionUtil(req);
		LoginRet loginRet = session.getValue("loginRet", LoginRet.class);
		
		if(loginRet!=null){			
			if (isIndexOf(url, personUrl) && loginRet.getMange().equals("N")) {
				chain.doFilter(request, response);
				return;
			}
			if (isIndexOf(url, mangeUrl) && loginRet.getMange().equals("Y")) {
				chain.doFilter(request, response);
				return;
			}
			if (isIndexOf(url, adminUrl) && loginRet.getMange().equals("A")) {
				chain.doFilter(request, response);
				return;
			}
		}
		
		res.sendRedirect("/pa/fail");
	}

	private boolean isIndexOf(String url, List<String> targetUrl) {
		for (String urlPattern : targetUrl) {
			if(url.indexOf(urlPattern)>=0){
				return true;
			}
		}
		return false;
	}
	
	private boolean isNofilterUrl(String url) {
		for (String urlPattern : noFilterUrl) {
			if(url.equals(urlPattern)){
				return true;
			}
		}
		return false;
	}

  
}  