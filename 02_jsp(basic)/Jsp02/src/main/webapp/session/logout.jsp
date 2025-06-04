<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page contentType="text/html"%>
<%
    session.invalidate();
    response.sendRedirect("loginForm");
%>
