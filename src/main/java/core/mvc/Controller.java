package core.mvc;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import next.model.ModelAndView;

public interface Controller {
    ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception;
}
