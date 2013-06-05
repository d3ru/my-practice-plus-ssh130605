<%@ page language="java" pageEncoding="gb2312"%>
<% String path=request.getContextPath(); %>
<%@taglib prefix="login" uri="/WEB-INF/tlds/ui.tld"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
<title>Tarena_IT网络商城-主页</title>
<SCRIPT language=javascript src="<%=path%>/js/ajax.js" 
type=text/javascript></SCRIPT>
<SCRIPT language=javascript src="<%=path%>/js/cart.js" 
type=text/javascript></SCRIPT>
<SCRIPT language=javascript src="<%=path%>/js/move.js" 
type=text/javascript></SCRIPT>
<link rel="stylesheet" style="text/css" href="<%=path%>/css/cm.css"/>
<link rel="stylesheet" style="text/css" href="<%=path%>/css/cart.css"/>
<script type="text/javascript">
function load(){
	document.all.a.onfocus;
}
</script>
</head>
<body topmargin="0" bgcolor="#dce0e1" onLoad="load();">
<table align="center" border="0" width="778" cellspacing="0" cellpadding="0">
<tr valign="top">
<td>
<jsp:include flush="true" page="/subpage/head.jsp"></jsp:include>
</td>
</tr>
<tr valign="top">
<td>
<jsp:include flush="true" page="/subpage/navigatenocart.jsp"></jsp:include>
</td>
</tr>
<tr valign="top">
<td>	
<div>
  <center>
  <table border="0" width="778" cellspacing="0" cellpadding="0">
  	<tr>
  	<td valign="top" align="center">
  	  	
		<div id="orderlist"  style="width:500;height:400;">
			<center>
				${order}
			</center>
		</div>
  	</td>
  	</tr>
  </table>
  </center>
</div>
</td>
</tr>
<tr valign="top">
<td><jsp:include flush="true" page="/subpage/foot.jsp"></jsp:include> 
</td>
</tr>
</table>
</body>
</html>
