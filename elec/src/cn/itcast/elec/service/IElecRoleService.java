package cn.itcast.elec.service;

import java.util.List;

import cn.itcast.elec.util.XmlObject;
import cn.itcast.elec.web.vo.ElecRoleForm;
import cn.itcast.elec.web.vo.ElecUserForm;

public interface IElecRoleService {
		public static final String SERVICE_NAME="cn.itcast.elec.service.impl.ElecRoleServiceImpl";

		List<XmlObject> readXml();

		List<XmlObject> readEditXml(String roleID);

		List<ElecUserForm> findElecUserListByRoleID(String roleID);

		void saveRole(ElecRoleForm elecRoleForm);

		void saveUserRole(ElecRoleForm elecRoleForm);


}
