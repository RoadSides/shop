package cn.itcast.elec.dao.impl;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecRolePopedomDao;
import cn.itcast.elec.domain.ElecRolePopedom;

@Repository(value=IElecRolePopedomDao.SERVICE_NAME)
public class ElecRolePopedomDaoImpl extends CommonDaoImpl<ElecRolePopedom> implements
		IElecRolePopedomDao {
		
}
