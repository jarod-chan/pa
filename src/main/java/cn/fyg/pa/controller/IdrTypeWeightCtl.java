package cn.fyg.pa.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.message.imp.SessionMPR;
import cn.fyg.pa.model.IdrYear;
import cn.fyg.pa.model.Result;
import cn.fyg.pa.service.IdrTypeService;
import cn.fyg.pa.service.IdrYearService;

@Controller
@RequestMapping("/admin/idrtypeweight")
public class IdrTypeWeightCtl {
	
	@Resource
	IdrYearService idrYearService;
	
	@Resource
	IdrTypeService idrTypeService;
	
	
	@RequestMapping(value="/edit/{year}",method=RequestMethod.GET)
	public String toEdit(@PathVariable("year") Long year,Map<String,Object> map,HttpSession session){
		IdrYear idrYear=idrYearService.findByYear(year);
		map.put("idrTypes", idrTypeService.findAll());
		map.put("idrYear", idrYear);
		map.put("message", new SessionMPR(session).getMessage());
		return "typeweight/edit";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(IdrYear idrYearForm,Map<String,Object> map,HttpSession session){
		idrYearService.save(idrYearForm);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:edit/"+idrYearForm.getYear();
	}
	
	@RequestMapping(value="/commit",method=RequestMethod.POST)
	public String commit(IdrYear idrYearForm,Map<String,Object> map,HttpSession session){
		IdrYear idryear=idrYearService.save(idrYearForm);
		Result result=idryear.isTypeWeightRight();
		if(result.pass()){
			new SessionMPR(session).setMessage("提交通过!");
		}else{
			new SessionMPR(session).setMessage("提交未通过,"+result.cause()+"!");
		}
		return "redirect:edit/"+idrYearForm.getYear();
	}

}
