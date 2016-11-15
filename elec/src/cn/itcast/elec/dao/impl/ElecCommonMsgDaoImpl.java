package cn.itcast.elec.dao.impl;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.stereotype.Repository;

import cn.itcast.elec.dao.IElecCommonMsgDao;
import cn.itcast.elec.domain.ElecCommonMsg;

@Repository(IElecCommonMsgDao.SERVICE_NAME)
public class ElecCommonMsgDaoImpl extends CommonDaoImpl<ElecCommonMsg>  implements IElecCommonMsgDao {

	@Override
	public List<Object[]> findElecCommonMsgListByCurrent(String currentDate) {
		final String sql="SELECT * FROM elec_commonmsg WHERE CreateDate='"+"2016-11-04"+"'";
		List<Object[]> list=this.getHibernateTemplate().execute(new HibernateCallback() {

			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query=session.createSQLQuery(sql);
				return query.list();
			}
				
		});
		return list;
	}

			
}
