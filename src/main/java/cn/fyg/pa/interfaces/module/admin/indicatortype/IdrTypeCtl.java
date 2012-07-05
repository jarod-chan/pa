package cn.fyg.pa.interfaces.module.admin.indicatortype;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.IdrTypeService;
import cn.fyg.pa.domain.model.indicatortype.IdrType;
import cn.fyg.pa.domain.model.indicatortype.IdrTypeRepository;
import cn.fyg.pa.interfaces.module.shared.message.MessagePasser;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;

@Controller
@RequestMapping("/admin/idrtype")
public class IdrTypeCtl {
	
	@Resource
	MessagePasser messagePasser;
	@Resource
	IdrTypeRepository idrTypeRepository;
	@Resource
	IdrTypeService idrTypeService;
	
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String list(Map<String,Object> map){

		List<IdrType> idrTypes=idrTypeRepository.findAll();
		map.put("idrTypes", idrTypes);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
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
		IdrType idrType=idrTypeRepository.find(idrTypeId);
		map.put("idrType", idrType);
		return "idrtype/edit";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(IdrType idrType,Map<String,Object> map){
		idrTypeService.save(idrType);
		messagePasser.setMessage("保存成功！");
		return "redirect:/admin/idrtype";
	}
	
	@RequestMapping(value="/delete/{Id}",method=RequestMethod.POST)
	public String delete(@PathVariable(value="Id")Long idrTypeId){
		idrTypeService.remove(idrTypeId);
		messagePasser.setMessage("删除成功！");
		return "redirect:/admin/idrtype";
	}
}
