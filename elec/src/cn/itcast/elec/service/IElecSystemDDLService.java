package cn.itcast.elec.service;

import java.util.List;

import cn.itcast.elec.web.vo.ElecSystemDDLForm;
import cn.itcast.elec.web.vo.ElecUserForm;

public interface IElecSystemDDLService {
		public final static String SERVICE_NAME="cn.itcast.elec.service.impl.ElecSystemDDLServiceImpl";

		List<ElecSystemDDLForm> findKeyWord();

		List<ElecSystemDDLForm> findElecSystemDDLListByKeyword(String keyword);

		void saveElecSystemDDL(ElecSystemDDLForm elecSystemDDLForm);

}
