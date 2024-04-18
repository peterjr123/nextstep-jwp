package next.web;

import core.db.DataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import next.model.User;

import java.io.IOException;

@WebServlet("/user/login")
public class LoginUserServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = DataBase.findUserById(req.getParameter("userId"));

        String reqPassword = req.getParameter("password");
        if(user != null && user.getPassword().equals(reqPassword)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
        }

        resp.sendRedirect("/index.jsp");
    }
}
