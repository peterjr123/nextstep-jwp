package next.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import core.mvc.Controller;
import next.controller.UserSessionUtils;
import next.dao.UserDao;
import next.model.ModelAndView;
import next.model.User;

public class LoginController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        String password = req.getParameter("password");
        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);
        ModelAndView modelAndView = new ModelAndView();
        if (user == null) {
            modelAndView.setAttribute("loginFailed", true);
            modelAndView.setViewName("/user/login.jsp");
            return modelAndView;
        }
        if (user.matchPassword(password)) {
            HttpSession session = req.getSession();
            session.setAttribute(UserSessionUtils.USER_SESSION_KEY, user);
            modelAndView.setViewName("redirect:/");
            return modelAndView;
        } else {
            modelAndView.setAttribute("loginFailed", true);
            modelAndView.setViewName("/user/login.jsp");
            return modelAndView;
        }
    }
}
