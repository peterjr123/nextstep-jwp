package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import next.dao.AnswerDao;
import next.dao.QuestionDao;
import next.model.Answer;
import next.model.Question;
import next.model.Result;

import java.util.List;

public abstract class DeleteController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {

        QuestionDao questionDao = new QuestionDao();
        AnswerDao answerDao = new AnswerDao();
        Long questionId = Long.parseLong(request.getParameter("questionId"));
        Question question = questionDao.findById(questionId);
        List<Answer> answers = answerDao.findAllByQuestionId(questionId);

        if(answers != null) {
            for(Answer answer : answers) {
                if(answer.getWriter() != question.getWriter()) {
                    return onFail();
                }
            }
        }


        for(Answer answer : answers) {
            answerDao.delete(answer.getQuestionId());
        }
        questionDao.deleteQuestion(questionId);
        return onSuccess();
    }

    protected abstract ModelAndView onSuccess();
    protected abstract ModelAndView onFail();
}
