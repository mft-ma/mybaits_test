<%--
  Created by IntelliJ IDEA.
  User: mft
  Date: 2020/11/10
  Time: 9:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>load</title>
</head>
<script  src="${pageContext.request.contextPath}/js/jquery-3.3.1.js"></script>
<script type="text/javascript">

</script>
<body>
<form action="${pageContext.request.contextPath}/mybatis/toLoad" method="post">
    <center>
    姓名：<input type="text" name="username" id="username"><br>
    密码：<input type="password" name="password"><br>
    <input type="submit" value="登录" id="load">
    </center>
</form>
</body>
</html>
