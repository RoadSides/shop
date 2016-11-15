package cn.itcast.elec.dao;

import java.util.Collection;
import java.util.List;

import cn.itcast.elec.domain.ElecSystemDDL;

public interface IElecSystemDDLDao extends ICommonDao<ElecSystemDDL>{
		public final static String SERVICE_NAME="cn.itcast.elec.dao.impl.ElecSystemDDLDaoImpl";

		List<Object> findKeyWord();

		void saveObjectByCollection(Collection<ElecSystemDDL> list);

		String findDDLName(String ddlCode, String keyword);
}
