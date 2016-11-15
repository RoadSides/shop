package cn.itcast.elec.service.impl;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecSystemDDLDao;
import cn.itcast.elec.domain.ElecSystemDDL;
import cn.itcast.elec.service.IElecSystemDDLService;
import cn.itcast.elec.web.vo.ElecSystemDDLForm;
import cn.itcast.elec.web.vo.ElecUserForm;

@Transactional(readOnly=true)
@Service(IElecSystemDDLService.SERVICE_NAME)
public class ElecSystemDDLServiceImpl implements IElecSystemDDLService{
		
		@Resource(name=IElecSystemDDLDao.SERVICE_NAME)
		private IElecSystemDDLDao elecSystemDDLDao;

		@Override
		public List<ElecSystemDDLForm> findKeyWord() {
			List<Object>  list=elecSystemDDLDao.findKeyWord();
			List<ElecSystemDDLForm>  formList=this.elecSystemDDLToVo(list);
			return formList;
		}

		private List<ElecSystemDDLForm> elecSystemDDLToVo(
				List<Object> list) {
			List<ElecSystemDDLForm> formList=new ArrayList<ElecSystemDDLForm>();
			ElecSystemDDLForm  elecSystemDDLForm=null;
			for(int i=0;list!=null&&i<list.size();i++){
				Object object=list.get(i);
				elecSystemDDLForm=new ElecSystemDDLForm();

				elecSystemDDLForm.setKeyword(object.toString());

				formList.add(elecSystemDDLForm);
			}
			return formList;
		}

		@Override
		public List<ElecSystemDDLForm> findElecSystemDDLListByKeyword(
				String keyword) {
			List<ElecSystemDDL> list=this.findSystemDDLListByCondition(keyword);
			List<ElecSystemDDLForm>  formList=this.elecSystemDDLPOListToVOList(list);
			return formList;
		}

		private List<ElecSystemDDL> findSystemDDLListByCondition(String keyword) {
			String hqlWhere=" and o.keyword=?";
			Object[] params={keyword};
			LinkedHashMap<String,String>  orderby=new LinkedHashMap<String,String>();
			orderby.put("o.ddlCode", "asc");
			List<ElecSystemDDL> list=elecSystemDDLDao.findCollectionByConditionNoPage(ElecSystemDDL.class, hqlWhere, params, orderby);
			return list;
		}

		private List<ElecSystemDDLForm> elecSystemDDLPOListToVOList(
				List<ElecSystemDDL> list) {
			ElecSystemDDLForm elecSystemDDLForm=new ElecSystemDDLForm();
			List<ElecSystemDDLForm> listForm=new LinkedList<ElecSystemDDLForm>();
			for(int i=0;list!=null&&i<list.size();i++){
				ElecSystemDDL elecSystemDDL=list.get(i);
				elecSystemDDLForm=new ElecSystemDDLForm();
				elecSystemDDLForm.setSqlID(elecSystemDDL.getSeqID().toString());
				elecSystemDDLForm.setDdlCode(elecSystemDDL.getDdlCode().toString());
				elecSystemDDLForm.setDdlName(elecSystemDDL.getDdlName());
				elecSystemDDLForm.setKeyword(elecSystemDDL.getKeyword());
				listForm.add(elecSystemDDLForm);
			}
			return listForm;
		}

		@Override
		@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
		public void saveElecSystemDDL(ElecSystemDDLForm elecSystemDDLForm) {
				String keyword=elecSystemDDLForm.getKeywordname();
				String typeflag=elecSystemDDLForm.getTypeflag();
				String[] itemname=elecSystemDDLForm.getItemname();
				if(typeflag!=null&&typeflag.equals("new")){					
						this.saveSystemDDLWithParams(keyword,itemname);
				}else{
					List<ElecSystemDDL> list=findSystemDDLListByCondition(keyword);
					this.elecSystemDDLDao.deleteObjectByCollection(list);
					this.saveSystemDDLWithParams(keyword, itemname);
				}
		}
		
		@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
		private void saveSystemDDLWithParams(String keyword, String[] itemname) {
			List<ElecSystemDDL> list=new ArrayList<ElecSystemDDL>();
			for(int i=0;itemname!=null&&i<itemname.length;i++){
				ElecSystemDDL elecElecSystemDDL=new ElecSystemDDL();
				elecElecSystemDDL.setDdlName(itemname[i]);
				elecElecSystemDDL.setDdlCode(new Integer(i+1));
				elecElecSystemDDL.setKeyword(keyword);
				list.add(elecElecSystemDDL);
			}
			this.elecSystemDDLDao.saveObjectByCollection(list);
		}
		
		
}
