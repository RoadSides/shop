package cn.itcast.elec.service.impl;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.apache.struts2.ServletActionContext;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import cn.itcast.elec.dao.IElecRolePopedomDao;
import cn.itcast.elec.dao.IElecUserRoleDao;
import cn.itcast.elec.domain.ElecRolePopedom;
import cn.itcast.elec.domain.ElecUserRole;
import cn.itcast.elec.service.IElecRoleService;
import cn.itcast.elec.util.XmlObject;
import cn.itcast.elec.web.vo.ElecRoleForm;
import cn.itcast.elec.web.vo.ElecUserForm;

@Transactional(readOnly=true)
@Service(IElecRoleService.SERVICE_NAME)
public class ElecRoleServiceImpl implements IElecRoleService {
		
		@Resource(name=IElecUserRoleDao.SERVICE_NAME)
		private IElecUserRoleDao elecUserRoleDao;
		
		@Resource(name=IElecRolePopedomDao.SERVICE_NAME)
		private IElecRolePopedomDao elecRolePopedomDao;

		@Override
		public List<XmlObject> readXml() { 
			
			ServletContext servletContext=ServletActionContext.getServletContext();
			String path=servletContext.getRealPath("/WEB-INF/classes/FUnction.xml");
			
			File file=new File(path);
			List<XmlObject>  list=new ArrayList<XmlObject>();
			try {	
				SAXReader  reader=new SAXReader();
				Document document=reader.read(file);
				Element  element=document.getRootElement();
				XmlObject xmlObject=null;
				for(Iterator<Element>  iter=element.elementIterator("Function");iter.hasNext();){
					Element  xmlElement=iter.next();
					xmlObject=new XmlObject();
					xmlObject.setCode(xmlElement.elementText("FunctionCode"));
					xmlObject.setName(xmlElement.elementText("FunctionName"));
					xmlObject.setParentCode(xmlElement.elementText("ParentCode"));
					xmlObject.setParentName(xmlElement.elementText("ParentName"));
					list.add(xmlObject);
				}
			} catch (DocumentException e) {
				throw new RuntimeException(e);
			}
			return list;
		}

		@Override
		public List<XmlObject> readEditXml(String roleID) {
		    ElecRolePopedom elecRolePopedom=	this.elecRolePopedomDao.findObjectById(ElecRolePopedom.class, roleID);
		    String popedom="";
		    if(elecRolePopedom!=null){
		    	popedom=elecRolePopedom.getPopedomcode();
		    }
		    List<XmlObject>  list=this.readXmlByPopedom(popedom);
			return list;
		}

		private List<XmlObject> readXmlByPopedom(String popedom) {
			List<XmlObject>  list=new ArrayList<XmlObject>();
			List<XmlObject>  xmlList=this.readXml();
			for(XmlObject xmlObject:xmlList){
				if(popedom.contains(xmlObject.getCode())){
						xmlObject.setFlag("1");
				}else{
						xmlObject.setFlag("0");
				}
				list.add(xmlObject);
			}
			return list;
		}

		@Override
		public List<ElecUserForm> findElecUserListByRoleID(String roleID) {
			List<Object[]>  list=elecUserRoleDao.findElecUserListByRoleID(roleID);
			List<ElecUserForm>  formList=this.elecUserRoleObjectListToVOList(list);
			return formList;
		}

		private List<ElecUserForm> elecUserRoleObjectListToVOList(
				List<Object[]> list) {
			List<ElecUserForm>  listForm=new ArrayList<ElecUserForm>();
			ElecUserForm  elecUserForm=null;
			for(int i=0;list!=null&&i<list.size();i++){
				Object[] objects=list.get(i);
				elecUserForm=new ElecUserForm();
				elecUserForm.setFlag(objects[0].toString());
				elecUserForm.setUserID(objects[1].toString());
				elecUserForm.setUserName(objects[2].toString());
				elecUserForm.setLogonName(objects[3].toString());
				listForm.add(elecUserForm);
			}
			return listForm;
		}

		@Override
		@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
		public void saveRole(ElecRoleForm elecRoleForm) {
				this.saveRolePopedom(elecRoleForm);
		}

		
		private void saveRolePopedom(ElecRoleForm elecRoleForm) {
				String roleid=elecRoleForm.getRoleid();
				
				String[] selectoper=elecRoleForm.getSelectoper();
				
				StringBuffer popedom=new StringBuffer("");
				if(selectoper!=null){
					for(int i=0;i<selectoper.length;i++){
						popedom.append(selectoper[i]);
					}
				}
				
				ElecRolePopedom elecRolePopedom=elecRolePopedomDao.findObjectById(ElecRolePopedom.class, roleid);
				
				if(elecRolePopedom!=null){
					elecRolePopedom.setPopedomcode(popedom.toString());
					elecRolePopedomDao.update(elecRolePopedom);
				}else{
					elecRolePopedom=new ElecRolePopedom();
					elecRolePopedom.setRoleID(roleid);
					elecRolePopedom.setPopedomcode(popedom.toString());
					elecRolePopedomDao.save(elecRolePopedom);
				}
		}

		@Override
		@Transactional(isolation=Isolation.DEFAULT,propagation=Propagation.REQUIRED,readOnly=false)
		public void saveUserRole(ElecRoleForm elecRoleForm) {
				String roleid=elecRoleForm.getRoleid();
				
				String[] selectuser=elecRoleForm.getSelectuser();
				
				String hqlWhere="and o.roleID=?";
				
				Object[] params={roleid};
				
				List<ElecUserRole>  list=elecUserRoleDao.findCollectionByConditionNoPage(ElecUserRole.class, hqlWhere, params, null);
				
				elecUserRoleDao.deleteObjectByCollection(list);
				List<ElecUserRole>  userRoleList=new ArrayList<ElecUserRole>();
				 if(selectuser!=null){
					 for(int i=0;i<selectuser.length;i++){
						 String userID=selectuser[i];
						 ElecUserRole  elecUserRole=new ElecUserRole();
						 elecUserRole.setUserID(userID);
						 elecUserRole.setRoleID(roleid);
						 userRoleList.add(elecUserRole);
					 }
					 this.elecUserRoleDao.saveObjectByCollection(userRoleList);
				 }
		}
}
