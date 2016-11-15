package cn.itcast.elec.web.action;

import java.util.Date;

import javax.annotation.Resource;

import org.springframework.context.annotation.Scope;

import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;
import cn.itcast.elec.util.StringHelper;
import cn.itcast.elec.web.vo.ElecTextForm;

import com.opensymphony.xwork2.ModelDriven;

@Scope("property")
public class ElecTextAction extends BaseAction  implements ModelDriven<ElecTextForm>{

	private ElecTextForm elecTextForm=new ElecTextForm();
	private IElecTextService elecTextService;
	
	public IElecTextService getElecTextService() {
		return elecTextService;
	}
	
	@Resource(name="cn.itcast.elec.service.impl.ElecTextServiceImpl")
	public void setElecTextService(IElecTextService elecTextService) {
		this.elecTextService = elecTextService;
	}

	@Override
	public ElecTextForm getModel() {
		return elecTextForm;
	}
		
	public String save(){
		ElecText elecText=new ElecText();
		elecText.setTextName(elecTextForm.getTextName());
		Date time=StringHelper.stringConvertDate(elecTextForm.getTextDate());
		elecText.setTextDate(time);
		elecText.setTextRemark(elecTextForm.getTextRemark()); 
		elecTextService.saveElectext(elecText);
		return "save";
	}
}
