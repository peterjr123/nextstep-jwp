package next.controller;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

@WebServlet(urlPatterns = "/", loadOnStartup = 1)
public class DispatcherServlet extends HttpServlet {
    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String url = req.getRequestURI().substring(req.getContextPath().length());
        MyServlet servlet = RequestMapping.getServlet(url);

        String responseUrl = servlet.execute(req, resp);
        if(responseUrl.startsWith(MyServlet.DO_REDIRECT)) {
            resp.sendRedirect(responseUrl.substring(MyServlet.DO_REDIRECT.length()));
        }
        else {
            RequestDispatcher dispatcher = req.getRequestDispatcher(responseUrl);
            dispatcher.forward(req, resp);
        }
    }
}
