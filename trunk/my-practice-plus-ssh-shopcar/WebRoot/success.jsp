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
    <title>恭喜</title>
   <script type="text/javascript" src="js/common.js"></script>
  </head>
  
  <body>
		<form id="form1" name="form1" method="post" action="query.jsp">
		  <label><input type="radio" name="productype" value="1" />百货</label>
		  <label><input type="radio" name="productype" value="2" />图书</label>
		  <label><input type="radio" name="productype" value="3" />音乐</label>
		  <label><input type="radio" name="productype" value="4" />影视</label>
		  <label><input type="text" name="querycondition" /></label>
		  <label><input type="submit" name="Submit" value="提交" /><input type="hidden" name="querytype"  value="0"/></label>
		</form>
		<form id="form2" name="form2" method="post" action="query.jsp">
		  <label>商品名称<input name="querycondition" type="text" size="8" />
		  </label><br>
		  <label>商品类型
		  <select name="productype">
		  	<option value="999">所有类型</option>
		  	<option value="1">百货</option>
			<option value="2">图书</option>
			<option value="3">音乐</option>
			<option value="4">影视</option>
		  </select></label><br>
		  <label>生产日期<input name="pdate" type="text" size="8" />
		  </label><br>
		  <label>价格范围<input name="price1" type="text" size="3" />&nbsp;
		  <input name="price2" type="text" size="3" /></label><br>
		  <label>折扣率<input name="pdiscount" type="text" size="8" />
		  </label><br>
		  <label><input type="submit" name="Submit2" value="提交" />
		  <input type="reset" name="Submit3" value="重置" /></label>
		  <input type="hidden" name="querytype"  value="1"/>
		</form>
  <oqui:OrderQueryShow/>
    <div style="font-size: 12px;text-align: center;">.你的操作有误:${sc_success}!<br>
     页面2秒后自动跳回到上一页,如果你的浏览器没有跳转,<a href="javascript:window.history.go(-1)">请点击这里</a>
    </div>
    <tqui:transactionQueryShow/>
  </body>
</html>
