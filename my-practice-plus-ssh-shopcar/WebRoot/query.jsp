<%@ page language="java" pageEncoding="gb2312"%>
<% String path=request.getContextPath(); %>
<%@taglib prefix="login" uri="/WEB-INF/tlds/ui.tld"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
<title>Tarena_IT网络商城-高级查询</title>
<SCRIPT language=javascript src="<%=path %>/js/ajax.js" 
type=text/javascript></SCRIPT>
<SCRIPT language=javascript src="<%=path %>/js/cart.js" 
type=text/javascript></SCRIPT>
<SCRIPT language=javascript src="<%=path %>/js/move.js" 
type=text/javascript></SCRIPT>
<link rel="stylesheet" style="text/css" href="<%=path %>/css/cm.css"/>
<link rel="stylesheet" style="text/css" href="<%=path %>/css/cart.css"/>
<script type="text/javascript" src="<%=path %>/js/common.js"></script>
<script type="text/javascript">
	function load(){
		document.all.a.onfocus;
	}
	var queryproduct;
   	function highquery(){
   		var url="query.jsp?productype="+document.all.productype.value+
   		"&querycondition="+document.all.querycondition.value+
   		"&querytype="+document.all.querytype.value+
   		"&pdate1="+document.all.pdate1.value+
   		"&pdate2="+document.all.pdate2.value+
   		"&price1="+document.all.price1.value+
   		"&price2="+document.all.price2.value+
   		"&pdiscount1="+document.all.pdiscount1.value+
   		"&pdiscount2="+document.all.pdiscount2.value+
   		"&page=1";
   		
   		window.open(url,"_self");	
   		
   		    		
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
	<!-- 购物车界面开始-->
	<div id="shopsWindow"  style="display: none;position: absolute;left:300;top:200;"  onmousedown="f_mdown(this)" onmousemove="f_move(this)">
		<div class="shopscontain">
			<div id="shops">
				<H2>购物车<img src="images/closeb.gif" class="closepic" onclick="closeShopWindow()"/></H2>
				<div class="productinfo_bt" style="width: 8%;display: none;">ID</div>
				<div class="productinfo_bt" style="width: 40%">商品名称</div>
				<div class="productinfo_bt" style="width: 12%">单价(&yen;)</div>
				<div class="productinfo_bt" style="width: 10%;">库存量</div>
				<div class="productinfo_bt" style="width: 15%">数量</div>
				<div class="productinfo_bt" style="width: 12%">金额(&yen;)</div>
				<div class="productinfo_bt" style="width: 10%; border-right: 0px;">退货</div>

				<div id="shops_carts">
					
				</div>
				<center><button onclick="accountSubmit('${pageContext.request.contextPath}');">结算</button>&nbsp;&nbsp;&nbsp;<button onclick="ShopCartsClear()">清空购物车</button></center>
			</div>
		</div>
	</div>
	<!-- 购物车界面结束-->

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
                <login:highquery></login:highquery>
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
  	<table style="margin-top: 1px;" width="100%" border="1" cellpadding="0" cellspacing="0">
  		<tr>
  		<td width="100%">
  		<div id="main" style="height:385;overflow:auto;">
  		<%
  		String querystring=request.getParameter("querytype");
  		if(querystring==null || !querystring.equals("1"))
  		{ 
  		%>
  			<!-- 高级查询的第一次显示无查询条件，就显示推荐产品 -->
  			<login:listprorityproduct></login:listprorityproduct>
  		<%
  		}
  		else
  		{
  		%>
  			<!-- 高级查询输入条件后，就显示查询的结果 -->
  			<login:advancequery/>
  		<%
  		}
  		%>
  		</div>
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
