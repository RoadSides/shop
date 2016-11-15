package cn.itcast.elec.web.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.annotation.Resource;
import cn.itcast.elec.service.IElecSystemDDLService;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.web.vo.ElecSystemDDLForm;
import cn.itcast.elec.web.vo.ElecUserForm;
import com.opensymphony.xwork2.ModelDriven;

@SuppressWarnings("serial")
public class ElecUserAction extends BaseAction implements ModelDriven<ElecUserForm> {

	private ElecUserForm elecUserForm=new ElecUserForm();
	
	private IElecUserService elecUserService;

	private IElecSystemDDLService elecSystemDDLService;


	@Resource(name=IElecSystemDDLService.SERVICE_NAME)
	public void setElecSystemDDLService(IElecSystemDDLService elecSystemDDLService) {
		this.elecSystemDDLService = elecSystemDDLService;
	}

	@Resource(name=IElecUserService.SERVICE_NAME)
	public void setElecUserService(IElecUserService elecUserService) {
		this.elecUserService = elecUserService;
	}

	@Override
	public ElecUserForm getModel() {
		return elecUserForm;
	}
		
	public String home(){
		List<ElecUserForm>  list=this.elecUserService.findElecUserList(elecUserForm);
		request.setAttribute("userList", list);
		return "home";
	}
	
	public String add(){
		this.initSystemDDL();
		return "add";
	}
	
	private void initSystemDDL() {
			List<ElecSystemDDLForm>  sexList=elecSystemDDLService.findElecSystemDDLListByKeyword("性别");
			List<ElecSystemDDLForm>  jctList=elecSystemDDLService.findElecSystemDDLListByKeyword("所属单位");
			List<ElecSystemDDLForm>  isdutyList=elecSystemDDLService.findElecSystemDDLListByKeyword("是否在职");
			request.setAttribute("sexList", sexList);
			request.setAttribute("jctList", jctList);
			request.setAttribute("isdutyList", isdutyList);
	}

	public String edit(){
		elecUserForm=elecUserService.findElecUser(elecUserForm);
//		if(elecUserForm!=null)
//			ActionContext.getContext().getValueStack().push(elecUserForm);
		this.initSystemDDL();
		String viewflag=elecUserForm.getViewflag();
		request.setAttribute("viewflag", viewflag);
		return "edit";
	}
	
	public String save(){
		this.elecUserService.saveElecUser(elecUserForm);
		return "list";
	}
	
	public String delete(){
		this.elecUserService.deleteElecUser(elecUserForm);
		return "list";
	}
	
	public String checkLogonName(){
		response.setContentType("text/html;charset=UTF-8");
		
		String checkflag=this.elecUserService.checkLogonName(elecUserForm.getLogonName());
		PrintWriter out;
		try {
			out = response.getWriter();		
			out.print(checkflag);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
		return NONE;
	}
}
