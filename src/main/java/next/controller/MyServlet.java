package next.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public abstract class MyServlet {
    public static final String DO_REDIRECT = "redirect:";
    private static final String METHOD_POST = "POST";
    private static final String METHOD_GET = "GET";

    final String execute(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String method = req.getMethod();
        if(method.equals(METHOD_GET)) {
            return doGet(req, resp);
        }
        else if(method.equals(METHOD_POST)) {
            return doPost(req, resp);
        }
        return doGet(req, resp);
    }

    String doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return null;
    }

    String doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return null;
    }
}
