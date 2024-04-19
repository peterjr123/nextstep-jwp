package next.controller;

import java.io.IOException;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import core.db.DataBase;
import jakarta.servlet.http.HttpSession;

public class ListUserServlet extends MyServlet {
    @Override
    public String doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if(session != null && session.getAttribute("user") != null) {
            req.setAttribute("users", DataBase.findAll());
        }
        return "/user/list.jsp";
    }
}
