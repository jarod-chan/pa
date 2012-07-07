package cn.fyg.pa.interfaces.module.admin.deptkpi.preview;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import cn.fyg.pa.interfaces.module.admin.deptkpi.preview.dto.PreviewPage;
import cn.fyg.pa.interfaces.module.shared.message.MessagePasser;
import cn.fyg.pa.interfaces.module.shared.tool.Constant;
import cn.fyg.pa.interfaces.module.shared.tool.Strpath;

@Controller
@RequestMapping("/admin/deptkpi/{year}/department/{departmentId}")
public class PreviewCtl {
	
	private static Strpath TEMPLATE_FACTORY=new Strpath("deptkpi/preview/");
	private static class Page{
		static String PREVIEW=TEMPLATE_FACTORY.getPath("preview");
	}
	
	@Resource
	PreviewFacade previewFacade;
	@Resource
	MessagePasser messagePasser;


	@RequestMapping(value="/preview",method=RequestMethod.GET)
	public String toPreview(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map){
		PreviewPage previewPage = previewFacade.getDeptKpiForPreview(year, departmentId);
		map.put("previewPage", previewPage);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return Page.PREVIEW;
	}
}
