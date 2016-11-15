package cn.itcast.elec.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecUserRoleDao;
import cn.itcast.elec.domain.ElecUserRole;

@Repository(IElecUserRoleDao.SERVICE_NAME)
public class ElecUserRoleDaoImpl extends CommonDaoImpl<ElecUserRole> implements
		IElecUserRoleDao {

	@Override
	public List<Object[]> findElecUserListByRoleID(String roleID) {
		final String sql="SELECT DISTINCT CASE elec_user_role.RoleID WHEN ?  THEN '1' ELSE '0' END AS flag,"
				+ "elec_user.UserID as userID,"
				+ "elec_user.UserName  as userName,"
				+"elec_user.LogonName  as logonName "
				+"FROM elec_user LEFT OUTER JOIN elec_user_role "
				+"ON elec_user.UserID=elec_user_role.UserID "
				+"AND elec_user_role.RoleID=? "
				+ " WHERE elec_user.IsDuty='1'";
		final String id=roleID;
		List<Object[]>  list=this.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session)
					throws HibernateException, SQLException {
					Query query= session.createSQLQuery(sql);
					query.setString(0, id);
					query.setString(1, id);
					return query.list();
			}
			
		});
		return list;
	}
		
}
