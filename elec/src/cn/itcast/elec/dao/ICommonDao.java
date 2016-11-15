package cn.itcast.elec.dao;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;

import cn.itcast.elec.domain.ElecText;

public interface ICommonDao<T> {
		public void save(T entity);
		
		public void update(T entity);
		
		public T findObjectById(Class clazz,Serializable id);
		
		public void deleteObjectByIds(Class clazz,Serializable[] ids);
		
		public void deleteObjectByCollection(Collection<T> elecTexts);
		
		public List<T> findCollectionByConditionNoPage(Class  c,String hqlWhere,
				Object[] params, LinkedHashMap<String, String> orderby);
		
		public void saveObjectByCollection(Collection<T>  entities);
}
