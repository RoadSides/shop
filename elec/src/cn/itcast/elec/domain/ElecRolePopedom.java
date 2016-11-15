package cn.itcast.elec.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ElecRolePopedom implements Serializable{
		private String roleID;
		private String popedomcode;
		private String remark;
		
		public String getRoleID() {
			return roleID;
		}
		public void setRoleID(String roleID) {
			this.roleID = roleID;
		}
		public String getPopedomcode() {
			return popedomcode;
		}
		public void setPopedomcode(String popedomcode) {
			this.popedomcode = popedomcode;
		}
		public String getRemark() {
			return remark;
		}
		public void setRemark(String remark) {
			this.remark = remark;
		}
}
