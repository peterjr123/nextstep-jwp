package next.view;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public interface View {
    public void createResponse(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
