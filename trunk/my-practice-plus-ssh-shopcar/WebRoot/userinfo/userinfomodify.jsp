<%@ page pageEncoding="gb2312" contentType="text/html; charset=gb2312"%>
<!-- �ñ���ϸ�XML��ʽ -->
<jsp:directive.page import="global.TableInfo"/>
<jsp:directive.page import="global.DataAccessImpl"/>
<jsp:directive.page import="java.sql.ResultSet"/>
<%String path = request.getContextPath(); %>
<%
	String uid = session.getAttribute("sc_user").toString();//��ȡ�û�ID
	String sql = "select * from "+TableInfo.TABLE_UserInfo+" where ";
	sql += TableInfo.USER_uid+"='"+uid+"'";
	DataAccessImpl dataAccess = DataAccessImpl.newInstance();
	ResultSet rst = dataAccess.queryBySQL(sql);
	if(rst.next())
	{
 %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<head>
		<title>�û�ע����Ϣ�޸�</title>
		<link rel="stylesheet" style="text/css" href="<%=path%>/css/cm.css"></link>
	</head>

	<body style="font-size :12px;text-align:center;background-color: #dce0e1;">
	<jsp:include flush="true" page="/subpage/head.jsp"></jsp:include>
  	<jsp:include flush="true" page="/subpage/navigatenocart.jsp"></jsp:include>
	<br/><br/>
		<table width="300"  border="1" cellspacing="0" cellpadding="0">
			<tr height="100%">
				<td align="center" valign="top">
				<form id="form1" name="form1" method="post" action="userinfoupdate.jsp">
					<table border="0" cellspacing="0" cellpadding="0">
						<tr>
							<td  height="35" align="right">�����ʼ���</td>
							<td align="center">
								<label><input style="width: 120px;" name="<%=TableInfo.USER_umail%>" type="text" size="12" readonly="readonly" value="<%=rst.getString(TableInfo.USER_umail)%>"/></label>
							</td>
						</tr>
						<tr>
							<td height="35" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
							<td align="center">
								<label><input style="width: 120px;" name="<%=TableInfo.USER_uname%>" type="text" size="12" value="<%=rst.getString(TableInfo.USER_uname)%>"/></label>
							</td>
						</tr>
						<tr>
							<td height="35" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;ַ��</td>
							<td align="center">
								<label><input style="width: 120px;"s name="<%=TableInfo.USER_uaddress%>" type="text" size="12" value="<%=rst.getString(TableInfo.USER_uaddress)%>"/></label>
							</td>
						</tr>
						<tr>
							<td height="35" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;�ࣺ</td>
							<td align="center">
								<label><input style="width: 120px;" name="<%=TableInfo.USER_uzip%>" type="text" size="12" value="<%=rst.getString(TableInfo.USER_uzip)%>"/></label>
							</td>
						</tr>
						<tr>
							<td height="35" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
							<td align="center">
								<label><input style="width: 120px;" name="<%=TableInfo.USER_utele%>" type="text" size="12" value="<%=rst.getString(TableInfo.USER_utele)%>"/></label>
							</td>
						</tr>
						<tr>
							<td height="35" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����</td>
							<td align="center">
								<label><input style="width: 120px;" name="<%=TableInfo.USER_umobile%>" type="text" size="12" value="<%=rst.getString(TableInfo.USER_umobile)%>"/></label>
							</td>
						</tr>
						<tr>
							<td height="40" colspan="2" align="center">
								<label>
									<input type="submit" name="Submit" value="�ύ" />&nbsp;&nbsp;&nbsp;&nbsp;
									<input type="reset" name="Submit2" value="����" />
								</label>
							</td>
						</tr>
					</table>
					</form>
				</td>
			</tr>
		</table>
		<p>&nbsp;</p>
		<p>&nbsp;</p>
		<jsp:include flush="true" page="/subpage/foot.jsp"></jsp:include> 
	</body>
</html>
<%} %>
