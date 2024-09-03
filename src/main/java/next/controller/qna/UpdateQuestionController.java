package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import next.dao.QuestionDao;

public class UpdateQuestionController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        QuestionDao questionDao = new QuestionDao();
        questionDao.updateQuestion(Long.valueOf(request.getParameter("questionId")), request.getParameter("title"), request.getParameter("contents"));
        return jspView("redirect:/");
    }
}
