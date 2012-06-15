package cn.fyg.pa.interfaces.module.admin.indicatortype;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.IdrTypeService;
import cn.fyg.pa.domain.model.indicatortype.IdrType;
import cn.fyg.pa.domain.model.indicatortype.IdrTypeRepository;
import cn.fyg.pa.interfaces.module.shared.message.impl.SessionMPR;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

@Controller
@RequestMapping("/admin/idrtype")
public class IdrTypeCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(IdrTypeCtl.class);
	
	@Resource
	IdrTypeRepository idrTypeRepository;
	@Resource
	IdrTypeService idrTypeService;
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String list(Map<String,Object> map,HttpSession session){
		logger.info("list");
		List<IdrType> idrTypes=idrTypeRepository.findAll();
		map.put("idrTypes", idrTypes);
		map.put(Constant.MESSAGE_NAME, new SessionMPR(session).getMessage());
		return "idrtype/list";
	}
	
	@RequestMapping(value="/add",method=RequestMethod.GET)
	public String toAdd(Map<String,Object> map){
		logger.info("toAdd");
		IdrType idrType=new IdrType();
		map.put("idrType", idrType);
		return "idrtype/edit";
	}
	
	@RequestMapping(value="/edit/{Id}",method=RequestMethod.GET)
	public String toEdit(@PathVariable(value="Id")Long idrTypeId,Map<String,Object> map){
		logger.info("toEdit");
		IdrType idrType=idrTypeRepository.find(idrTypeId);
		map.put("idrType", idrType);
		return "idrtype/edit";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(IdrType idrType,Map<String,Object> map,HttpSession session){
		logger.info("save");
		idrTypeService.save(idrType);
		new SessionMPR(session).setMessage("保存成功！");
		return "redirect:/admin/idrtype";
	}
	
	@RequestMapping(value="/delete/{Id}",method=RequestMethod.POST)
	public String delete(@PathVariable(value="Id")Long idrTypeId,HttpSession session){
		logger.info("delete");
		idrTypeService.remove(idrTypeId);
		new SessionMPR(session).setMessage("删除成功！");
		return "redirect:/admin/idrtype";
	}
}
