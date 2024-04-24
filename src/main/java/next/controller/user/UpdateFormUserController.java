package next.controller.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import core.mvc.Controller;
import next.controller.UserSessionUtils;
import next.dao.UserDao;
import next.model.ModelAndView;
import next.model.User;

public class UpdateFormUserController implements Controller {

    @Override
    public ModelAndView execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
        String userId = req.getParameter("userId");
        UserDao userDao = new UserDao();
        User user = userDao.findByUserId(userId);
        if (!UserSessionUtils.isSameUser(req.getSession(), user)) {
            throw new IllegalStateException("다른 사용자의 정보를 수정할 수 없습니다.");
        }

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setAttribute("user", user);
        modelAndView.setViewName("user/updateForm.jsp");
        return modelAndView;
    }
}
