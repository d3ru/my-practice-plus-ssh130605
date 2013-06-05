<%@ page  pageEncoding="gb2312" contentType="text/html; charset=gb2312"%>
<%@taglib prefix="login" uri="/WEB-INF/tlds/ui.tld"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户登录页</title>
    <link rel="stylesheet" style="text/css" href="<%=path%>/css/cm.css">
  </head>
  <body bgcolor="#dce0e1">
	  <jsp:include flush="true" page="/subpage/head.jsp"></jsp:include>
	  <jsp:include flush="true" page="/subpage/navigatenocart.jsp"></jsp:include>
	  <br/>
	  <hr size="2" style="width: 778px;" color="#000000"/>
		<div align="center"><font face="隶书" size="5"><b>用户登陆</b></font></div>
		<div align="center">
			<br/>
			<br/>
			<br/>
			<table  height='200'>
				<tr height="20%"><td width="20%"></td><td width="30%"><font color="red">${notlogin}</font></td><td width="50%"></td></tr>
				<tr height="80%">
					<td></td>
					<td width="80%">			
						<login:loginonly verifycodeurl="${pageContext.request.contextPath}/img.jpg" registryurl="${pageContext.request.contextPath}/registry.jsp" submitevent="loginRequest('${pageContext.request.contextPath}')" isajax="false" actionurl="loginservice.do"  backcss="css/login.css" background="true"></login:loginonly>
					</td>
					<td></td>
				</tr>
			</table>
		</div>
		<br/>
		<br/>
		<br/>
		<br/>
		
		<br/><br/><br/><br/>
		<br/><br/>
	  <hr size="2" style="width: 778px;" color="#000000"/>
	  <jsp:include flush="true" page="/subpage/foot.jsp"></jsp:include> 
  </body>
</html>


