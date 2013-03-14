package cn.fyg.pa.interfaces.module.atten.busiout;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.BusioutService;
import cn.fyg.pa.domain.model.busiout.AMPM;
import cn.fyg.pa.domain.model.busiout.Busiout;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;

@Controller
@RequestMapping("/atten/{personId}/busiout")
public class BusioutCtl {
	
	private interface Page{
		String PATH = "busiout/";
		String LIST = PATH + "list";
		String NEW = PATH + "new";
	}
	
	@Resource
	PersonRepository personRepository;
	@Resource
	BusioutService busioutService;
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
    }
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="list",method=RequestMethod.GET)
	public String toList(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		return Page.LIST;
	}

	@RequestMapping(value="new",method=RequestMethod.GET)
	public String toNew(@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		Busiout busiout = busioutService.create(person);
		map.put("busiout", busiout);
		map.put("ampms", AMPM.values());
		return Page.NEW;
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(Busiout busiout,@ModelAttribute("person")Person person,Map<String,Object> map,HttpSession session){
		
		return "";
	}
	
}
