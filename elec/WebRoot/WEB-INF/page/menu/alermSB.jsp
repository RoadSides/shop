<%@taglib prefix="s"  uri="/struts-tags" %>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<html>
  <head>
    <title>load</title>
    <link href="${pageContext.request.contextPath }/css/Style.css" type="text/css" rel="stylesheet" />
   </head>
  
  <body>
    <table width="100%" border="0" id="table8">
    <s:if test="#request.commonList!=null">
			<s:iterator  value="#request.commonList"  var="common">
				<tr>
					<td align="left" valign="middle"  style="word-break: break-all">
					<span class="style1">&nbsp&nbsp&nbsp${common.devRun}
					</span></td>
				</tr>		
			</s:iterator>
	</s:if>
	
				
	</table>
  </body>
</html>