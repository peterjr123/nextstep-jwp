package next.controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.QuestionDao;
import next.model.ModelAndView;

public class HomeController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        QuestionDao questionDao = new QuestionDao();

        modelAndView.setAttribute("questions", questionDao.findAll());
        modelAndView.setViewName("home.jsp");
        return modelAndView;
    }
}
