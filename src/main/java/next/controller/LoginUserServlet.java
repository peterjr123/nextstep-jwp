package next.controller;

import core.db.DataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class LoginUserServlet extends MyServlet {
    private static final Logger log = LoggerFactory.getLogger(LoginUserServlet.class);
    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        User user = DataBase.findUserById(req.getParameter("userId"));

        String reqPassword = req.getParameter("password");
        if(user != null && user.getPassword().equals(reqPassword)) {
            HttpSession session = req.getSession();
            session.setAttribute("user", user);
            log.debug("user login: {}", user);
            return MyServlet.DO_REDIRECT + req.getContextPath() + "/index.jsp";
        }
        else {
            return MyServlet.DO_REDIRECT + "./login_failed.jsp";
        }
    }
}
