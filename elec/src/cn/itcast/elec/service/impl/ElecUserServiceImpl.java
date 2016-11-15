package cn.itcast.elec.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecSystemDDLDao;
import cn.itcast.elec.dao.IElecUserDao;
import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.util.MD5keyBean;
import cn.itcast.elec.util.StringHelper;
import cn.itcast.elec.web.vo.ElecUserForm;

@Transactional(readOnly=true)
@Service(value=IElecUserService.SERVICE_NAME)
public class ElecUserServiceImpl implements IElecUserService {
		
		@Resource(name=IElecUserDao.SERVICE_NAME)
		private IElecUserDao elecUserDao;

		@Resource(name=IElecSystemDDLDao.SERVICE_NAME)
		private IElecSystemDDLDao  elecSystemDDLDao;
		
		@Override
		public List<ElecUserForm> findElecUserList(ElecUserForm elecUserForm) {
			String hqlWhere="";
			List<String> paramsList=new ArrayList<String>();
			if(elecUserForm!=null&&elecUserForm.getUserName()!=null&&elecUserForm.getUserName()!=""){
				hqlWhere +=" and o.userName like ?";
				paramsList.add("%"+elecUserForm.getUserName()+"%");			
			}		
			Object[] params=paramsList.toArray();
			LinkedHashMap<String,String> orderby=new LinkedHashMap<String, String>();
			orderby.put("o.onDutyDate", "desc");	
			List<ElecUser> list=elecUserDao.findCollectionByConditionNoPage(ElecUser.class, hqlWhere, params, orderby);
			List<ElecUserForm> formList=this.elecUserPOListToVOList(list);
			return formList;
		}

		private List<ElecUserForm> elecUserPOListToVOList(List<ElecUser> list) {
			ElecUserForm elecUserForm=null;
			List<ElecUserForm>  formList=new ArrayList<ElecUserForm>();
			for(int i=0;list!=null&&i<list.size();i++){
					ElecUser elecUser=list.get(i);
					elecUserForm=new ElecUserForm();
					elecUserForm.setUserID(elecUser.getUserID());
					elecUserForm.setLogonName(elecUser.getLogonName());
					elecUserForm.setUserName(elecUser.getUserName());
					if(elecUser.getSexID()!=null&&!elecUser.getSexID().equals("")){
						elecUserForm.setSexID(elecUser.getSexID()!=null?elecSystemDDLDao.findDDLName(elecUser.getSexID(),"性别"):"");
					}else{
						elecUserForm.setSexID("");
					}
					elecUserForm.setContactTel(elecUser.getContactTel());
					if(elecUser.getIsDuty()!=null&&!elecUser.getIsDuty().equals("")){
							elecUserForm.setIsDuty(elecUser.getIsDuty()!=null?elecSystemDDLDao.findDDLName(elecUser.getIsDuty(), "是否在职"):"");
					}else{
						elecUserForm.setIsDuty("");
					}
					formList.add(elecUserForm);
			}
			return formList;
		}

		@Override
		@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
		public void saveElecUser(ElecUserForm elecUserForm) {
				ElecUser elecUser=this.elecUserVOToPO(elecUserForm);
				if(elecUser!=null&&elecUser.getUserID()!=null){
					elecUserDao.update(elecUser);
				}
				else
				{
					elecUserDao.save(elecUser);	
				}
		}

		private ElecUser elecUserVOToPO(ElecUserForm elecUserForm) {
			ElecUser elecUser=new ElecUser();
			if(elecUserForm!=null){
				if(elecUserForm.getUserID()!=null&&!elecUserForm.getUserID().equals("")){
					elecUser.setUserID(elecUserForm.getUserID());
					if(elecUserForm.getOffDutyDate()!=null&&!elecUserForm.getOffDutyDate().equals("")){
						elecUser.setOffDutyDate(StringHelper.stringConvertDate(elecUserForm.getOffDutyDate()));
					}
				}
				elecUser.setJctID(elecUserForm.getJctID());
				elecUser.setSexID(elecUserForm.getSexID());
				elecUser.setUserName(elecUserForm.getUserName());
				elecUser.setLogonName(elecUserForm.getLogonName());
				
				String md5flag=elecUserForm.getMd5flag();
				String password=elecUserForm.getLogonPwd();
				String md5password="";
			
				if(md5flag!=null&&md5flag.equals("1")){
					md5password=password;
				}else{
					MD5keyBean  md5=new MD5keyBean();
					md5password=md5.getkeyBeanofStr(password);
				}
				
				
				elecUser.setLogonPwd(md5password);
				elecUser.setEmail(elecUserForm.getEmail());
				elecUser.setContactTel(elecUserForm.getContactTel());
				elecUser.setAddress(elecUserForm.getAddress());
				elecUser.setIsDuty(elecUserForm.getIsDuty());
				elecUser.setRemark(elecUserForm.getRemark());
				if(elecUserForm.getBirthday()!=null&&!elecUserForm.getBirthday().equals("")){
					elecUser.setBirthday(StringHelper.stringConvertDate(elecUserForm.getBirthday()));
				}
				if(elecUserForm.getOnDutyDate()!=null&&!elecUserForm.getOnDutyDate().equals("")){
					elecUser.setOnDutyDate(StringHelper.stringConvertDate(elecUserForm.getOnDutyDate()));
				}
				elecUser.setMobile(elecUserForm.getMobile());
			}
			return elecUser;
		}

		@Override
		public ElecUserForm findElecUser(ElecUserForm elecUserForm) {
			String userID=elecUserForm.getUserID();
			ElecUser elecUser=this.elecUserDao.findObjectById(ElecUser.class,userID);
			elecUserForm=this.elecUserPOToVO(elecUser,elecUserForm);
			return elecUserForm;
		}

		private ElecUserForm elecUserPOToVO(ElecUser elecUser,ElecUserForm elecUserForm) {
			if(elecUser!=null){
				elecUserForm.setUserID(elecUser.getUserID());
				elecUserForm.setOffDutyDate(String.valueOf(elecUser.getOffDutyDate()!=null&&!elecUser.getOffDutyDate().equals("")?elecUser.getOffDutyDate():""));
				elecUserForm.setJctID(elecUser.getJctID());
				elecUserForm.setSexID(elecUser.getSexID());
				elecUserForm.setUserName(elecUser.getUserName());
				elecUserForm.setLogonName(elecUser.getLogonName());
				elecUserForm.setEmail(elecUser.getEmail());
				elecUserForm.setContactTel(elecUser.getContactTel());
				elecUserForm.setAddress(elecUser.getAddress());
				elecUserForm.setIsDuty(elecUser.getIsDuty());
				elecUserForm.setRemark(elecUser.getRemark());
				elecUserForm.setLogonPwd(elecUser.getLogonPwd());
				elecUserForm.setBirthday(String.valueOf(elecUser.getBirthday()!=null&&!elecUser.getBirthday().equals("")?elecUser.getBirthday():""));
				elecUserForm.setOnDutyDate(String.valueOf(elecUser.getOnDutyDate()!=null&&!elecUser.getOnDutyDate().equals("")?elecUser.getOnDutyDate():""));
				elecUserForm.setMobile(elecUser.getMobile());
			}
			return elecUserForm;
		}

		@Override
		@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
		public void deleteElecUser(ElecUserForm elecUserForm) {
				Serializable[] arr={elecUserForm.getUserID()};
				
				this.elecUserDao.deleteObjectByIds(ElecUser.class,arr);
				
		}

		@Override
		public String checkLogonName(String logonName) {
			String hqlWhere ="and o.logonName =?";
			Object[]  params={logonName};
			List<ElecUser>  list=elecUserDao.findCollectionByConditionNoPage(ElecUser.class, hqlWhere, params, null);
			String checkflag;
			if(list!=null&&list.size()>0){
				checkflag="1";
			}else{
				checkflag="2";
			}
			return checkflag;
		}

		@Override
		public ElecUser findElecUserByLogonName(String name) {
			String hqlWhere=" and o.logonName=?";
			Object[] params={name};
			List<ElecUser>  list=elecUserDao.findCollectionByConditionNoPage(ElecUser.class, hqlWhere, params, null);
			ElecUser elecUser=null;
			if(list!=null&&list.size()>0){
				elecUser=list.get(0);
			}
			return elecUser;
		}
		
		
}	
