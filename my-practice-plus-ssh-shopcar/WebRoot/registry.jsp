<%@ page language="java" pageEncoding="gb2312"%>
<%@taglib prefix="tag"  tagdir="/WEB-INF/tags"%>
<html>
<head>
<meta http-equiv="Content-Language" content="zh-cn"/>
<meta http-equiv="Content-Type" content="text/html; charset=gb2312"/>
<title>CM���׹�����-��ҳ</title>
<SCRIPT language=javascript src="js/ajax.js" 
type=text/javascript>
</SCRIPT>
<script type="text/javascript">
	var checkmail;//��������Ƿ��Ѿ�ע��
	function umailcheck(){
		var url="registryCheck.do?umail="+document.all.umail.value;
  		//����XMLHttpRequest����
		try{
   			checkmail=new XMLHttpRequest();//������ʷԭ�����е���������ܱ��쳣
		}catch(e){
    	//Ϊ��ֹ��ȥIE�ϰ汾��֧��XMLHttpRequest��
    	//����΢���ṩ��Active�������request����
	   		try{
	   			checkmail=new ActiveXObject("MSXML2.XMLHTTP");
	   		}catch(e1){
	   		 	try{
	    			checkmail=new ActiveXObject("Mirosoft.XMLHTTP");
	    			checkmail=new ActiveXObject("MSXML.XMLHTTP");
	    		}catch(e2){
					return;    	
	   			}
	  	 	}
    	}
	   	checkmail.open("get",url);	
	   	checkmail.onreadystatechange=function(){ 				   		
	   		if(checkmail.readyState==4){
   				if(checkmail.status==200){
 					alert("��Ǹ���������Ѿ���ע�ᣬ���������롣");
 					document.all.umail.value="";
 					document.all.umail.focus();
   				}
   				//if(checkmail.status==400){
 					//alert("��ϲ�����������ע��^_^");
   				//}
   			}
   		};
   		checkmail.send(null);    		
   	}
	function firstsubmit(){
		var umail=document.all.umail.value;
    	var upass=document.all.pass.value;
    	var repass=document.all.repass.value;
    	//��������ʼ���У�������
    	if(umail==""){
    		alert("�����ʼ�����Ϊ��");
    		document.all.umail.focus(); 
    		return ;
    	}
    	if(upass==""){
    		alert("���벻��Ϊ��");
    		document.all.pass.focus();
    		return ;
    	}
    	if(repass=="" || repass!=upass){
    		alert("�ظ��������");
    		document.all.pass.focus(); 
    		return ;
    	}
		document.all.firstregpage.style.display="none";
		document.all.secregpage.style.display="block";
		document.all.yhzh.style.color="black";
		document.all.yhxx.style.color="red";
	}
	function firstback(){
		document.all.firstregpage.style.display="block";
		document.all.secregpage.style.display="none";
		document.all.yhzh.style.color="red";
		document.all.yhxx.style.color="black";
	}
	function secback(){
		document.all.thirdregpage.style.display="none";
		document.all.secregpage.style.display="block";
		document.all.yhxx.style.color="red";
		document.all.zcxx.style.color="black";
	}
	function secsubmit(){
		var uname=document.all.uname.value;
    	var uaddress=document.all.uaddress.value;
    	var uzip=document.all.uzip.value;
    	var utele=document.all.utele.value;
    	var umobile=document.all.umobile.value;
    	if(uname==""){
    		alert("��������Ϊ��");
    		document.all.uname.focus(); 
    		return ;
    	}
    	if(uaddress==""){
    		alert("��ַ����Ϊ��");
    		document.all.uaddress.focus(); 
    		return ;
    	}
    	if(uzip==""){
    		alert("�ʱ಻��Ϊ��");
    		document.all.uzip.focus(); 
    		return ;
    	}
    	if(utele==""){
    		alert("�绰����Ϊ��");
    		document.all.utele.focus(); 
    		return ;
    	}
		document.all.yhzh.style.color="black";
		document.all.yhxx.style.color="black";
		document.all.zcxx.style.color="red";
		document.all.firstregpage.style.display="none";
		document.all.secregpage.style.display="none";
		document.all.thirdregpage.style.display="block";
		document.all.uuname.value=document.all.uname.value;
		document.all.uuaddress.value=document.all.uaddress.value;
		document.all.uuzip.value=document.all.uzip.value;
		document.all.uutele.value=document.all.utele.value;
		document.all.uumobile.value=document.all.umobile.value;
		document.all.uumail.value=document.all.umail.value;
	}
	function reg(){
		document.all.regform.submit();
	}
</script>
<link rel="stylesheet" style="text/css" href="css/cm.css">
<script type="text/javascript">
function load(){
	document.all.umail.onfocus;
}
</script>
</head>
<body topmargin="0" bgcolor="#dce0e1" onload="load();">
<form name="regform" action="registrysave.jsp" method="post">
<jsp:include flush="true" page="subpage/head.jsp"></jsp:include>
<jsp:include flush="true" page="subpage/navigate.jsp"></jsp:include>
  <br/>
<div align="center">
<table  height="5%" border="0" width="778" cellspacing="0" cellpadding="0">
  <tr style="padding-top: 10px;margin-bottom:-10px;font-size: 14px;font-weight: bold">
  <td align="right" width="100px" >
  ע�������򵼣�
  </td>
  <td align="left" width="70px">
  <div id="yhzh" style="color: red;font-style: italic;font-family: serif;">�û��ʺš�</div>
  </td>
  <td align="left" width="70px">
  <div id="yhxx" style="color: black;font-style: italic;font-family: serif;">�û���Ϣ��</div>
  </td>
  <td align="left" >
  <div id="zcxx" style="color: black;font-style: italic;font-family:serif;">ע����Ϣ�ύ</div>
  </td>
  </tr>
  </table>
  <hr size="2" style="width: 778px;" color="#000000"/>
</div>
<div id="firstregpage" align="center" style="margin-bottom: -8px;padding-bottom: -8px;display: block;">
  <center>
  <table  height="50%" border="0" width="778" cellspacing="0" cellpadding="0">
  <tr>
  <td width="300" align="right">�����ʼ���
  </td>
  <td align="left" style="padding-left: 30px;"><input id="umail" type="text" name="umail" onblur="umailcheck();" />
  </td>
  </tr>
  <tr>
  <td align="right">��&nbsp;&nbsp;&nbsp;&nbsp;�
  </td>
  <td align="left" style="padding-left:30px;"><input id="pass" type="password" name="pass" />
  </td>
  </tr>
  <tr>
  <td align="right">�ظ����
  </td>
  <td align="left" style="padding-left: 30px;"><input id="repass" type="password" name="repass" />
  </td>
  </tr>
  <tr height="120">
  <td align="center" colspan="2"><button onclick="firstsubmit();">��һ��</button></td>
  </tr>
  </table>
  </center>
</div>
<div id="secregpage" style="display: none;" align="center" style="margin-bottom: -8px;padding-bottom: -8px">
  <center>
  <table  height="50%" border="0" width="778" cellspacing="0" cellpadding="0" >
  <tr>
  <td width="300" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����
  </td>
  <td align="left" style="padding-left: 30px;"><input id="uname" type="text" name="uname" />
  </td>
  </tr>
  <tr>
  <td align="right">��&nbsp;&nbsp;&nbsp;&nbsp;ַ��
  </td>
  <td align="left" style="padding-left: 30px;"><input id="uaddress" type="text" name="uaddress" />
  </td>
  </tr>
  <tr>
  <td align="right">��&nbsp;&nbsp;&nbsp;&nbsp;�ࣺ
  </td>
  <td align="left" style="padding-left: 30px;"><input id="uzip" type="text" name="uzip" />
  </td>
  </tr>
  <tr>
  <td align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����
  </td>
  <td align="left" style="padding-left: 30px;"><input id="utele" type="text" name="utele" />
  </td>
  </tr>
  <tr>
  <td align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����
  </td>
  <td align="left" style="padding-left: 30px;"><input id="umobile" type="text" name="umobile" />
  </td>
  </tr>
  <tr height="120">
  <td align="center" colspan="2"><button onclick="secsubmit();">��һ��</button>&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="firstback();">��һ��</button>
  </td>
  </tr>
  </table>
  </center>
</div>
<div id="thirdregpage" style="display: none;" align="center" style="margin-bottom: -8px;padding-bottom: -8px">
  <center>
  <div>����ע����Ϣ���£�ȷ��ע������"ע��"��ť</div>
  <table  height="50%" border="0" width="778" cellspacing="0" cellpadding="0" >
  <tr>
  <td width="300" align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����
  </td>
  <td align="left" style="padding-left: 30px;"><input id="uuname" readonly/>
  </td>
  </tr>
  <tr>
  <td align="right">��&nbsp;&nbsp;&nbsp;&nbsp;ַ��
  </td>
  <td align="left" style="padding-left: 30px;"><input id="uuaddress" readonly/>
  </td>
  </tr>
  <tr>
  <td align="right">��&nbsp;&nbsp;&nbsp;&nbsp;�ࣺ
  </td>
  <td align="left" style="padding-left: 30px;"><input id="uuzip" readonly/>
  </td>
  </tr>
  <tr>
  <td align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����
  </td>
  <td align="left" style="padding-left: 30px;"><input id="uutele" readonly/>
  </td>
  </tr>
  <tr>
  <td align="right">��&nbsp;&nbsp;&nbsp;&nbsp;����
  </td>
  <td align="left" style="padding-left: 30px;"><input id="uumobile" readonly/>
  </td>
  </tr>
  <tr>
  <td align="right">�����ʼ���
  </td>
  <td align="left" style="padding-left: 30px;"><input id="uumail" readonly/>
  </td>
  </tr>
  <tr height="120">
  <td align="center" colspan="2"><button onclick="reg();">ע��</button>&nbsp;&nbsp;&nbsp;&nbsp;<button onclick="secback();">��һ��</button>
  </td>
  </tr>
  </table>
  </center>
</div>
<div style="margin-top: 2px;padding-top: 2px;margin-bottom: 5px;padding-bottom: 5px;">
  <hr color="gray" size="1" width="778" align="center">
</div>
<div align="center" style="margin-top: -10px;padding-top: -10px">
  <table border="0" width="778">
  <tr>
      <td height="50" align="center" valign="middle">
      <tag:footer/>
      </td>
    <td width="24"><br></td>
    </tr>
  </table>
</div>
<div align="center">
  <center>
  <table border="1" width="778" cellspacing="0" cellpadding="0" style="border-left-style: solid; border-left-color: #FFFFFF; border-right-style: solid; border-right-color: #FFFFFF; border-top-style: solid; border-top-color: #FFFFFF; border-bottom-style: solid; border-bottom-color: #FFFFFF;">
    <tr>
      <td width="778" background="images/2.gif">��</td>
    </tr>
  </table>
  </center>
</div>
</form>
</body>

</html>
