<%--
  다음과 같이 login.jsp를 작성하여 Scope에 저장된 데이터를 출력한다.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
  <h1>EL 실습</h1>
  사용자 아이디 : ${userid}<br>
  사용자 비밀번호 : ${passwd}<br>
</body>
</html>
