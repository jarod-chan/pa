package cn.fyg.pa.interfaces.web.module.system.index;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.fyg.pa.domain.model.summary.Content;

@Controller
@RequestMapping("")
public class IndexCtl {
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String welcome() {
		return "redirect:first";
	}
	
	@RequestMapping(value = "first")
	public String first() {
		return "first";
	}
		
	@RequestMapping(value = "fail", method = RequestMethod.GET)
	public String fail() {
		return "fail";
	}
	
	@RequestMapping(value="hold",method=RequestMethod.GET)
	@ResponseBody
	public String hold(Content content,HttpSession session){
		return "success";
	}

}
