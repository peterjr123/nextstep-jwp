package next.controller;

import core.mvc.Controller;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class QuestionDetailsServlet implements Controller {
    private static final Logger log = LoggerFactory.getLogger(QuestionDetailsServlet.class);

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        int questionId = Integer.parseInt(req.getParameter("questionId"));
        QuestionDao questionDao = new QuestionDao();
        Question question = questionDao.getQuestionById(questionId);
        req.setAttribute("question", question);

        AnswerDao answerDao = new AnswerDao();
        List<Answer> answerList = answerDao.findAnswer(questionId);
        req.setAttribute("answers", answerList);

        log.debug("contents: {}", question.getContents());
        return "/qna/show.jsp";
    }
}
