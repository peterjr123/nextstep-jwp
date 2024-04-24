package next.controller.qna;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.ModelAndView;

public class ShowController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        Long questionId = Long.parseLong(req.getParameter("questionId"));
        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setAttribute("question", questionDao.findById(questionId));
        modelAndView.setAttribute("answers", answerDao.findAllByQuestionId(questionId));
        modelAndView.setViewName("/qna/show.jsp");
        return modelAndView;
    }
}
