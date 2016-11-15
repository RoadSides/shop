package cn.itcast.elec.domain;

import java.io.Serializable;
import java.util.Date;

@SuppressWarnings("serial")
public class ElecText implements Serializable{
	 
		private String textID;
		private String textName;
		private Date textDate;
		private String textRemark;//备注
		
		public String getTextID() {
			return textID;
		}
		public void setTextID(String textID) {
			this.textID = textID;
		}
		public Date getTextDate() {
			return textDate;
		}
		public void setTextDate(Date textDate) {
			this.textDate = textDate;
		}
		public String getTextName() {
			return textName;
		}
		public void setTextName(String textName) {
			this.textName = textName;
		}
		public String getTextRemark() {
			return textRemark;
		}
		public void setTextRemark(String textRemark) {
			this.textRemark = textRemark;
		}

}
