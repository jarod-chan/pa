package cn.fyg.pa.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import cn.fyg.pa.dao.PersonDao;
import cn.fyg.pa.model.Person;
import cn.fyg.pa.model.enums.ManageEnum;
import cn.fyg.pa.model.enums.TypeEnum;
import cn.fyg.pa.tool.CommonModelAndView;
import cn.fyg.pa.tool.Constant;
import cn.fyg.pa.tool.EnumUtil;
import cn.fyg.pa.tool.Tool;


@Controller
@RequestMapping("/person")
public class PersonCtl {
	
	private static final Logger logger = LoggerFactory.getLogger(PersonCtl.class);

	@Autowired
	private PersonDao personDao;
	
	@RequestMapping(value="")
	public ModelAndView index(@CookieValue(value="chkstr",required=false) String cookieChkstr) {
		logger.info("get list");
		
		if(!Constant.ADMIN_PASSWORD.equals(cookieChkstr)){
			return CommonModelAndView.getHomeModelAndView();
		}
		
		ModelAndView mav = new ModelAndView();
		List<Person> persons = personDao.getAllFyperson();
		mav.addObject("persons",persons);
		mav.setViewName("person/list");
		return mav;
	}
	
	@RequestMapping(value="/new")
	public ModelAndView _new(){
		logger.info("get user not with id");
		Person person=new Person();
		ModelAndView mav=new ModelAndView();
		mav.addObject("typeEnum",EnumUtil.enumToMap(TypeEnum.values()));
		mav.addObject("manageEnum",EnumUtil.enumToMap(ManageEnum.values()));
		mav.addObject("person",person);
		mav.setViewName("person/new");
		return mav;
	}
	
    /** 保存新增 */  
    @RequestMapping(value="",method=RequestMethod.POST)  
    public ModelAndView create(@Valid Person person,BindingResult result) throws Exception {
    	if(result.hasErrors()){
			ModelAndView mav=new ModelAndView();
			mav.addObject("typeEnum",EnumUtil.enumToMap(TypeEnum.values()));
			mav.addObject("manageEnum",EnumUtil.enumToMap(ManageEnum.values()));
			mav.addObject("person", person);
			mav.setViewName("person/new");
			return mav;
		}
    	personDao.save(person);  
        return new ModelAndView("redirect:/person");  
    }  
      
	/**显示编辑*/
	@RequestMapping(value="/{personId}")
	public ModelAndView edit(@PathVariable("personId")Long personId){
		logger.info("get user with id");
		Person person=personDao.find(personId);
		ModelAndView mav=new ModelAndView();
		mav.addObject("typeEnum",EnumUtil.enumToMap(TypeEnum.values()));
		mav.addObject("manageEnum",EnumUtil.enumToMap(ManageEnum.values()));
		mav.addObject("person", person);
		mav.setViewName("person/edit");
		return mav;
	}
	
	/**保存编辑*/
	@RequestMapping(value="/{personId}",method=RequestMethod.PUT)
	public ModelAndView update(@PathVariable("personId")Long personId,@Valid Person person,BindingResult result){
		if(result.hasErrors()){
			person.setId(personId);
			ModelAndView mav=new ModelAndView();
			mav.addObject("typeEnum",EnumUtil.enumToMap(TypeEnum.values()));
			mav.addObject("manageEnum",EnumUtil.enumToMap(ManageEnum.values()));
			mav.addObject("person", person);
			mav.setViewName("person/edit");
			return mav;
		}
		logger.info("post user");
		person.setId(personId);
		personDao.save(person);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/person");
		return mav;
	}
	
	@RequestMapping(value="/{personId}",method=RequestMethod.DELETE)
	public ModelAndView delete(@PathVariable("personId")Long personId){
		logger.info("post user");
		personDao.remove(personId);
		ModelAndView mav=new ModelAndView();
		mav.setViewName("redirect:/person");
		return mav;
	}
	
	@RequestMapping(method=RequestMethod.POST,value="/password")
	public ModelAndView initPassword(@RequestParam(value="passlen",required=false) Long passlen,@RequestParam(value="type",required=false) String type,@CookieValue(value="chkstr",required=false) String cookieChkstr) {
		logger.info("post initPassword");
		if(!Constant.ADMIN_PASSWORD.equals(cookieChkstr)){
			return CommonModelAndView.getHomeModelAndView();
		}
		
		List<Person> people = personDao.getAllFyperson();
		List<Person> peopleHasPassword=new ArrayList<Person>();
		for (Person fyperson : people) {
			if(type.equals("reset")){
				fyperson.setChkstr(Tool.getPassword(passlen));
				peopleHasPassword.add(fyperson);
			}else if(type.equals("init")){
				if(fyperson.getChkstr()==null||fyperson.getChkstr().trim().equals("")){
					fyperson.setChkstr(Tool.getPassword(passlen));
					peopleHasPassword.add(fyperson);
				}
			}

		}
		personDao.saveAll(peopleHasPassword);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("persons",people);
		mav.addObject("msg","操作成功");
		mav.setViewName("redirect:/person");
		return mav;
		
	}
	

	

	
	
	

}
