<%@ page pageEncoding="gb2312"%>
<jsp:directive.page import="global.TableInfo"/>
<%@ taglib uri="/WEB-INF/tlds/orderqueryshow.tld" prefix="oqui"%>
<%@ taglib uri="/WEB-INF/tlds/transactionsqueryshow.tld" prefix="tqui"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%
	session.setAttribute(TableInfo.SC_User,"1");
 %>
<html>
  <head>
    <title>个人订单显示</title>
   <script type="text/javascript" src="js/common.js"></script>
  </head>
  
  <body>
  <oqui:OrderQueryShow/>
  </body>
</html>
