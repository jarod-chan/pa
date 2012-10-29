package cn.fyg.pa.interfaces.module.questionaires.admin;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import cn.fyg.pa.application.questionaires.KeyService;
import cn.fyg.pa.application.questionaires.QuesService;
import cn.fyg.pa.domain.model.questionaires.key.Key;
import cn.fyg.pa.domain.model.questionaires.key.KeyState;
import cn.fyg.pa.domain.model.questionaires.ques.Ques;
import cn.fyg.pa.interfaces.module.shared.tool.Tool;

@Component
public class QuesAdminFacade {
	
	@Resource
	QuesService quesService;
	@Resource
	KeyService keyService;
	
	public List<QuesBean> getQuesBeanList(){
		List<Ques> quesList = quesService.findAll();
		List<QuesBean> quesBeanList=new ArrayList<QuesBean>();
		for (Ques ques : quesList) {
			QuesBean quesBean=new QuesBean();
			quesBean.setQues(ques);
			Map<KeyState, Integer> keyStateCount = keyService.getKeyStateCount(ques.getId());
			quesBean.setValid(getStateCount(keyStateCount,KeyState.nouse)+getStateCount(keyStateCount,KeyState.used)+getStateCount(keyStateCount,KeyState.finish));
			quesBean.setNotFinish(getStateCount(keyStateCount,KeyState.nouse)+getStateCount(keyStateCount,KeyState.used));
			quesBean.setFinish(getStateCount(keyStateCount,KeyState.finish));
			quesBean.setInvalid(getStateCount(keyStateCount,KeyState.invalid));
			quesBeanList.add(quesBean);
		}
		return quesBeanList;
	}
	
	private int getStateCount(Map<KeyState, Integer> keyStateCount,KeyState keyState){
		Integer count = keyStateCount.get(keyState);
		return count==null?0:count.intValue();
	}
	
	public void produceKey(Long qtid,int keyNum){
		List<Key> keys=new ArrayList<Key>();
		for(int i=0;i<keyNum;i++){
			Key key = new Key();
			key.setQtid(qtid);
			key.setState(KeyState.nouse);
			key.setUuid(Tool.getPassword(4L)+"-"+Tool.getPassword(4L));
			keys.add(key);
		}
		keyService.save(keys);
	}
	
	public void recover(String keystr){
		Key key = keyService.find(keystr);
		if(key==null){
			throw new RuntimeException(String.format("验证码：%s不存在！", keystr));
		}
		keyService.invalid(keystr);
	}

}
