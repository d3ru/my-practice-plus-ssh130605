<%@ page pageEncoding="gb2312"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>错误</title>
    <script type="text/javascript">
    	window.setTimeout("window.location(window.history.go(-1))",2000);
    </script>
  </head>
  
  <body>
    <div style="font-size: 12px;text-align: center;">你的操作有误:${sc_error}!<br>
     页面2秒后自动跳回到上一页,如果你的浏览器没有跳转,<a href="javascript:window.history.go(-1)">请点击这里</a>
    </div>
  </body>
</html>
