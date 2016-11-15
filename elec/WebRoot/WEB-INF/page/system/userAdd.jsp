<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s"  uri="/struts-tags" %>
<html>
  <head>
   <title>添加用户</title>
   <LINK href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet">
   <script language="javascript" src="${pageContext.request.contextPath }/script/function.js"></script>
   <script type="text/javascript" language="JavaScript" src="${pageContext.request.contextPath }/script/calendar.js" charset="gb2312"></script>
   <script type="text/javascript" src="${pageContext.request.contextPath }/script/validate.js"></script>
<Script language="javascript">

	function check_null(){
	    
	    var theForm=document.Form1;
	    
	    if(Trim(theForm.logonName.value)=="")
		{
			alert("登录名不能为空");
			theForm.logonName.focus();
			return false;
		}
	    if(Trim(theForm.userName.value)=="")
		{
			alert("用户姓名不能为空");
			theForm.userName.focus();
			return false;
		}
	    if(theForm.jctID.value=="")
		{
			alert("请选择所属单位");
			theForm.jctId.focus();
			return false;
		}
	
        if(theForm.logonPwd.value!=theForm.passwordconfirm.value){
		
		  alert("两次输入密码不一致，请重新输入");
		  return;
		}
		if(checkNull(theForm.contactTel)){
         if(!checkPhone(theForm.contactTel.value))
		  {
			alert("请输入正确的电话号码");
			theForm.contactTel.focus();
			return false;
		  }
		}
		
	    if(checkNull(theForm.mobile)){
         if(!checkMobilPhone(theForm.mobile.value))
		  {
			alert("请输入正确的手机号码");
			theForm.mobile.focus();
			return false;
		  }
		}
		
	   if(checkNull(theForm.email))	{
         if(!checkEmail(theForm.email.value))
		 {
			alert("请输入正确的EMail");
			theForm.email.focus();
			return false;
		 }
	   }
		
	   if(theForm.remark.value.length>250){
     
        	alert("备注字符长度不能超过250");
			theForm.empRemark.focus();
			return false; 
        }
		 
		 document.Form1.action="${pageContext.request.contextPath}/system/elecUserAction_save.action";
		 document.Form1.submit();	 		 
		 refreshOpener();
	}
	
	
	function createXMLHttpRequest() {
	var xmlHttpReq=null;
	if (window.XMLHttpRequest) {//Mozilla 浏览器
		xmlHttpReq = new XMLHttpRequest();
	}else {
		if (window.ActiveXObject) {//IE 浏览器
			try {
				xmlHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
			}
			catch (e) {
				try {//IE 浏览器
					xmlHttpReq = new ActiveXObject("Msxml2.XMLHTTP");
				}
				catch (e) {
				}
			}
		}
	}
	return xmlHttpReq;
}

	function checkLogonName(){
			var logonName=document.getElementById("logonName").value;
			var xmlHttp=createXMLHttpRequest();
			xmlHttp.onreadystatechange=function(){
					if(xmlHttp.readyState==4){
						if(xmlHttp.status==200){
								var data=xmlHttp.responseText;
								if(data==1){
									alert("当前输入的登录名已被注册");
									document.getElementById("logonName").value="";
									document.getElementById("logonName").focus();
								}
						}
					}
			}
			xmlHttp.open("post","${pageContext.request.contextPath}/system/elecUserAction_checkLogonName.action",true);
			xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
			xmlHttp.send("logonName="+logonName);
	} 
   </script>
  </head>
  
 <body>
 
  <form name="Form1" method="post">
 <br>
    <table cellSpacing="1" cellPadding="5" width="580" align="center" bgColor="#eeeeee" style="border:1px solid #8ba7e3" border="0">
    <tr>
		<td class="ta_01" align="center" colSpan="4" background="${pageContext.request.contextPath }/images/b-info.gif">
			 <font face="宋体" size="2"><strong>添加用户</strong></font>
		</td>
    </tr>
     <tr>
         <td align="center" bgColor="#f5fafe" class="ta_01">登&nbsp;&nbsp;录&nbsp;&nbsp;名：<font color="#FF0000">*</font></td>
         <td class="ta_01" bgColor="#ffffff"><input name="logonName" type="text" maxlength="25" id="logonName" size="20"  onblur="checkLogonName()"/>
          </td>
         <td width="18%" align="center" bgColor="#f5fafe" class="ta_01">用户姓名：<font color="#FF0000">*</font></td>
         	<td class="ta_01" bgColor="#ffffff"><input name="userName" type="text" maxlength="25" id="userName" size="20"> 
          </td>
    </tr>
<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别：</td>
<td class="ta_01" bgColor="#ffffff">
<select name="sexID" style="width:155px">
<option value="">输入性别</option>
		<s:iterator  value="#request.sexList"  var="sexList">
				<option  value="<s:property value="#sexList.ddlCode"/>"/><s:property  value="#sexList.ddlName"/></option>
		</s:iterator>
</select></td>

<td align="center" bgColor="#f5fafe" class="ta_01">所属单位：<font color="#FF0000">*</font></td>
<td class="ta_01" bgColor="#ffffff">
<select name="jctID" style="width:155px">
<option value="">选择城市</option>
	<s:iterator value="#request.jctList"  var="jctList">
		<option  value="<s:property  value="#jctList.ddlCode"/>"/><s:property  value="#jctList.ddlName"/></option>
	</s:iterator>
</select></td>

</tr>
<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">密&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;码：</td>
<td class="ta_01" bgColor="#ffffff"><input name="logonPwd" type="password" maxlength="25"  size=22>
</td>
<td align="center" bgColor="#f5fafe" class="ta_01">确认密码：</td>
<td class="ta_01" bgColor="#ffffff"><input name="passwordconfirm" type="password" maxlength="25"  size=22>
</td>
</tr>

<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">出生日期：</td>
<td class="ta_01" bgColor="#ffffff"><input name="birthday" type="text" maxlength="50"  size="20" onclick="JavaScript:calendar(document.Form1.birthday)" >
</td>
<td align="center" bgColor="#f5fafe" class="ta_01">联系地址：</td>
<td class="ta_01" bgColor="#ffffff"><input name="address" type="text" maxlength="50"  size="20">
</td>
</tr>

<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">联系电话：</td>
<td class="ta_01" bgColor="#ffffff"><input name="contactTel" type="text" maxlength="25"  size="20"></td>
<td align="center" bgColor="#f5fafe" class="ta_01">手&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;机：</td>
<td class="ta_01" bgColor="#ffffff">
<input name="mobile" type="text" maxlength="25"  size="20">
</td>
</tr>

<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">电子邮箱：</td>
<td class="ta_01" bgColor="#ffffff"><input name="email" type="text" maxlength="50"  size="20">
</td>
<td align="center" bgColor="#f5fafe" class="ta_01">是否在职：</td>
<td class="ta_01" bgColor="#ffffff">
<select name="isDuty" style="width:155px">
	<s:iterator  value="#request.isdutyList"  var="isdutyList">
		<option  value="<s:property value="#isdutyList.ddlCode"/>"><s:property  value="#isdutyList.ddlName"/></option>
	</s:iterator>
</select></td>
</tr>

<tr>
<td align="center" bgColor="#f5fafe" class="ta_01">入职日期：</td>
<td class="ta_01" bgColor="#ffffff"><input name="onDutyDate" type="text" maxlength="50" size="20" onclick="JavaScript:calendar(document.Form1.ondutydate)">
</td>
<td align="center" bgColor="#ffffff" class="ta_01"></td>
<td class="ta_01" bgColor="#ffffff"></td>
</tr>

<TR>
<TD class="ta_01" align="center" bgColor="#f5fafe">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注：</TD>
<TD class="ta_01" bgColor="#ffffff" colSpan="3"><textarea name="remark"  style="WIDTH:95%"  rows="4" cols="52"></textarea></TD>
</TR>
<TR>
<td  align="center"  colSpan="4"  class="sep1"></td>
</TR>
<tr>
	<td class="ta_01" style="WIDTH: 100%" align="center" bgColor="#f5fafe" colSpan="4">
	<input type="button" name="BT_Submit" value="保存"  style="font-size:12px; color:black; height=22;width=55"   onClick="check_null()">
	 <FONT face="宋体">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</FONT>
	<input style="font-size:12px; color:black; height=22;width=55"  type="button" value="关闭"  name="Reset1"  onClick="window.close()">
		
	</td>
</tr>
</table>　
</form>

</body>
</html>
