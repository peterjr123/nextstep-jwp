package next.controller;

import core.db.DataBase;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import next.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@WebServlet("/user/create")
public class CreateUserServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(CreateUserServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));
        log.debug("user : {}", user);

        DataBase.addUser(user);
        resp.sendRedirect("./list");
    }
}
