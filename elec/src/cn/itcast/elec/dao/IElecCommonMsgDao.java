package cn.itcast.elec.dao;

import java.util.List;

import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.web.vo.ElecCommonMsgForm;

public interface IElecCommonMsgDao extends ICommonDao<ElecCommonMsg>{
			public final static String SERVICE_NAME="cn.itcast.elec.dao.impl.ElecCommonMsgDaoImpl";

			List<Object[]> findElecCommonMsgListByCurrent(String currentDate);
			
}
