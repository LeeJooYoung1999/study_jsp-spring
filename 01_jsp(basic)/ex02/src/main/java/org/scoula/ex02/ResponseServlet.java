package org.scoula.ex02;
//ResponseServlet 클래스를추가하고,/response요청에 대해 다음처럼 한글응답을 내보내세요.
    //(한글이 깨지지 않도록 문자셋을 설정하세요)
//ResponseServlet 요청 성공

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "ResponseServlet", value = "/response")
public class ResponseServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8"); //한글깨짐방지 - UTF-8문자셋 설정
        PrintWriter out = response.getWriter();

        out.println("<html><body>");
        out.println("<h1>ResponseServlet 요청성공</h1>");
        out.println("<body><html>");
    }//doGet
}//class
