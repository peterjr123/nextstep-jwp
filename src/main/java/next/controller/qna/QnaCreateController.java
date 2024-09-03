package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import next.dao.QuestionDao;
import next.model.Question;
import next.model.User;

public class QnaCreateController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User user = (User) request.getSession().getAttribute("user");
        String title = request.getParameter("title");
        String contents = request.getParameter("contents");

        QuestionDao questionDao = new QuestionDao();

        questionDao.insert(new Question(user.getName(), title, contents));
        return jspView("redirect:/");
    }
}
