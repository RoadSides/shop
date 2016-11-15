package cn.itcast.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecUserDao;
import cn.itcast.elec.domain.ElecUser;

@Repository(IElecUserDao.SERVICE_NAME)
public class ElecUserDaoImpl extends CommonDaoImpl<ElecUser> implements IElecUserDao {
			

}
