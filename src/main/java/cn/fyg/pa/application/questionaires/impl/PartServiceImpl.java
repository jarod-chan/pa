package cn.fyg.pa.application.questionaires.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.fyg.pa.application.questionaires.PartService;
import cn.fyg.pa.domain.model.questionaires.part.Part;
import cn.fyg.pa.domain.model.questionaires.part.PartRepository;

@Service
public class PartServiceImpl implements PartService {
	
	@Resource
	PartRepository partRepository;

	@Override
	public List<Part> findQuesParts(Long qtid) {
		return partRepository.findByQtid(qtid);
	}

	@Override
	public Part findFirstPart(Long qtid) {
		List<Part> parts = partRepository.findByQtid(qtid);
		if(parts==null||parts.isEmpty()){
			return null;
		}
		return parts.get(0);
	}

	@Override
	public List<Part> findPrevNext(Long qtid, Long partid) {
		List<Part> parts = partRepository.findByQtid(qtid);
		int prev=-1,current=-1,next=-1;
		for(int i=0;i<parts.size();i++){
			Part part = parts.get(i);
			if(part.getId().equals(partid)){
				current=i;
				prev=current-1;
				next=current+1;
				break;
			}
		}
		List<Part> ret=new ArrayList<Part>();
		ret.add(prev<0?null:parts.get(prev));
		ret.add(parts.get(current));
		ret.add(next>=parts.size()?null:parts.get(next));
		return ret;
	}

}
