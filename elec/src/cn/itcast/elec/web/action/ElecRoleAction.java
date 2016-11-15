package cn.itcast.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.elec.service.IElecRoleService;
import cn.itcast.elec.service.IElecSystemDDLService;
import cn.itcast.elec.util.XmlObject;
import cn.itcast.elec.web.vo.ElecRoleForm;
import cn.itcast.elec.web.vo.ElecSystemDDLForm;
import cn.itcast.elec.web.vo.ElecUserForm;

import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecRoleAction extends BaseAction  implements ModelDriven<ElecRoleForm>{

	private ElecRoleForm elecRoleForm=new ElecRoleForm();
	
	private IElecRoleService elecRoleService;
	
	private IElecSystemDDLService elecSystemDDLService;
	
	@Resource(name=IElecSystemDDLService.SERVICE_NAME)
	public void setElecSystemDDLService(IElecSystemDDLService elecSystemDDLService) {
		this.elecSystemDDLService = elecSystemDDLService;
	}

	@Resource(name=IElecRoleService.SERVICE_NAME)
	public void setElecRoleService(IElecRoleService elecRoleService) {
		this.elecRoleService = elecRoleService;
	}
	
	@Override
	public ElecRoleForm getModel() {
		return elecRoleForm;
	}
	
	public String home(){
		List<ElecSystemDDLForm> systemList=elecSystemDDLService.findElecSystemDDLListByKeyword("角色类型");
		request.setAttribute("systemList", systemList);
		List<XmlObject>  xmlList=elecRoleService.readXml();
		request.setAttribute("xmlList", xmlList);
		return "home";
	}
		
	
	public String edit(){
		String roleID=elecRoleForm.getRole();
		List<XmlObject>  xmlList=elecRoleService.readEditXml(roleID);
		request.setAttribute("xmlList", xmlList);
		List<ElecUserForm>  userList=elecRoleService.findElecUserListByRoleID(roleID);
		request.setAttribute("userList", userList);
		return "edit";
	}
	
	public String save(){
		this.elecRoleService.saveRole(elecRoleForm);
		this.elecRoleService.saveUserRole(elecRoleForm);
		return "save";
	}
}
