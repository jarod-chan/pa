package cn.fyg.pa.interfaces.web.shared.tool;

public class Urlpath {
	
	private String url;
	
	public Urlpath(String url){
		this.url=url;
	}
	
	public String redirect(){
		return "redirect:"+this.url;
	}
	
	public String forward(){
		return "forward:"+this.url;
	}

}
