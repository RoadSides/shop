package cn.itcast.elec.service.impl;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecCommonMsgDao;
import cn.itcast.elec.domain.ElecCommonMsg;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.utils.UUIDUtils;
import cn.itcast.elec.web.vo.ElecCommonMsgForm;

@Transactional(readOnly=true)
@Service(IElecCommonMsgService.SERVICE_NAME)
public class ElecCommonMsgServiceImpl implements IElecCommonMsgService {
		
		@Resource(name=IElecCommonMsgDao.SERVICE_NAME)
		private IElecCommonMsgDao elecCommonMsgDao;

		@Override
		public List<ElecCommonMsgForm> findElecCommonMsgList() {
			String hqlWhere="";
			Object[] params=null;
			LinkedHashMap<String, String>  orderby=new LinkedHashMap<String, String>();
			orderby.put("o.createDate", "desc");
			List<ElecCommonMsg>  list=elecCommonMsgDao.findCollectionByConditionNoPage(ElecCommonMsg.class,hqlWhere, params, orderby);
			List<ElecCommonMsgForm>  formList=this.elecCommonMsgPOListToVOList(list);
			return formList;
		}

		private List<ElecCommonMsgForm> elecCommonMsgPOListToVOList(
				List<ElecCommonMsg> list) {
			List<ElecCommonMsgForm>  formList=new LinkedList<ElecCommonMsgForm>();
			ElecCommonMsgForm elecCommonMsgForm=null;
			for(int i=0;list!=null&&i<list.size();i++){
				new ElecCommonMsgForm();
				elecCommonMsgForm=new ElecCommonMsgForm();
				ElecCommonMsg elecCommonMsg=list.get(i);
				 elecCommonMsgForm.setComID(elecCommonMsg.getComID());
				 elecCommonMsgForm.setDevRun(elecCommonMsg.getDevRun());
				 elecCommonMsgForm.setStationRun(elecCommonMsg.getStationRun());
				 elecCommonMsgForm.setCreateDate(String.valueOf(elecCommonMsg.getCreateDate()!=null?elecCommonMsg.getCreateDate():"没有记录时间"));
				 formList.add(elecCommonMsgForm);
			}
			return formList;
		}

		@Override
		@Transactional(isolation=Isolation.DEFAULT,readOnly=false)
		public void save(ElecCommonMsgForm elecCommonMsgForm) {
			
				ElecCommonMsg elecCommonMsg=this.elecCommonMsgVOToPO(elecCommonMsgForm);
				this.elecCommonMsgDao.save(elecCommonMsg);
		}

		private ElecCommonMsg elecCommonMsgVOToPO(
				ElecCommonMsgForm elecCommonMsgForm) {
			 ElecCommonMsg elecCommonMsg=new ElecCommonMsg();
			 if(elecCommonMsgForm!=null){
				 elecCommonMsg.setStationRun(elecCommonMsgForm.getStationRun());
				 elecCommonMsg.setComID(UUIDUtils.getUUID());
				 elecCommonMsg.setCreateDate(new Date());
				 elecCommonMsg.setDevRun(elecCommonMsgForm.getDevRun());
			 }
			return elecCommonMsg;
		}

		@Override
		public List<ElecCommonMsgForm> findElecCommonMsgListByCurrent() {
			java.sql.Date date=new java.sql.Date(new java.util.Date().getTime());
			String currentDate=date.toString();
			List<Object[]>  list=elecCommonMsgDao.findElecCommonMsgListByCurrent(currentDate);
			List<ElecCommonMsgForm>  formList=this.elecCommonMsgObjectListToVOList(currentDate,list);
			 return formList;
		}

		private List<ElecCommonMsgForm> elecCommonMsgObjectListToVOList(String currentDate,
				List<Object[]> list) {
			List<ElecCommonMsgForm>  formList=new ArrayList<ElecCommonMsgForm>();
			ElecCommonMsgForm elecCommonMsgForm=null;
			for(int i=0;list!=null&&i<list.size();i++){
				Object[] object=list.get(i);
				elecCommonMsgForm=new ElecCommonMsgForm();
				elecCommonMsgForm.setStationRun(object[1].toString());
				elecCommonMsgForm.setDevRun(object[2].toString());
				elecCommonMsgForm.setComID(object[0].toString());
				elecCommonMsgForm.setCreateDate(currentDate);
				formList.add(elecCommonMsgForm);
			}
			return formList;
		}

		
		
}
