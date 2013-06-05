<%@ page language="java" pageEncoding="gb2312"%>
<%@taglib prefix="login" uri="/WEB-INF/tlds/ui.tld"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
<title>Tarena_IT网络商城-主页</title>
<SCRIPT language=javascript src="js/ajax.js" 
type=text/javascript></SCRIPT>
<script type="text/javascript" src="../js/common.js"></script>
<link rel="stylesheet" style="text/css" href="css/cm.css">
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
<jsp:include flush="true" page="subpage/head.jsp"></jsp:include>
</td>
</tr>
<tr valign="top">
<td>
<jsp:include flush="true" page="subpage/navigate.jsp"></jsp:include>
</td>
</tr>
<tr valign="top">
<td>
<div>
  <center>
  <table border="0" width="778" cellspacing="0" cellpadding="0">
  <tr>
  <td valign="top" height="10">
  </td>
  </tr>
  <tr>
  <td valign="top" height="85%">
  <table border="0" width="778" cellspacing="0" cellpadding="0">
  <tr>
  <td width="192" valign="top">
  	<table  height="100%"  width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr valign="top">
		<td><table border="0" width="100%" cellspacing="0" cellpadding="0">
          <tr>
            <td></td>
            </tr>
          <tr>
            <td>
              <table style='padding-top:10px;' border="1" width="100%" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center">
                <login:transactionquery></login:transactionquery>
                </td>
                </tr>
              </table></td>
            </tr>
        </table></td>
		</tr>
		<tr>
		  <td><table border="0" width="100%" cellspacing="0" cellpadding="0">
        <tr>
          <td width="6%" height="8"></td>
          <td width="84%" height="10">&nbsp;</td>
          <td width="10%"></td>
        </tr>
        <tr>
          <td colspan="3"><table border="1" width="100%" cellspacing="0" cellpadding="0">
              <tr>
                <td id="cata" width="100%" background="images/2.gif"><p align="center">::商品种类::</td>
              </tr>
              <tr valign="top" style="height: 160px;margin-bottom: 0px;padding-bottom: 4px;">
                <td width="100%" >　
                <div align="left"><login:menutag submitevent="showprobytype"></login:menutag></div>
                  　</td>
              </tr>
          </table></td>
        </tr>
      </table></td>
		  </tr>
		<tr>
		  <td>&nbsp;</td>
		  </tr>
		<tr>
		  <td>&nbsp;</td>
		  </tr>
		</table>  </td>
		<td width="10">		</td>
  <td width="576" valign="top">
  	<table style="margin-top: 1px;" width="100%" border="0" cellpadding="0" cellspacing="0">
  		<tr>
  		<td width="100%">
  		<login:listprorityproduct></login:listprorityproduct>
  		</td>
  		</tr>
	</table>
  </td>
  </tr>
  </table></td></tr></table>
  </center>
</div>
</td>
</tr>
<tr valign="top">
<td>
<jsp:include flush="true" page="subpage/foot.jsp"></jsp:include> 
</td>
</tr>
</table>
</body>
</html>
