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

@Controller
@RequestMapping("/admin/deptkpi/{year}/department/{departmentId}")
public class PreviewCtl {
	
	@Resource
	PreviewFacade previewFacade;
	@Resource
	MessagePasser messagePasser;

	
	private interface Page {
		String PATH = "deptkpi/preview/";
		String PREVIEW=PATH+"preview";
	}

	@RequestMapping(value="/preview",method=RequestMethod.GET)
	public String toPreview(@PathVariable("year")Long year,@PathVariable("departmentId")Long departmentId,Map<String,Object> map){
		PreviewPage previewPage = previewFacade.getDeptKpiForPreview(year, departmentId);
		map.put("previewPage", previewPage);
		map.put(Constant.MESSAGE_NAME, messagePasser.getMessage());
		return Page.PREVIEW;
	}
}
