package cn.fyg.pa.infrastructure.perisistence;

import org.springframework.stereotype.Repository;

import cn.fyg.pa.domain.yearchk.EnableYearNotExist;
import cn.fyg.pa.domain.yearchk.YearConfigRepositroy;

@Repository
public class YearConfigRepositroyJpa implements YearConfigRepositroy {

	@Override
	public Long getEnableYear() throws EnableYearNotExist {
		// TODO Auto-generated method stub
//		throw new EnableYearNotExist();
		return 2012L;
	}

}
