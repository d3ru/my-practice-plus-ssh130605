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
    <title>��ϲ</title>
   <script type="text/javascript" src="js/common.js"></script>
  </head>
  
  <body>
		<form id="form1" name="form1" method="post" action="query.jsp">
		  <label><input type="radio" name="productype" value="1" />�ٻ�</label>
		  <label><input type="radio" name="productype" value="2" />ͼ��</label>
		  <label><input type="radio" name="productype" value="3" />����</label>
		  <label><input type="radio" name="productype" value="4" />Ӱ��</label>
		  <label><input type="text" name="querycondition" /></label>
		  <label><input type="submit" name="Submit" value="�ύ" /><input type="hidden" name="querytype"  value="0"/></label>
		</form>
		<form id="form2" name="form2" method="post" action="query.jsp">
		  <label>��Ʒ����<input name="querycondition" type="text" size="8" />
		  </label><br>
		  <label>��Ʒ����
		  <select name="productype">
		  	<option value="999">��������</option>
		  	<option value="1">�ٻ�</option>
			<option value="2">ͼ��</option>
			<option value="3">����</option>
			<option value="4">Ӱ��</option>
		  </select></label><br>
		  <label>��������<input name="pdate" type="text" size="8" />
		  </label><br>
		  <label>�۸�Χ<input name="price1" type="text" size="3" />&nbsp;
		  <input name="price2" type="text" size="3" /></label><br>
		  <label>�ۿ���<input name="pdiscount" type="text" size="8" />
		  </label><br>
		  <label><input type="submit" name="Submit2" value="�ύ" />
		  <input type="reset" name="Submit3" value="����" /></label>
		  <input type="hidden" name="querytype"  value="1"/>
		</form>
  <oqui:OrderQueryShow/>
    <div style="font-size: 12px;text-align: center;">.��Ĳ�������:${sc_success}!<br>
     ҳ��2����Զ����ص���һҳ,�����������û����ת,<a href="javascript:window.history.go(-1)">��������</a>
    </div>
    <tqui:transactionQueryShow/>
  </body>
</html>
