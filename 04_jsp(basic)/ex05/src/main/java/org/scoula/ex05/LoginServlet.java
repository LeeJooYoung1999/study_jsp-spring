package org.scoula.ex05;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

//다음 설정으로LoginServlet을 작성하세요.
// ○ 패키지: org.scoula.ex05
// ○ url 맵핑: /login
// ○ userid, passwd 파라미터를 추출하여 request 스코프에 저장
// ○ login.jsp로 포워딩
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        String userid = req.getParameter("userid");
        String passwd = req.getParameter("passwd");

        req.setAttribute("userid", userid);
        req.setAttribute("passwd", passwd);

        req.getRequestDispatcher("login.jsp").forward(req, res);
    }
}
