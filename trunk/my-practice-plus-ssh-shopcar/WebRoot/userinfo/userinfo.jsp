<%@ page  pageEncoding="gb2312" contentType="text/html; charset=gb2312"%>
<%@taglib prefix="login" uri="/WEB-INF/tlds/ui.tld"%>
<%String path = request.getContextPath(); %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>�û���¼ҳ</title>
    <link rel="stylesheet" style="text/css" href="<%=path%>/css/cm.css">
    <script type="text/javascript">
    	function isCancle(url){
    		var question = confirm("��ȷ��Ҫɾ��ע����Ϣ��?");
    		if (question != "0"){
    			window.location.replace(url);
    		}
    	}
    	function isUnregister(url)
    	{
    		var question = confirm("��ȷ��Ҫע����¼��?");
    		if (question != "0"){
    			window.location.replace(url);
    		}
    	}
    </script>
  </head>
  <body bgcolor="#dce0e1">
  <jsp:include flush="true" page="/subpage/head.jsp"></jsp:include>
  <jsp:include flush="true" page="/subpage/navigatenocart.jsp"></jsp:include>
  <br/>
  <hr size="2" style="width: 778px;" color="#000000"/>
  <div  align="center">
  <table width="778" border="1" cellspacing="0" cellpadding="0">
  <tr>
    <td width="30%" align="center"><table width="97%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td height="25" align="center" valign="middle"><strong>�û���Ϣ��ά��</strong></td>
      </tr>
      <tr>
        <td height="25" align="center" valign="middle"><a href="<%=path %>/userinfo/userinfomodify.jsp" target="_self">ע����Ϣ�޸�</a></td>
      </tr>
      <tr>
        <td height="25" align="center" valign="middle"><a href="<%=path %>/userinfo/orderquery.jsp" target="_self">������Ϣ</a></td>
      </tr>
      <tr>
        <td height="25" align="center" valign="middle"><a href="<%=path %>/userinfo/transactionquery.jsp">������Ϣ</a></td>
      </tr>
      <tr>
        <td height="25" align="center" valign="middle"><a href="javascript:isCancle('<%=path%>/userinfo/userinfodelete.jsp')" target="_self">ע���û�</a></td>
      </tr>
	  <tr>
        <td height="25" align="center" valign="middle"><a href="javascript:isUnregister('<%=path%>/logout.do')">�˳���¼</a></td>
      </tr>
    </table></td>
    <td align="center" valign="middle">
	    <table width="97%" border="0" cellspacing="0" cellpadding="0">
	      <tr>
	        <td height="25" align="center" valign="middle">��ӭʹ�������̳����߹���ϵͳv1.0<br/>
			ʹ��˵�����û���½������޸��Լ��ĸ�����Ϣ,�޸Ķ���,ɾ������,<br/>�鿴����������Ϣ��ע���Լ���
			</td>
		   </tr>
		</table>
    </td>
  </tr>
</table>
</div>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
	<p>&nbsp;</p>
  <hr size="2" style="width: 778px;" color="#000000"/>
  <jsp:include flush="true" page="/subpage/foot.jsp"></jsp:include> 
  </body>
</html>



