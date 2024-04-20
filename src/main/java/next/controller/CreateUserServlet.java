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


public class CreateUserServlet extends MyServlet {
    private static final Logger log = LoggerFactory.getLogger(CreateUserServlet.class);

    @Override
    public String doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        User user = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));
        log.debug("create user : {}", user);

        DataBase.addUser(user);
        return MyServlet.DO_REDIRECT + "./list";
    }
}
