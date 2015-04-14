package cn.fyg.pa.interfaces.web.module.suggestion;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import cn.fyg.pa.application.SuggestionService;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.suggestion.Suggestion;
import cn.fyg.pa.interfaces.web.advice.personin.annotation.PersonIn;
import cn.fyg.pa.interfaces.web.shared.tool.Constant;

@Controller
@RequestMapping("/suggestion")
public class SuggestionCtl {
	
	private static final String PATH="suggestion/";	
	private interface Page {
		String SUGGESTION = PATH + "suggestion";
	}
	
	@Autowired
	SuggestionService suggestionService;
	
	@RequestMapping(value="",method=RequestMethod.GET)
	@PersonIn(0)
	public String toEdit(Person person,Map<String,Object> map){
		Long personId=person.getId();
		List<Suggestion> suggestionList = this.suggestionService.findByPersonId(personId);
		map.put("suggestionList", suggestionList);
		return Page.SUGGESTION;
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	@PersonIn(0)
	public String save(Person person,String content,RedirectAttributes redirectAttributes){
		content=StringUtils.trim(content);
		Suggestion suggestion = this.suggestionService.create(person.getId(), content);
		this.suggestionService.save(suggestion);
		redirectAttributes.addFlashAttribute(Constant.MESSAGE_NAME, "保存成功");
		return "redirect:/suggestion"; 
	}

	@RequestMapping(value="/delete",method=RequestMethod.POST)
	@ResponseBody
	public boolean delete(Long id){
		this.suggestionService.delete(id);
		HashMap<String,Object> map = new HashMap<String,Object>();
		map.put("result", true);
		return true;
	}
}
