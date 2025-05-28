package org.scoula.ex02;

import java.io.*;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.annotation.*;

//@WebServlet(name = "helloServlet", value = "/hello-servlet")  --슬라이드7, @WebServlet을주석처리한후, HelloServlet이 동작하도록 web.xml을 수정.
@WebServlet(name = "MyServlet", urlPatterns={"/xxx", "/yyy" }) // -- 슬라이드8, 다음 두url에 HelloServlet이 맵핑되도록 @WebServlet을 설정하세요.
public class HelloServlet extends HttpServlet {
    private String message;

    public void init() {
        message = "Hello World!";
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Hello
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        out.println("<h1>" + message + "</h1>");
        out.println("</body></html>");
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        System.out.println("init 호출");
    }

    @Override
    public void destroy() {
        System.out.println("destroy 호출");
    }
}