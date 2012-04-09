package cn.fyg.pa.application.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.domain.model.IdrType;
import cn.fyg.pa.domain.service.IdrTypeService;
import cn.fyg.pa.infrastructure.message.imp.SessionMPR;
import cn.fyg.pa.tool.Constant;

@Controller
@RequestMapping("/admin/idrtype")
public class IdrTypeCtl {
	
	@Resource
	IdrTypeService idrTypeService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String index(Map<String,Object> map,HttpSession session){
		List<IdrType> idrTypes=idrTypeService.findAll();
		map.put("idrTypes", idrTypes);
		map.put(Constant.MESSAGE_NAME, new SessionMPR(session).getMessage());
		return "idrtype/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String toAdd(Map<String,Object> map){
		IdrType idrType=new IdrType();
		map.put("idrType", idrType);
		return "idrtype/edit";
	}
	
	@RequestMapping(value="/edit/{Id}",method=RequestMethod.GET)
	public String toEdit(@PathVariable(value="Id")Long idrTypeId,Map<String,Object> map){
		IdrType idrType=idrTypeService.find(idrTypeId);
		map.put("idrType", idrType);
		return "idrtype/edit";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(IdrType idrType,Map<String,Object> map,HttpSession session){
		idrTypeService.save(idrType);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:/admin/idrtype";
	}
	
	@RequestMapping(value="/delete/{Id}",method=RequestMethod.POST)
	public String delete(@PathVariable(value="Id")Long idrTypeId,HttpSession session){
		idrTypeService.remove(idrTypeId);
		new SessionMPR(session).setMessage("删除成功！");
		return "redirect:/admin/idrtype";
	}
}
