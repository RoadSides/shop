<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<HTML>
<HEAD>
<TITLE>国家电力监测中心首页</TITLE>
<LINK href="/css/Font.css" type="text/css" rel="stylesheet">

<STYLE>BODY {
	SCROLLBAR-ARROW-COLOR: #ffffff; SCROLLBAR-BASE-COLOR: #dee3f7
}
</STYLE>

<SCRIPT type="text/javascript">
function submitrequest(action){
eval("document.location='"+action+"'");
}

function exitsys(){
    window.close();   
 }
</SCRIPT>
</HEAD>

<FRAMESET border=0 frameSpacing=0 rows=82,* frameBorder=0 id="mainparent">
<FRAME name=topFrame src="${pageContext.request.contextPath }/system/elecMenuAction_title.action" noResize scrolling=no>
<FRAMESET name="main" border=0 frameSpacing=0 frameBorder=0 cols=143,1%,*>
<FRAME name="leftFrame" src="${pageContext.request.contextPath }/system/elecMenuAction_left.action" noResize>
<frame name="changeButton" src="${pageContext.request.contextPath }/system/elecMenuAction_change1.action" frameBorder=0 marginHeight=0 marginWidth=0 scrolling=no noresize>
<FRAME name="mainFrame" src="${pageContext.request.contextPath }/system/elecMenuAction_loading.action" >
</FRAMESET>
</FRAMESET>


</HTML>
