package cn.itcast.elec.dao.impl;

import java.util.Collection;
import java.util.List;

import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecSystemDDLDao;
import cn.itcast.elec.domain.ElecSystemDDL;

@Repository(IElecSystemDDLDao.SERVICE_NAME)
public class ElecSystemDDLDaoImpl extends CommonDaoImpl<ElecSystemDDL>  implements IElecSystemDDLDao {

	@Override
	public List<Object> findKeyWord() {
		String hql="select distinct o.keyword from ElecSystemDDL o";
		List<Object> list= this.getHibernateTemplate().find(hql);
		return list;
	}

	@Override
	public void saveObjectByCollection(Collection<ElecSystemDDL> list) {
		this.getHibernateTemplate().saveOrUpdateAll(list);
	}

	@Override
	public String findDDLName(String ddlCode, String keyword) {
		String hql="from ElecSystemDDL where keyword = '"+keyword+"'  and ddlCode='"+ddlCode+"'";
		List<ElecSystemDDL>  list=this.getHibernateTemplate().find(hql);
		String ddlName="";
		if(list!=null&&list.size()>0){
			ElecSystemDDL elecSystemDDL=list.get(0);
			ddlName=elecSystemDDL.getDdlName();
		}
		return ddlName;
	}
		
}
