package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import next.dao.AnswerDao;
import next.model.ModelAndView;
import next.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DeleteAnswerController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(DeleteAnswerController.class);

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        AnswerDao answerDao = new AnswerDao();
        int answerId = Integer.parseInt(req.getParameter("answerId"));
        log.debug("answerId: {}", answerId);

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setAttribute("result", Result.ok());
        return modelAndView;
    }
}
