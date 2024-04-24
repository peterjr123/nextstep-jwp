package next.controller.qna;

import com.fasterxml.jackson.databind.ObjectMapper;
import core.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import next.controller.user.CreateUserController;
import next.dao.AnswerDao;
import next.model.Answer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AddAnswerController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(AddAnswerController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Answer answer = new Answer(req.getParameter("writer"), req.getParameter("contents"),
                Long.parseLong(req.getParameter("questionId")));
        log.debug("answer: {}", answer);

        AnswerDao answerDao = new AnswerDao();
        Answer savedAnswer = answerDao.insert(answer);
        ObjectMapper mapper = new ObjectMapper();
        resp.setContentType("application/json");
        resp.getWriter().print(mapper.writeValueAsString(savedAnswer));
        return null;
    }
}
