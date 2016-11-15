package cn.itcast.elec.dao;

import java.util.List;

import cn.itcast.elec.domain.ElecUserRole;

public interface IElecUserRoleDao extends ICommonDao<ElecUserRole> {
		public final static String SERVICE_NAME="cn.itcast.elec.dao.impl.ElecUserRoleDaoImpl";

		List<Object[]> findElecUserListByRoleID(String roleID);
		
}
