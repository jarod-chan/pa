package cn.fyg.pa.application.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.fyg.pa.application.GroupService;
import cn.fyg.pa.application.StringList;
import cn.fyg.pa.domain.model.group.Group;
import cn.fyg.pa.domain.model.group.GroupEntity;
import cn.fyg.pa.domain.model.group.GroupRepository;
import cn.fyg.pa.domain.model.group.MembershipEntity;
import cn.fyg.pa.domain.model.group.MembershipRepository;


@Service("groupService")
public class GroupServiceImpl implements GroupService {
	
	public static final String SEPARATE="."; 
	
	@Autowired
	GroupRepository groupRepository;
	@Autowired
	MembershipRepository membershipRepository;
	
	@Override
	public Group createGroup() {
		GroupEntity groupEntity = new GroupEntity();
		return groupEntity;
	}

	@Override
	@Transactional
	public Group saveGroup(Group group) {
		GroupEntity groupEntity=(GroupEntity)group;
		GroupEntity parentEntity=(GroupEntity)groupEntity.getParent();
		String code=groupEntity.getKey();
		if(parentEntity!=null){
			code=parentEntity.getCode()+SEPARATE+code;
		}
		groupEntity.setCode(code);
		if(groupRepository.isConflict(groupEntity)){
			throw new RuntimeException("组织编码不能重复");
		}
		
		if(groupEntity.getUuid()!=null){				
			return groupRepository.update(groupEntity);
		}
		return groupRepository.persistent(groupEntity);
	}

	@Override
	@Transactional
	public Group findGroup(String key) {
		return groupRepository.find(key);
	}

	@Override
	@Transactional
	public void createMembership(String userKey, String groupKey) {
		if(membershipRepository.findByKey(userKey, groupKey)==null){
			String groupCode = groupRepository.find(groupKey).getCode();
			MembershipEntity membershipEntity=new MembershipEntity();
			membershipEntity.setGroupKey(groupKey);
			membershipEntity.setCode(groupCode);
			membershipEntity.setUserKey(userKey);
			membershipRepository.persistent(membershipEntity);
		}
	}

	@Override
	@Transactional
	public void deleteMembership(String userKey, String groupKey) {
		MembershipEntity membershipEntity = membershipRepository.findByKey(userKey, groupKey);
		if(membershipEntity!=null){
			membershipRepository.remove(membershipEntity);
		}		
	}

	@Override
	public StringList groupUserKey(String groupKey) {
		List<MembershipEntity> membershipEntitys=membershipRepository.findByGroupKey(groupKey);
		return new StringList(getUserKeyList(membershipEntitys));
	}

	@Override
	public StringList userGroupKey(String userKey) {
		List<MembershipEntity> membershipEntitys=membershipRepository.findByUserKey(userKey);
		List<String> result = new ArrayList<String>();
		for (MembershipEntity membershipEntity : membershipEntitys) {
			result.add(membershipEntity.getGroupKey());
		}
		return new StringList(result);
	}

	@Override
	public StringList parentUserKey(String groupKey,String userKey) {
		return parentUserKey(groupKey,userKey,1);
	}

	@Override
	public StringList parentUserKey(String groupKey,String userKey,int parentLevel) {
		GroupEntity groupEntity = groupRepository.find(groupKey);
		String rootCode=groupEntity.getCode();
		MembershipEntity membershipEntity=membershipRepository.findMaxCodeLikeGroupKeyAndUserKey(rootCode, userKey);
		String code=(membershipEntity==null?"":membershipEntity.getCode());
		if(!isLegalCode(rootCode,code,parentLevel)){
			throw new RuntimeException(String.format("code compute error.rootcode:[%s] code[%s]",rootCode,code));
		}
		
		if(parentLevel==0){
			return new StringList(new ArrayList<String>());
		}
		
		String targetCode = computeTargetCode(parentLevel, code);
		List<MembershipEntity> membershipEntitys=membershipRepository.findByCode(targetCode);
		return new StringList(getUserKeyList(membershipEntitys));
		
	}

	//返回所有用户编码
	private List<String> getUserKeyList(List<MembershipEntity> membershipEntitys) {
		List<String> result = new ArrayList<String>();
		for (MembershipEntity membershipEntity : membershipEntitys) {
			result.add(membershipEntity.getUserKey());
		}
		return result;
	}

	//计算上级编码
	private String computeTargetCode(int parentLevel, String code) {
		String targetCode=code;
		for(int i=0;i<parentLevel;i++){
			targetCode=StringUtils.substringBeforeLast(targetCode, SEPARATE);
		}
		return targetCode;
	}
	
	//判断计算参数是否合法
	private boolean isLegalCode(String rootCode, String code,int parentLevel) {
		if(StringUtils.isNotBlank(rootCode)&&StringUtils.isNotBlank(code)){
			String sub=StringUtils.substringAfter(code, rootCode+SEPARATE);
			if(StringUtils.split(sub, SEPARATE).length>=parentLevel){
				return true;
			}
		}
		return false;
	}

	@Override
	public List<StringList> reportLine(String groupKey, String userKey) {
		GroupEntity groupEntity = groupRepository.find(groupKey);
		String rootCode=groupEntity.getCode();
		MembershipEntity membershipEntity=membershipRepository.findMaxCodeLikeGroupKeyAndUserKey(rootCode, userKey);
		String code=(membershipEntity==null?"":membershipEntity.getCode());
		if(!isLegalCode(rootCode,code,0)){
			throw new RuntimeException(String.format("code compute error.rootcode:[%s] code[%s]",rootCode,code));
		}
		
		//获得所有编码
		List<String> codeList=new ArrayList<String>();
		String targetCode=code;
		
		while(!rootCode.equals(targetCode)){
			targetCode=StringUtils.substringBeforeLast(targetCode, SEPARATE);	
			codeList.add(targetCode);
		}
		
		System.out.println(codeList);
		
		List<MembershipEntity> membershipEntitys = membershipRepository.findByCodeList(codeList);
		Map<String,StringList> codeMap=getCodeMap(membershipEntitys);

		List<StringList> result = new ArrayList<StringList>();
		appendFirstToResult(userKey,result);
		appendMapToResult(codeList,codeMap,result);
		return result;
	}

	private void appendMapToResult(List<String> codeList,Map<String, StringList> codeMap, List<StringList> result) {
		for (String code : codeList) {
			StringList stringList=codeMap.get(code);
			result.add(stringList==null?new StringList(new ArrayList<String>()):stringList);
		}
	}

	private void appendFirstToResult(String userKey, List<StringList> result) {
		result.add(new StringList(Arrays.asList(userKey)));
	}

	private Map<String, StringList> getCodeMap(List<MembershipEntity> membershipEntitys) {
		Map<String,StringList> result=new HashMap<String,StringList>();
		for (MembershipEntity membershipEntity : membershipEntitys) {
			String code=membershipEntity.getCode();
			if(!result.keySet().contains(code)){
				result.put(code, new StringList(new ArrayList<String>()));
			}
			result.get(code).push(membershipEntity.getUserKey());	
		}
		return result;
	}

}
