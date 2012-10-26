package cn.fyg.pa.domain.model.questionaires.part;

import java.util.List;

public interface PartRepository {
	
	List<Part> findByQtid(Long qtid);

}
