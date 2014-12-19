package cn.fyg.pa.interfaces.web.security;   
  
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


/**
 *过滤session超时用户发送的请求
 */
public class UrlFilter implements Filter {   
	
	private static final Logger logger = LoggerFactory.getLogger(UrlFilter.class);
	
	/**
	 * 资源url
	 */
	private static final List<String> resFilterUrl=Arrays.asList("/pa/resources");
	
	/**
	 * 非过滤url
	 */
	private static final List<String> noFilterUrl=Arrays.asList("/pa/","/pa/first","/pa/fetchcsr","/pa/fail","/pa/login","/pa/hold");
	
  
    public void destroy() {   
    	logger.info("destroy");
    }   
  
    public void init(FilterConfig filterConfig) throws ServletException {   
    }
    
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		// 设置请求的字符编码,返回请求的字符编码  
        request.setCharacterEncoding("UTF-8");     
        response.setCharacterEncoding("UTF-8");     
       
        // 转换ServletRequest为 HttpServletRequest     
        HttpServletRequest req = (HttpServletRequest) request;     
        
        // 获取当前请求的URI     
        String url = req.getRequestURI(); 
        
        if(isIndexOf(url,resFilterUrl)){
        	chain.doFilter(request, response);
        	return;
        }
 
        if(isNofilterUrl(url)){
        	chain.doFilter(request, response);
        	return;
        }

        Object loginRetObj=req.getSession().getAttribute("loginRet");        
		
		if(loginRetObj!=null){	
			chain.doFilter(request, response);
			return;
		}
		 // 转换ServletResponse为HttpServletRequest     
        HttpServletResponse res = (HttpServletResponse) response;
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
