package cn.fyg.pa.domain.model.yearchk;

import java.util.List;

public interface ChkitemRepository {

	Fychkitem find(Long id);

	List<Fychkitem> getItemsByYear(Long year);

}