package next.web;

import core.db.DataBase;
import jakarta.servlet.RequestDispatcher;
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

@WebServlet("/user/update")
public class UpdateUserServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(UpdateUserServlet.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect("./update.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User updatedUser = new User(req.getParameter("userId"), req.getParameter("password"), req.getParameter("name"),
                req.getParameter("email"));

        HttpSession session = req.getSession();
        User originalUser = (User) session.getAttribute("user");

        // update user info in session&DB
        DataBase.deleteUser(originalUser.getUserId());
        DataBase.addUser(updatedUser);
        session.setAttribute("user", updatedUser);

        resp.sendRedirect("./list");
    }
}
