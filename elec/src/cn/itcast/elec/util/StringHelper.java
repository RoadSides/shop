package cn.itcast.elec.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class StringHelper {

		/**
		 * 将string的日期型时装换成日期类型
		 *
		 */
		public static Date stringConvertDate(String textDate){
			SimpleDateFormat format=new SimpleDateFormat("yyyy-MM-dd");
			Date d=null;
			try {
				if(textDate!=null&&textDate.trim()!=""){
					d=format.parse(textDate);		
					return d;
				}else return null;
			} catch (ParseException e) {
				throw new RuntimeException(e);
			}
		}
}
