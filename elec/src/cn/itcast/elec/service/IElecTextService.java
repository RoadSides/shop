package cn.itcast.elec.service;

import java.util.List;

import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.web.vo.ElecTextForm;

public interface IElecTextService {
	public final static String  SERVICE_NAME="cn.itcast.elec.service.impl.ElecTextServiceImpl";
	
	public void saveElectext(ElecText electext);
	
	public List<ElecText> findCollectionByConditionNoPage(ElecTextForm elecTextForm);
}
