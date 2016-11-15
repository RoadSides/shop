package cn.itcast.elec.test;

import java.util.Date;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.Test;

import cn.itcast.elec.domain.ElecText;
import cn.itcast.elec.utils.UUIDUtils;

public class TestHibernate {
		
		@Test
		public void testElecText(){
			Configuration config=new Configuration();
			config.configure();
			SessionFactory sf=config.buildSessionFactory();
			
			Session session=sf.openSession();
			
			session.beginTransaction();
			
			ElecText elecText=new ElecText();
			elecText.setTextID(UUIDUtils.getUUID());
			elecText.setTextDate(new Date());
			elecText.setTextName("测试hibernate名称");
			elecText.setTextRemark("测试hibernate备注");
			
			session.save(elecText);
			
			session.getTransaction().commit();
			
			session.close();
		}
}
