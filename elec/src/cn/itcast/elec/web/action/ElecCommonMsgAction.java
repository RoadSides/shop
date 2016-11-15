package cn.itcast.elec.web.action;

import java.util.List;

import javax.annotation.Resource;

import cn.itcast.elec.service.IElecCommonMsgService;
import cn.itcast.elec.web.vo.ElecCommonMsgForm;

import com.opensymphony.xwork2.ModelDriven;

public class ElecCommonMsgAction extends BaseAction implements ModelDriven<ElecCommonMsgForm>{
		
		private ElecCommonMsgForm elecCommonMsgForm=new ElecCommonMsgForm();
		private IElecCommonMsgService elecCommonMsgService;

		public String home(){
			List<ElecCommonMsgForm> list=elecCommonMsgService.findElecCommonMsgList();
			request.setAttribute("commonList", list);
			return "home";
		}
		
		@Resource(name="cn.itcast.elec.service.impl.ElecCommonMsgServiceImpl")
		public void setElecCommonMsgService(IElecCommonMsgService elecCommonMsgService) {
			this.elecCommonMsgService = elecCommonMsgService;
		}

		@Override
		public ElecCommonMsgForm getModel() {

			return elecCommonMsgForm;
		}
		
		public String save(){
			this.elecCommonMsgService.save(elecCommonMsgForm);
			return "save";
		}
}
