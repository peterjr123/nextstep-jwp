package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;

import java.util.List;

public class QnaDeleteController extends DeleteController {

    @Override
    protected ModelAndView onSuccess() {
        return jspView("redirect:/");
    }

    @Override
    protected ModelAndView onFail() {
        return jspView("redirect:/");
    }
}
