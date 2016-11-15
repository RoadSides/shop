package cn.itcast.elec.test;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.itcast.elec.dao.IElecTextDao;
import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.utils.UUIDUtils;

public class TestDao {

		@SuppressWarnings("resource")
		public void saveElecText(){
			ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
			IElecTextDao elecTextDao=(IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
			
			ElecText elecText=new ElecText();
			elecText.setTextDate(new Date());
			elecText.setTextID(UUIDUtils.getUUID());
			elecText.setTextName("张三");
			elecText.setTextName("测试DAO名称");
			elecText.setTextRemark("测试DAO备注");
			
			elecTextDao.save(elecText);
		}
		
		
		public void update(){
				ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
				IElecTextDao elecTextDao=(IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
				
				ElecText elecText=new ElecText();
				elecText.setTextID("40281b815823faeb015823faec480000");
				elecText.setTextName("张三丰");
				elecText.setTextDate(new Date());
				elecText.setTextRemark("老年人");
				
				elecTextDao.update(elecText);
		}
		
		
		public void findObjectById(){
			 	ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
			 	IElecTextDao elecTextDao=(IElecTextDao) ac.getBean(IElecTextDao.SERVICE_NAME);
			 	
			 	Serializable id="8a8b8d18581ffdb501581ffdb7710000";
			 	ElecText elecText=elecTextDao.findObjectById(ElecText.class,id);
			 	System.out.println(elecText.getTextName());
		}
		
		
		public void deleteObjectByIds(){
				ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
				IElecTextDao elecTextDao=(IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
				
				Serializable[] ids={"8a8b8d185823d609015823d60ad70000","8a8b8d185823d6fe015823d6ff130000"};
				elecTextDao.deleteObjectByIds(ElecText.class,ids);				
		}	
		
		@Test
		public void deleteObjectByCollection(){
			ApplicationContext ac=new ClassPathXmlApplicationContext("applicationContext.xml");
			IElecTextDao elecTextDao=(IElecTextDao)ac.getBean(IElecTextDao.SERVICE_NAME);
			
			List<ElecText>  list=new ArrayList<ElecText>();
			ElecText elecText1=new ElecText();
			elecText1.setTextID("8a8b8d18581ffdb501581ffdb7710000");
			ElecText elecText2=new ElecText();
			elecText2.setTextID("8a8b8d185823dc43015823dc45030000");
			list.add(elecText1);
			list.add(elecText2);
			
			elecTextDao.deleteObjectByCollection(list);
		}
}
