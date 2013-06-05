<%@ page language="java" pageEncoding="gb2312"%>
<% String path=request.getContextPath(); %>
<%@taglib prefix="login" uri="/WEB-INF/tlds/ui.tld"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
<title>Tarena_IT网络商城-主页</title>
<SCRIPT language=javascript src="js/ajax.js" 
type=text/javascript></SCRIPT>
<SCRIPT language=javascript src="js/cart.js" 
type=text/javascript></SCRIPT>
<SCRIPT language=javascript src="js/move.js" 
type=text/javascript></SCRIPT>
<link rel="stylesheet" style="text/css" href="css/cm.css"/>
<link rel="stylesheet" style="text/css" href="css/cart.css"/>
<script type="text/javascript">
function load(){
	document.all.a.onfocus;
}

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
	<!-- 购物车ＵＩ代码开始 -->
	<div id="shopsWindow"  style="display: none;position: absolute;left:300;top:200;"  onmousedown="f_mdown(this)" onmousemove="f_move(this)">
		<div class="shopscontain">
			<div id="shops">
				<H2>购物车<img src="<%=path%>/images/closeb.gif" class="closepic" onclick="closeShopWindow()"/></H2>
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
	<!-- 购物车ＵＩ代码结束 -->
	
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
              <table border="1" width="100%" cellspacing="0" cellpadding="0">
                <tr>
                  <td id="logintitle" width="100%" background="images/2.gif">
                    <p align="center">::登录本站::</td>
                  </tr>
                <tr>
                  <td width="100%" align="center">　
                  <div id="login" align="center"><login:login verifycodeurl="${pageContext.request.contextPath}/img.jpg" registryurl="${pageContext.request.contextPath}/registry.jsp" submitevent="loginRequest('${pageContext.request.contextPath}')" isajax="true" actionurl="loginservice.do" backcss="css/login.css" background="false"></login:login></div>
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
  		<td height="25">
  		<!-- 商品查询UI -->
  		<login:productquerytag></login:productquerytag>
  		</td>
  		</tr>
  		<tr>
  		<td width="100%" colspan="2">
  		<!-- 主要或推荐商品显示区域 -->
  		<div id="main"  style="height:330;overflow:auto;">  		
  		<login:listprorityproduct></login:listprorityproduct>
  		</div>
  		</td>
  		</tr>
	</table></td>  
  </tr>
  </table>
  </td>
  </tr></table>
  </center>
</div>
</td>
</tr>
<tr valign="top">
<td><jsp:include flush="true" page="subpage/foot.jsp"></jsp:include> 
</td>
</tr>
</table>
</body>
</html>
