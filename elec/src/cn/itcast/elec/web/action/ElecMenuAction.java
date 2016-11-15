package cn.itcast.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.web.vo.ElecCommonMsgForm;
import cn.itcast.elec.web.vo.ElecTextForm;

import com.opensymphony.xwork2.ModelDriven;

public class ElecMenuAction extends BaseAction  implements ModelDriven<ElecTextForm>{

	private ElecTextForm elecTextForm=new ElecTextForm();
	
	@Resource(name="cn.itcast.elec.service.impl.ElecCommonMsgServiceImpl")
	private IElecCommonMsgService iElecCommonMsgService;
	
	public String home(){
		return "home";
	}
	
	@Override
	public ElecTextForm getModel() {
		return elecTextForm;
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
