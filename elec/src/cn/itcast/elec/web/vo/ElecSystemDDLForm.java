package cn.itcast.elec.web.vo;

public class ElecSystemDDLForm {
		
		private String sqlID;
		private String keyword;
		private String ddlCode;
		private String ddlName;
		
		private String keywordname;
		private String typeflag;
		private String[] itemname;
		
		
		
		public String getKeywordname() {
			return keywordname;
		}
		public void setKeywordname(String keywordname) {
			this.keywordname = keywordname;
		}
		public String getTypeflag() {
			return typeflag;
		}
		public void setTypeflag(String typeflag) {
			this.typeflag = typeflag;
		}
		public String[] getItemname() {
			return itemname;
		}
		public void setItemname(String[] itemname) {
			this.itemname = itemname;
		}
		public String getSqlID() {
			return sqlID;
		}
		public void setSqlID(String sqlID) {
			this.sqlID = sqlID;
		}
		public String getKeyword() {
			return keyword;
		}
		public void setKeyword(String keyword) {
			this.keyword = keyword;
		}
		public String getDdlCode() {
			return ddlCode;
		}
		public void setDdlCode(String ddlCode) {
			this.ddlCode = ddlCode;
		}
		public String getDdlName() {
			return ddlName;
		}
		public void setDdlName(String ddlName) {
			this.ddlName = ddlName;
		}

}
