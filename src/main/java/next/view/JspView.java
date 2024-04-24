package next.view;

import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Map;

public class JspView implements View{
    Map<String, Object> model;
    String jspPath;

    public JspView(Map<String, Object> model, String jspPath) {
        this.model = model;
        this.jspPath = jspPath;
    }

    @Override
    public void createResponse(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        setAttributes(req);
        RequestDispatcher dispatcher = req.getRequestDispatcher(jspPath);
        dispatcher.forward(req, resp);
    }

    private void setAttributes(HttpServletRequest req) {
        for(String key : model.keySet()) {
            req.setAttribute(key, model.get(key));
        }
    }
}
