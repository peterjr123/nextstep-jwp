package next.controller;

import core.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import next.dao.QuestionDao;
import next.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

public class CreateQuestionController implements Controller {
    private static final Logger log = LoggerFactory.getLogger(CreateQuestionController.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Question question = new Question(0, req.getParameter("writer"), req.getParameter("title"),
                req.getParameter("contents"), new Date(), 0);
        log.debug("Question: {}", question);
        QuestionDao questionDao = new QuestionDao();
        questionDao.insert(question);
        return "redirect:/";
    }
}
