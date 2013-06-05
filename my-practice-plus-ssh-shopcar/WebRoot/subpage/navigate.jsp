<%@ page  pageEncoding="gb2312"%>
<%String path = request.getContextPath(); %>
   <div align="center">
  <center>
  <table border="1" width="800" style="border-left-style: solid; border-left-color: #FFFFFF; border-right-style: solid; border-right-color: #FFFFFF; border-top-style: solid; border-top-color: #000000; border-bottom-style: solid; border-bottom-color: #000000" background="<%=path%>/images/2.gif" cellspacing="0" cellpadding="0">
    <tr>
     <td width="100%" align="right">
     <div id="title" align="right" style="margin-right: 10px;">
      <font color="red">
      <%if(session.getAttribute("uname")!=null){ 
      		out.println(session.getAttribute("uname").toString()); 
      	} 
      %></font>
            <SCRIPT language=javascript src="<%=path%>/js/date.js" type=text/javascript></SCRIPT>
            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
            <b>|</b>&nbsp;<a href="<%=path%>/index.jsp">首页</a>
     <%if(session.getAttribute("uname")!=null){
 		out.println("<b>|</b>&nbsp;<a href='"+path+"/userinfo/userinfo.jsp' target='_self'>用户信息维护&nbsp;<b>|</b>&nbsp;<a href='"+path+"/logout.do'>注销</a>");    
     } %>
     <b>|</b>&nbsp;<a href="<%=path%>/login/userlogin.jsp">登录</a>&nbsp;<b>|</b>&nbsp;<a href="<%=path%>/registry.jsp">注册</a>&nbsp;<b>|</b>&nbsp;<label style="color: red;font-weight: bold;" onclick="showShopWindow();">购物车</label></div>
     </td>
    </tr>
  </table>
  </center>
</div>
