package cn.fyg.pa.interfaces.module.admin.companykpi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.application.IdrYearCompanyService;
import cn.fyg.pa.application.IdrYearTypeWeightService;
import cn.fyg.pa.domain.model.companykpi.IdrYearCompany;
import cn.fyg.pa.domain.model.companykpiitem.IdrCompany;
import cn.fyg.pa.domain.model.yeartypeweight.IdrYearTypeWeight;
import cn.fyg.pa.domain.shared.Result;
import cn.fyg.pa.interfaces.module.shared.message.MessagePasser;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;
import cn.fyg.pa.interfaces.module.shared.tool.JsonUtil;

@Controller
@RequestMapping("/admin/idrcompany")
public class IdrCompanyCtl {

	@Resource
	IdrYearCompanyService idrYearCompanyService;
	@Resource
	IdrYearTypeWeightService idrYearTypeWeightService;
	@Resource
	MessagePasser messagePasser;
	
	@RequestMapping(value="edit/{year}",method=RequestMethod.GET)
	public String toEdit(@PathVariable("year")Long year,Map<String,Object> map){
		IdrYearCompany idrYearCompany=idrYearCompanyService.findByYear(year);
		IdrYearTypeWeight idrYearTypeWeight=idrYearTypeWeightService.getByYear(year);
		map.put("idrYearCompany", idrYearCompany);
		map.put("idrYearTypeWeight", idrYearTypeWeight);
		map.put("idrTypeWeightsJson", JsonUtil.toArrayStr(idrYearTypeWeight.getIdrTypeWeight(),new String[]{"idrYearTypeWeight"}));
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return "idrcompany/edit";
	}
	
	@RequestMapping(value="save",method=RequestMethod.POST)
	public String save(IdrYearCompany idrYearCompanyForm){
		idrYearCompanyForm=idrYearCompanyService.save(idrYearCompanyForm);
		 messagePasser.setMessage("保存成功！");
		return "redirect:edit/"+idrYearCompanyForm.getYear();
	}
	
	@RequestMapping(value="sort",method=RequestMethod.POST)
	public String sort(IdrYearCompany idrYearCompanyForm){
		idrYearCompanyForm=idrYearCompanyService.sortIdrCompanyByIdrTypeWeight(idrYearCompanyForm);
		messagePasser.setMessage("排序完成！");
		return "redirect:edit/"+idrYearCompanyForm.getYear();
	}
	
	@RequestMapping(value="commit",method=RequestMethod.POST)
	public String commit(IdrYearCompany idrYearCompanyForm){
		idrYearCompanyForm=idrYearCompanyService.sortIdrCompanyByIdrTypeWeight(idrYearCompanyForm);
		Result result=idrYearCompanyForm.verifySelf();
		if(result.pass()){
			messagePasser.setMessage("提交通过！");
		}else{
			messagePasser.setMessage(String.format("提交失败，%s!", result.cause()));
		}
		return "redirect:edit/"+idrYearCompanyForm.getYear();
	}
	
	@RequestMapping(value="preview/{year}",method=RequestMethod.GET)
	public String preview(@PathVariable("year")Long year,Map<String,Object> map){
		IdrYearCompany idrYearCompany=idrYearCompanyService.findByYear(year);
		Map<Long,Integer> rowSpan=getRowSpan(idrYearCompany.getIdrCompany());
		map.put("idrYearCompany", idrYearCompany);
		map.put("rowSpan", rowSpan);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return "idrcompany/view";
	}

	private Map<Long, Integer> getRowSpan(List<IdrCompany> idrCompanyList) {
		Map<Long,Integer> rowSpan=new HashMap<Long,Integer>();
		if(idrCompanyList.isEmpty()){
			return rowSpan;
		}
		Long idrTypeWeightId=idrCompanyList.get(0).getIdrTypeWeight().getId();
		Long sn=idrCompanyList.get(0).getSn();
		int rowNum=0;
		for(IdrCompany idrCompany:idrCompanyList){
			if(!idrCompany.getIdrTypeWeight().getId().equals(idrTypeWeightId)){
				rowSpan.put(sn, rowNum);
				idrTypeWeightId=idrCompany.getIdrTypeWeight().getId();
				sn=idrCompany.getSn();
				rowNum=0;
			}
			rowNum++;
		}
		rowSpan.put(sn, rowNum);
		return rowSpan;
	}
}
