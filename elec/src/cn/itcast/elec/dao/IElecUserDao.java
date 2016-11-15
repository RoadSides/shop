package cn.itcast.elec.dao;

import cn.itcast.elec.domain.ElecUser;

public interface IElecUserDao extends ICommonDao<ElecUser> {
		public final static String SERVICE_NAME="cn.itcast.elec.dao.impl.ElecUserDaoImpl";
		
}
