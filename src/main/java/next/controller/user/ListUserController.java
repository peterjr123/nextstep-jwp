package next.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.controller.UserSessionUtils;
import next.dao.UserDao;
import next.model.ModelAndView;

public class ListUserController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        if (!UserSessionUtils.isLogined(req.getSession())) {
            return new ModelAndView("redirect:/users/loginForm");
        }

        ModelAndView modelAndView = new ModelAndView();
        UserDao userDao = new UserDao();
        modelAndView.setAttribute("users", userDao.findAll());
        modelAndView.setViewName("/user/list.jsp");
        return modelAndView;
    }
}
