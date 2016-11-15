package cn.itcast.elec.test;

import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.service.IElecTextService;
import cn.itcast.elec.utils.UUIDUtils;
import cn.itcast.elec.web.vo.ElecTextForm;

public class TestService {
		
		
		public void saveElecText(){
			ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
			IElecTextService IelecTextService=(IElecTextService)ac.getBean(IElecTextService.SERVICE_NAME);
			
			ElecText elecText=new ElecText();
			elecText.setTextDate(new Date());
			elecText.setTextName("测试Service的名字");
			elecText.setTextID(UUIDUtils.getUUID());
			elecText.setTextRemark("测试service的备注");
			
			IelecTextService.saveElectext(elecText);
		}
		
		@Test
		public void findCollection(){
			ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
			IElecTextService elecTextService=(IElecTextService)ac.getBean(IElecTextService.SERVICE_NAME);
			
			ElecTextForm elecTextForm=new ElecTextForm();
			elecTextForm.setTextName("这");
			elecTextForm.setTextRemark("这");
			List<ElecText> list=elecTextService.findCollectionByConditionNoPage(elecTextForm);

			for(int i=0;list!=null&&i<list.size();i++){
				ElecText elecText=list.get(i);
				System.out.println(elecText.getTextName()+"        "+elecText.getTextRemark());
			}
		}
}
