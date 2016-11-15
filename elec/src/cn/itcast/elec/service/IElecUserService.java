package cn.itcast.elec.service;

import java.util.List;

import cn.itcast.elec.web.vo.ElecUserForm;

public interface IElecUserService {
		public final static String SERVICE_NAME="cn.itcast.elec.service.impl.ElecUserServiceImpl";

		List<ElecUserForm> findElecUserList(ElecUserForm elecUserForm);

		void saveElecUser(ElecUserForm elecUserForm);

		ElecUserForm findElecUser(ElecUserForm elecUserForm);

		void deleteElecUser(ElecUserForm elecUserForm);

		String checkLogonName(String logonName);
}
