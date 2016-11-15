package cn.itcast.elec.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecTextDao;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;
import cn.itcast.elec.web.vo.ElecTextForm;

@Transactional(readOnly=true)
@Service(IElecTextService.SERVICE_NAME)
public class ElecTextServiceImpl implements IElecTextService {

	@Resource(name=IElecTextDao.SERVICE_NAME)
	private IElecTextDao elecTextDao;
	/**
	 * isolation:隔离级别
	 * readOnly：只读
	 * propagation:广播隔离级别
	 */
	@Transactional(isolation=Isolation.DEFAULT,readOnly=false)
	@Override
	public void saveElectext(ElecText electext) {
		elecTextDao.save(electext);
	}
	
	@Override
	public List<ElecText> findCollectionByConditionNoPage(
			ElecTextForm elecTextForm) {
		String hqlWhere="";
		List<String>  paramsList=new ArrayList<String>();
		if(elecTextForm!=null&&elecTextForm.getTextName().trim()!=null){
				hqlWhere+=" and o.textName like ?";
				paramsList.add("%"+elecTextForm.getTextName()+"%");
		}	
		if(elecTextForm!=null&&elecTextForm.getTextRemark().trim()!=null){
				hqlWhere+=" and o.textName like ?";
				paramsList.add("%"+elecTextForm.getTextRemark()+"%");
		}
		
		Object[] params=paramsList.toArray();

		LinkedHashMap<String, String> orderby=new LinkedHashMap<String, String>();
		orderby.put("o.textDate", "desc");
		orderby.put("o.textName","asc");
		//查询列表
		List<ElecText> list=elecTextDao.findCollectionByConditionNoPage(ElecText.class,hqlWhere,params,orderby);
		return list;
	}
}
