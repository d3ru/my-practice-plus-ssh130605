<%@ page language="java" pageEncoding="gb2312"%>
<jsp:directive.page import="global.TableInfo"/>
<% String path=request.getContextPath(); %>
<%@taglib prefix="login" uri="/WEB-INF/tlds/ui.tld"%>
<%@ taglib uri="/WEB-INF/tlds/transactionsqueryshow.tld" prefix="tqui"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
<title>交易查询</title>
<SCRIPT language=javascript src="js/ajax.js" 
type=text/javascript></SCRIPT>
<link rel="stylesheet" style="text/css" href="../css/cm.css"/>
<script type="text/javascript" src="../js/common.js"></script>
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
		<table width="778" border="1" cellspacing="0" cellpadding="0">
			<tr>
				<td width="30%" align="center">
					<form id="form1" name="form1" method="post" action="">
						<table width="97%" border="0" cellspacing="0" cellpadding="0">
							<tr>
								<td height="24" align="center" valign="middle">
									<strong>交易记录查询</strong>
								</td>
							</tr>
							<tr>
								<td height="24" align="center" valign="middle">
									商品名称 <label><input name="<%=TableInfo.PROT_pname %>" type="text" size="12" /></label>
								</td>
							</tr>
							<tr>
								<td height="24" align="center" valign="middle">
									商品描述	<label><input name="<%=TableInfo.PROT_pdescription %>" type="text" size="12" /></label>
								</td>
							</tr>
							<tr>
								<td height="24" align="center" valign="middle">
									交易日期 <label><input name="<%=TableInfo.TRAN_tshipdate %>" type="text" size="12" /></label>
								</td>
							</tr>
							<tr>
								<td height="50" align="center" valign="middle">
									<label><input type="checkbox" name="<%=TableInfo.TRAN_tshiped %>" value="1" checked="checked" />已经发货</label><br>
									<label><input type="checkbox" name="<%=TableInfo.TRAN_tshiped %>" value="2" checked="checked" />已经签收</label>
								</td>
							</tr>
							<tr>
								<td height="24" align="center" valign="middle">
									<label><input type="submit" name="Submit" value="提交" />&nbsp;&nbsp;
										<input type="reset" name="Submit2" value="重填" /></label>
								</td>
							</tr>
						</table>
					</form>
				</td>
				<td width="70%">
				<%-- 交易记录查询，原型：<tqui:transactionQueryShow queryPage="transactionquery.jsp"/> --%>
					<tqui:transactionQueryShow/>
				</td>
			</tr>
		</table>
  </center>
</div>
</td>
</tr>
<tr valign="top">
<td>
<jsp:include flush="true" page="/subpage/foot.jsp"></jsp:include> 
</td>
</tr>
</table>
</body>
</html>