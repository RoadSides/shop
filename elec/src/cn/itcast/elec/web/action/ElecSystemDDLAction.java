package cn.itcast.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.elec.service.IElecSystemDDLService;
import cn.itcast.elec.web.vo.ElecSystemDDLForm;

import com.opensymphony.xwork2.ModelDriven;

public class ElecSystemDDLAction extends BaseAction implements ModelDriven<ElecSystemDDLForm> {
	

	private IElecSystemDDLService elecSystemDDLService ;
	
	private ElecSystemDDLForm elecSystemDDLForm=new ElecSystemDDLForm();
	
	@Resource(name="cn.itcast.elec.service.impl.ElecSystemDDLServiceImpl")
	public void setElecSystemDDLService(IElecSystemDDLService elecSystemDDLService) {
		this.elecSystemDDLService = elecSystemDDLService;
	}

	@Override
	public ElecSystemDDLForm getModel() {
		return elecSystemDDLForm;
	}
	
	public String home(){
		List<ElecSystemDDLForm>  list=this.elecSystemDDLService.findKeyWord();
		request.setAttribute("systemList", list);
		return "home";
	}
	
	public String edit(){
		String keyword=elecSystemDDLForm.getKeyword();
		List<ElecSystemDDLForm> list=this.elecSystemDDLService.findElecSystemDDLListByKeyword(keyword);
		request.setAttribute("systemList", list);
		return "edit";
	}
	
	public String save(){
		this.elecSystemDDLService.saveElecSystemDDL(elecSystemDDLForm);
		return "save";
	}
}
