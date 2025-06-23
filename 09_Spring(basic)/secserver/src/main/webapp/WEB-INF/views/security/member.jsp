<%--
  Created by IntelliJ IDEA.
  User: student
  Date: 25. 6. 23.
  Time: 오전 10:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>member.jsp</title>
</head>
<body>
<h1>멤버이상만 접근가능 : member.jsp</h1>

<form action="/security/logout" method="post">
    <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    <input type="submit" value="로그아웃"/>
</form>
</body>
</html>
