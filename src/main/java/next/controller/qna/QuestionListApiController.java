package next.controller.qna;

import core.jdbc.DataAccessException;
import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.Result;

import java.util.List;

public class QuestionListApiController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mav = jsonView();

        QuestionDao questionDao = new QuestionDao();
        List<Question> questions = questionDao.findAll();
        mav.addObject("questions", questions);
        return mav;
    }
}
