package org.scoula.ex05;
//6슬라이드 - 다음과 같이 ScopeServlet을 작성하세요.


import org.scoula.ex05.domain.Member;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;


@WebServlet("/scope")
public class ScopeServlet extends HttpServlet {
    ServletContext sc; //이녀석의 용도는 서블릿 컨테이너 내의 전역정보(전역변수+어플리케이션 설정값)를 관리하기 위함.

    @Override
    public void init(ServletConfig config) throws ServletException {
        sc = config.getServletContext();
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        sc.setAttribute("scopeName", "applicationScope 값"); // Application Scope

        HttpSession session = req.getSession(); // Session Scope
        session.setAttribute("scopeName", "sessionScope 값");

        req.setAttribute("scopeName", "requestScope 값"); // Request Scope
        Member member = new Member("홍길동", "hong");
        req.setAttribute("member", member);

        req.getRequestDispatcher("scope.jsp").forward(req, resp);
        //System.out.println("ScopeServlet doGet");
    }
}
