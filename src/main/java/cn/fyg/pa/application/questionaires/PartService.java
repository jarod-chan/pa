package cn.fyg.pa.application.questionaires;

import java.util.List;

import cn.fyg.pa.domain.model.questionaires.part.Part;

public interface PartService {
	
	List<Part> findQuesParts(Long qtid);
	
	Part findFirstPart(Long qtid);
	
	List<Part> findPrevNext(Long qtid,Long partid);
}
