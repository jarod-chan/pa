package cn.fyg.pa.controller;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.message.imp.SessionMPR;
import cn.fyg.pa.model.IdrYearCompany;
import cn.fyg.pa.model.IdrYearTypeWeight;
import cn.fyg.pa.service.IdrYearCompanyService;
import cn.fyg.pa.service.IdrYearTypeWeightService;
import cn.fyg.pa.tool.JsonUtil;

@Controller
@RequestMapping("/admin/idrcompany")
public class IdrCompanyCtl {

	@Resource
	IdrYearCompanyService idrYearCompanyService;
	
	@Resource
	IdrYearTypeWeightService idrYearTypeWeightService;
	
	@RequestMapping(value="edit/{year}",method=RequestMethod.GET)
	public String toEdit(@PathVariable("year")Long year,Map<String,Object> map,HttpSession session){
		IdrYearCompany idrYearCompany=idrYearCompanyService.findByYear(year);
		IdrYearTypeWeight idrYearTypeWeight=idrYearTypeWeightService.findByYear(year);
		map.put("idrYearCompany", idrYearCompany);
		map.put("idrYearTypeWeight", idrYearTypeWeight);
		map.put("idrTypeWeightsJson", JsonUtil.toArrayStr(idrYearTypeWeight.getIdrTypeWeight(),new String[]{"idrYearTypeWeight"}));
		map.put("message", new SessionMPR(session).getMessage());
		return "idrcompany/edit";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String edit(IdrYearCompany idrYearCompanyForm,HttpSession session){
		idrYearCompanyForm=idrYearCompanyService.save(idrYearCompanyForm);
		 new SessionMPR(session).setMessage("保存成功！");
		return "redirect:edit/"+idrYearCompanyForm.getYear();
	}
}
