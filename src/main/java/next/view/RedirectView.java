package next.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class RedirectView implements View{
    public static final String DEFAULT_REDIRECT_PREFIX = "redirect:";

    String path;
    public RedirectView(String path) {
        this.path = path;
    }
    @Override
    public void createResponse(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        resp.sendRedirect(req.getContextPath() + path.substring(DEFAULT_REDIRECT_PREFIX.length()));
    }
}
