package cn.itcast.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.elec.domain.ElecUser;
import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.service.IElecUserService;
import cn.itcast.elec.util.MD5keyBean;
import cn.itcast.elec.web.vo.ElecCommonMsgForm;
import cn.itcast.elec.web.vo.ElecMenuForm;
import cn.itcast.elec.web.vo.ElecTextForm;

import com.opensymphony.xwork2.ModelDriven;

public class ElecMenuAction extends BaseAction  implements ModelDriven<ElecMenuForm>{

	private ElecMenuForm elecMenuForm=new ElecMenuForm();
	private IElecUserService elecUserService;
	
	@Resource(name=IElecUserService.SERVICE_NAME)
	public void setElecUserService(IElecUserService elecUserService) {
		this.elecUserService = elecUserService;
	}

	@Resource(name="cn.itcast.elec.service.impl.ElecCommonMsgServiceImpl")
	private IElecCommonMsgService iElecCommonMsgService;
	
	public String home(){
		String name=elecMenuForm.getName();
		String password=elecMenuForm.getPassword();
		if(password!=null){
			
			MD5keyBean md5=new MD5keyBean();
			String md5String=md5.getkeyBeanofStr(password);

			ElecUser elecUser=elecUserService.findElecUserByLogonName(name);
			if(elecUser==null){
				this.addFieldError("error", "您当前输入的登录名不存在");	
				return "error";
			}
			if(elecUser.getLogonPwd().equals(md5String)){
				request.getSession().setAttribute("globle_user", elecUser);
				return "home";
			}
			else{
				this.addFieldError("error", "你输入的密码错误");
				return "error";
			}
		}
		else 
		{
			this.addFieldError("error","当前密码为空");
			return "error";
		}
	}
	
	@Override
	public ElecMenuForm getModel() {
		return elecMenuForm;
	}
		
	public String title(){
		return "title";
	}
	
	public String left(){
		return "left";
	}
	
	public String change1(){
		return "change1";
	}
	
	public String loading(){
		return "loading";
	}
	
	public String alermXZ(){
		return "alermXZ";
	}
	
	public String alermJX(){
		return "alermJX";
	}
	
	public String alermSB(){
		List<ElecCommonMsgForm> list= this.iElecCommonMsgService.findElecCommonMsgListByCurrent();
		request.setAttribute("commonList", list);
		return "alermSB";
	}
	
	public String alermYS(){
		return "alermYS";
	}
	
	public String alermZD(){
		List<ElecCommonMsgForm> list= this.iElecCommonMsgService.findElecCommonMsgListByCurrent();
		request.setAttribute("commonList", list);
		return "alermZD";
	}
	
}
