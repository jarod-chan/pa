package cn.fyg.pa.interfaces.module.finance.summarysnapshot;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.SummarySnapshotService;
import cn.fyg.pa.domain.model.person.Person;
import cn.fyg.pa.domain.model.person.PersonRepository;
import cn.fyg.pa.domain.model.summarysnapshot.SnapshotEnum;
import cn.fyg.pa.domain.model.summarysnapshot.SnapshotItem;
import cn.fyg.pa.domain.model.summarysnapshot.SummarySnapshot;
import cn.fyg.pa.domain.model.summarysnapshot.SummarySnapshotRepository;
import cn.fyg.pa.interfaces.module.finance.summarysnapshot.dto.PageItemBean;
import cn.fyg.pa.interfaces.module.shared.bean.YearAndPrevMonth;
import cn.fyg.pa.interfaces.module.shared.bean.YearBean;
import cn.fyg.pa.interfaces.module.shared.message.MessagePasser;
import cn.fyg.pa.interfaces.module.shared.session.SessionUtil;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;
import cn.fyg.pa.interfaces.module.shared.tool.DateTool;

@Controller
@RequestMapping("/finance/{personId}/summarysnapshot")
public class SummarySnapshotCtl {
	
	private static final Logger logger=LoggerFactory.getLogger(SummarySnapshotCtl.class);
	
	private interface Page {
		String PATH = "finance/summarysnapshot/";
		String LIST = PATH + "list";
		String RECEIVE = PATH + "receive";
		String VIEW = PATH + "view";
	}

	private static final String QUERY_BEAN_LIST = "finance.summarysnapshot.summarysnapshotctl.list";
	private static final String QUERY_BEAN_RECEIVE = "finance.summarysnapshot.summarysnapshotctl.receive";
	
	@Resource
	SessionUtil sessionUtil;
	@Resource
	PersonRepository personRepository;
	@Resource
	SummarySnapshotFacade summarySnapshotFacade;
	@Resource
	SummarySnapshotService summarySnapshotService;
	@Resource
	SummarySnapshotRepository summarySnapshotRepository;
	@Resource
	MessagePasser messagePasser;
	
	@ModelAttribute("person")
	public Person initPerson(@PathVariable("personId") Long personId){
		return personRepository.find(personId);
	}
	
	@RequestMapping(value="",method=RequestMethod.GET)
	public String toReceive(Map<String,Object> map,HttpSession session){
		YearAndPrevMonth queryBean=getYearAndPrevMonthFromSession();
		SummarySnapshot summarySnapshot = summarySnapshotRepository.findByPeriod(queryBean.getYear(), queryBean.getMonth());
		List<SnapshotItem> snapshotItemList =
				summarySnapshot!=null?
				summarySnapshot.getSnapshotItems():
				summarySnapshotFacade.buildSnapshotItemList(queryBean.getYear(), queryBean.getMonth());

		PageItemBean pageItemBean=summarySnapshotFacade.buildPageItemBean(snapshotItemList);
		map.put("dateTool", new DateTool());
		map.put("summarySnapshot", summarySnapshot);
		map.put("pageItemBean", pageItemBean);
		map.put("queryBean", queryBean);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return Page.RECEIVE;
	}

	private YearAndPrevMonth getYearAndPrevMonthFromSession() {
		YearAndPrevMonth monthChkQueryBean =  sessionUtil.getValue(QUERY_BEAN_RECEIVE);
		if(monthChkQueryBean==null){
			monthChkQueryBean=new YearAndPrevMonth();
			sessionUtil.setValue(QUERY_BEAN_RECEIVE, monthChkQueryBean);
		}
		return monthChkQueryBean;
	}
	
	@RequestMapping(value="",method=RequestMethod.POST)
	public String setReceiveQueryBean(YearAndPrevMonth queryBean){
		sessionUtil.setValue(QUERY_BEAN_RECEIVE, queryBean);
		return "redirect:summarysnapshot";
	}
	
	@RequestMapping(value="/save",method=RequestMethod.POST)
	public String save(SummarySnapshot summarySnapshot){
		summarySnapshot.setState(SnapshotEnum.RECEIVED);
		summarySnapshot.setLogDate(new Date());
		summarySnapshotService.save(summarySnapshot);
		messagePasser.setMessage("考核结果接收成功");
		return "redirect:../summarysnapshot";
	}
	
	@RequestMapping(value="/history",method=RequestMethod.GET)
	public String  toList(Map<String,Object> map){
		YearBean queryBean=getYearBeanFromSession();
		List<SummarySnapshot> summarySnapshots = summarySnapshotRepository.findByYear(queryBean.getYear());
		map.put("summarySnapshots", summarySnapshots);
		map.put("dateTool", new DateTool());
		map.put("queryBean", queryBean);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return Page.LIST;
	}
	
	private YearBean getYearBeanFromSession(){
		YearBean yearBean=sessionUtil.getValue(QUERY_BEAN_LIST);
		if(yearBean==null){
			yearBean=new YearBean();
			sessionUtil.setValue(QUERY_BEAN_LIST, yearBean);
		}
		return yearBean;
	}
	
	@RequestMapping(value="/history",method=RequestMethod.POST)
	public String setYearBean(YearBean yearBean){
		sessionUtil.setValue(QUERY_BEAN_LIST, yearBean);
		return "redirect:history";
	}
	
	@RequestMapping(value="/history/{summarySnapshotId}",method=RequestMethod.GET)
	public String toView(@PathVariable("summarySnapshotId") Long summarySnapshotId,Map<String,Object> map){
		SummarySnapshot summarySnapshot = summarySnapshotRepository.find(summarySnapshotId);
		map.put("summarySnapshot", summarySnapshot);
		PageItemBean pageItemBean = summarySnapshotFacade.buildPageItemBean(summarySnapshot.getSnapshotItems());
		map.put("pageItemBean", pageItemBean);
		boolean isSummarySnapshotCanRemove=summarySnapshot.getState().equals(SnapshotEnum.RECEIVED);
		map.put("isSummarySnapshotCanRemove", isSummarySnapshotCanRemove);
		return Page.VIEW;
	}
	
	
	@RequestMapping(value="/{summarySnapshotId}/remove",method=RequestMethod.POST)
	public String remove(@PathVariable("summarySnapshotId") Long summarySnapshotId){
		summarySnapshotService.remove(summarySnapshotId);
		messagePasser.setMessage("考核结果已删除");
		return "redirect:../history";
	}
	

	

	

}
