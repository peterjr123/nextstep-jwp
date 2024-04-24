package next.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.dao.UserDao;
import next.model.ModelAndView;
import next.model.User;

public class ProfileController implements Controller {
    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);
        if (user == null) {
            throw new NullPointerException("사용자를 찾을 수 없습니다.");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setAttribute("user", user);
        modelAndView.setViewName("user/profile.jsp");
        return modelAndView;
    }
}
