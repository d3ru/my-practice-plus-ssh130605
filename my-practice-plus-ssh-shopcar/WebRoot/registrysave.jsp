<%@ page language="java" import="global.*" pageEncoding="gb2312"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
<title>CM简易购物网-主页</title>
<link rel="stylesheet" style="text/css" href="css/cm.css">
</head>
<body topmargin="0" bgcolor="#dce0e1" onload="load();">
<%
	String umail=request.getParameter("umail");
	String upassword=request.getParameter("repass");
	String uname=request.getParameter("uname");
	String uaddress=request.getParameter("uaddress");
	String uzip=request.getParameter("uzip");
	String utele=request.getParameter("utele");
	String umobile=request.getParameter("umobile");
	String sql="insert into UserInfo (umail,uid,upassword,uname,uaddress,uzip,utele,umobile)values ('"+umail+"',"+umail.hashCode()+",'"+upassword+"','"+uname+"','"+uaddress+"','"+uzip+"','"+utele+"','"+umobile+"')";
	IDataAccess ida=DataAccessImpl.newInstance();
	ida.executeSQL(sql);
	//这里自动登录功能。
	session.setAttribute("loginsuccess", "登录成功！");
	session.setAttribute("uname",uname);
	session.setAttribute("sc_user",""+umail.hashCode());
%>
<div  align="center">
  <center>
  <table id="a" border="1" width="778" cellspacing="0" cellpadding="0" style="border-left-style: solid; border-left-color: #FFFFFF; border-right-style: solid; border-right-color: #FFFFFF; border-top-style: solid; border-top-color: #000000; border-bottom-style: solid; border-bottom-color: #000000">
    <tr>
      <td width="772" background="images/2.gif"></td>
    </tr>
  </table>
  </center>
</div>
<div align="center">
  <table border="0" width="778">
  <tr>
      <td width="250" align="center" valign="middle"><img src="images/icon.jpg"/></td>
    <td width="484" height="50" align="center" valign="middle">
<font size="6px" face="serif"><em><strong>CM简易电子网城</strong></em></font></td>
    <td width="24"></td>
    </tr>
  </table>
  
</div>
<div align="center">
  <center>
  <table border="1" width="778" style="border-left-style: solid; border-left-color: #FFFFFF; border-right-style: solid; border-right-color: #FFFFFF; border-top-style: solid; border-top-color: #000000; border-bottom-style: solid; border-bottom-color: #000000" background="images/2.gif" cellspacing="0" cellpadding="0">
    <tr>
      <td width="100%" align="right"><div align="right" style="margin-right: 8px;padding-right: 8px;">
		<SCRIPT language=javascript src="js/date.js" type=text/javascript></SCRIPT></div></td>
    </tr>
  </table>
  </center>
</div>
<div align="center">
<table height="70%" border="0" width="778" cellspacing="0" cellpadding="0">
  <tr>
  <td>
 <font color="red"><%=uname%></font>,恭喜你，注册成功！！
 两秒后跳转到系统首页，请登录后使用。
 <%response.sendRedirect("index.jsp"); %>
  
  </td>
  </tr>
  </table>
</div>
</body>

</html>
