package cn.itcast.elec.dao.impl;

import java.io.Serializable;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import cn.itcast.elec.dao.ICommonDao;
import cn.itcast.elec.domain.ElecText;

public class CommonDaoImpl<T> extends HibernateDaoSupport  implements ICommonDao<T> {


	@Override
	public void update(T entity) {
		this.getHibernateTemplate().update(entity);
	}

	@Override
	public void save(T entity) {
		this.getHibernateTemplate().save(entity);
	}

	@Resource(name="sessionFactory")
	public final void setSessionFactoryDi(SessionFactory sessionFactory){
		super.setSessionFactory(sessionFactory);
	}

	@Override
	public T findObjectById(Class clazz,Serializable id) {
//		ParameterizedType pt=(ParameterizedType) this.getClass().getGenericSuperclass();
//		Class entity=(Class) pt.getActualTypeArguments()[0];
		return  (T) this.getHibernateTemplate().get(clazz,id);
	}

	@Override
	public void deleteObjectByIds(Class clazz,Serializable[] ids) {
		for(int i=0;i<ids.length;i++){
			Object object=this.getHibernateTemplate().get(clazz, ids[i]);
			this.getHibernateTemplate().delete(object);
		}
	}

	@Override
	public void deleteObjectByCollection(Collection<T> elecTexts) {
			this.getHibernateTemplate().deleteAll(elecTexts);
	}

	@Override
	public List<T> findCollectionByConditionNoPage(Class c,String hqlWhere,
			final Object[] params, LinkedHashMap<String, String> orderby) {
		
		String hql="from "+c.getSimpleName()+" o where 1=1";
		String hqlOrderBy=this.orderByCondition(orderby);
		
		final String finalhql=hql+hqlWhere+hqlOrderBy;
		List<T> list=(List<T>) this.getHibernateTemplate().execute(new HibernateCallback<Object>() {
			@Override
			public Object doInHibernate(Session session) throws HibernateException,
					SQLException {
				Query query= session.createQuery(finalhql);
				setParams(query, params);
				return  query.list();
			}
		});
		return list;
	}
		
	private void setParams(Query query,Object[] params) {
		
			for(int i=0;params!=null&&i<params.length;i++){
				query.setParameter(i, params[i]);
			}
	}
	
	private String orderByCondition(LinkedHashMap<String,String> orderby){
		StringBuffer buffer=new StringBuffer("");
		if(orderby!=null){
			buffer.append(" order by ");		
			for(Map.Entry<String, String> map:orderby.entrySet()){
				buffer.append(" "+map.getKey()+" "+map.getValue()+",");
			}
			buffer.deleteCharAt(buffer.length()-1);
		}
		return buffer.toString();
	}

	@Override
	public void saveObjectByCollection(Collection<T> entities) {
		this.getHibernateTemplate().saveOrUpdateAll(entities);
	}
	
	
}
