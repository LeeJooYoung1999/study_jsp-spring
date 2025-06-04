<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<html>
<head>
  <title>로그인 정보 출력</title>
</head>
<body>
<h1>로그인 입력 파라미터 출력</h1>

<%-- 클라이언트가 입력한 아이디와 비밀번호를 request 객체에서 가져옴 --%>
<%
  String userid = request.getParameter("userid");
  String password = request.getParameter("password");
%>

<p>아이디값: <%= userid %></p>
<p>비밀번호: <%= password %></p>

</body>
</html>
