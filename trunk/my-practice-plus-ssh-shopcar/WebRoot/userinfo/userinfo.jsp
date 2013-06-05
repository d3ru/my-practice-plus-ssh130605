<%@ page  pageEncoding="gb2312" contentType="text/html; charset=gb2312"%>
<%@taglib prefix="login" uri="/WEB-INF/tlds/ui.tld"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>用户登录页</title>
    <link rel="stylesheet" style="text/css" href="<%=path%>/css/cm.css">
    <script type="text/javascript">
    	function isCancle(url){
    		var question = confirm("你确认要删除注册信息吗?");
    		if (question != "0"){
    			window.location.replace(url);
    		}
    	}
    	function isUnregister(url)
    	{
    		var question = confirm("你确认要注销登录吗?");
    		if (question != "0"){
    			window.location.replace(url);
    		}
    	}
    </script>
  </head>
  <body bgcolor="#dce0e1">
  <jsp:include flush="true" page="/subpage/head.jsp"></jsp:include>
  <jsp:include flush="true" page="/subpage/navigatenocart.jsp"></jsp:include>
  <br/>
  <hr size="2" style="width: 778px;" color="#000000"/>
  <div  align="center">
  <table width="778" border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td width="30%" align="center"><table width="97%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="25" align="center" valign="middle"><strong>用户信息自维护</strong></td>
      </tr>
      <tr>
        <td height="25" align="center" valign="middle"><a href="<%=path %>/userinfo/userinfomodify.jsp" target="_self">注册信息修改</a></td>
      </tr>
      <tr>
        <td height="25" align="center" valign="middle"><a href="<%=path %>/userinfo/orderquery.jsp" target="_self">订单信息</a></td>
      </tr>
      <tr>
        <td height="25" align="center" valign="middle"><a href="<%=path %>/userinfo/transactionquery.jsp">交易信息</a></td>
      </tr>
      <tr>
        <td height="25" align="center" valign="middle"><a href="javascript:isCancle('<%=path%>/userinfo/userinfodelete.jsp')" target="_self">注销用户</a></td>
      </tr>
	  <tr>
        <td height="25" align="center" valign="middle"><a href="javascript:isUnregister('<%=path%>/logout.do')">退出登录</a></td>
      </tr>
    </table></td>
    <td align="center" valign="middle">
	    <table width="97%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td height="25" align="center" valign="middle">欢迎使用网上商城在线购物系统v1.0<br/>
			使用说明：用户登陆后可以修改自己的个人信息,修改订单,删除订单,<br/>查看以往交易信息，注销自己等
			</td>
		   </tr>
		</table>
    </td>
  </tr>
</table>
</div>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
  <hr size="2" style="width: 778px;" color="#000000"/>
  <jsp:include flush="true" page="/subpage/foot.jsp"></jsp:include> 
  </body>
</html>



