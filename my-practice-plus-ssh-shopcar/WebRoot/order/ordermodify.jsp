<%@ page pageEncoding="gb2312"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<jsp:directive.page import="global.DataAccessImpl"/>
<jsp:directive.page import="businesses.tools.CommonTools"/>
<jsp:directive.page import="businesses.tools.QueryTools"/>
<jsp:directive.page import="global.TableInfo"/>
<jsp:directive.page import="java.sql.ResultSet"/>
<jsp:directive.page import="java.sql.Blob"/>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>订单修改</title>
	</head>
<%
	DataAccessImpl dataAccess = DataAccessImpl.newInstance();
	String orderidx = request.getParameter("orderid");
	int orderid  = CommonTools.StringToInt(orderidx);
	if(orderid == -1)
		CommonTools.Error(request,response,session);
	else{
		if(QueryTools.isExistColumn(dataAccess,TableInfo.TRAN_tid,TableInfo.TABLE_Transactions,TableInfo.TRAN_tid+"="+orderid)){
	String tamount = QueryTools.getColumnValue(dataAccess,TableInfo.TRAN_tamount,TableInfo.TABLE_Transactions,TableInfo.TRAN_tid+"="+orderid);
	String pid = QueryTools.getColumnValue(dataAccess,TableInfo.TRAN_tid,TableInfo.TABLE_Transactions,TableInfo.TRAN_tid+"="+orderid);
	String sql = "select "+TableInfo.PROT_pname+","+TableInfo.PROT_pdate+","+TableInfo.PROT_pprice+",";
	sql += TableInfo.PROT_pdiscount+","+TableInfo.PROT_pamount+","+TableInfo.PROT_pphoto;
	sql += " from "+TableInfo.TABLE_Products+" where "+TableInfo.PROT_pid+"="+pid;
	System.out.println(sql);
	ResultSet rst = dataAccess.queryBySQL(sql);
	if(rst.next()){	
	Blob blob = rst.getBlob(TableInfo.PROT_pphoto);
%>
	<body>
		<form id="form1" name="form1" method="post"
			action="order_manager.jsp?method=update">
			<table width="90%" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td width="27%" height="24" align="center">订单编号</td>
					<td width="27%" height="24" align="center">
						<label><input type="text" name="orderid" readonly="true" value="<%=pid%>" /></label>
					</td>
					<td width="46%" rowspan="6" align="center" valign="middle">这是显示商品图片
					<%
						if(blob!=null){
					%>
						<img src='<%="product.jpg?pid="+pid%>' width="100" height="100">
					<%
						}else{
					%>
						暂无图片
					<%
						}
					%>
					</td>
				</tr>
				<tr>
					<td height="24" align="center">产品名称</td>
					<td height="24" align="center">
						<label><input type="text" name="pname" readonly="true" value="<%=rst.getString(TableInfo.PROT_pname)%>" /></label>
					</td>
				</tr>
				<tr>
					<td height="24" align="center">生产日期</td>
					<td height="24" align="center">
						<label><input type="text" name="pdate" readonly="true" value="<%=rst.getString(TableInfo.PROT_pdate)%>" /></label>
					</td>
				</tr>
				<tr>
					<td height="24" align="center">价格</td>
					<td height="24" align="center">
						<label><input type="text" name="pprice" readonly="true" value="<%=rst.getString(TableInfo.PROT_pprice)%>" /></label>
					</td>
				</tr>
				<tr>
					<td height="24" align="center">折扣率</td>
					<td height="24" align="center">
						<label><input type="text" name="pdiscount" readonly="true" value="<%=CommonTools.StringToDouble(rst.getString(TableInfo.PROT_pdiscount))/10%>" /></label>
					</td>
				</tr>
				<tr>
					<td height="24" align="center">库存量</td>
					<td height="24" align="center">
						<label><input type="text" name="pdiscount" readonly="true" value="<%=rst.getString(TableInfo.PROT_pamount)%>" /></label>
					</td>
				</tr>
				<tr>
					<td height="24" align="center">数量</td>
					<td height="24" align="center">
						<label><input type="text" name="tamount" value="<%=tamount%>"/></label>
					</td>
					<td></td>
				</tr>
				<tr>
					<td></td>
					<td></td>
					<td></td>
				</tr>
				<tr>
					<td colspan="3" align="center">
						<label>
							<input type="submit" name="Submit" value="修改" />
							<input type="reset" name="Submit2" value="重填" />
						</label>
					</td>
				</tr>
			</table>
		</form>
		<%
			}
		}
	}
		 %>
	</body>
</html>
