package cn.itcast.elec.service;

import java.util.List;

import cn.itcast.elec.web.vo.ElecCommonMsgForm;

public interface IElecCommonMsgService {
		public final static String SERVICE_NAME="cn.itcast.elec.service.impl.ElecCommonMsgServiceImpl";

		List<ElecCommonMsgForm> findElecCommonMsgList();

		void save(ElecCommonMsgForm elecCommonMsgForm);

		List<ElecCommonMsgForm> findElecCommonMsgListByCurrent();

}
