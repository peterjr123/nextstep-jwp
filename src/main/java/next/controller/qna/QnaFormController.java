package next.controller.qna;

import core.mvc.AbstractController;
import core.mvc.ModelAndView;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import next.model.User;

public class QnaFormController extends AbstractController {
    @Override
    public ModelAndView execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ModelAndView mv = jspView("/qna/form.jsp");

        // 1. update form
        if(request.getParameter("questionId") != null) {
            mv.addObject("questionId", request.getParameter("questionId"));
            return mv;
        }

        // 2. create form
        User user = (User) request.getSession().getAttribute("user");
        if(user == null) {
            return jspView("redirect:/users/loginForm");
        }

        mv.addObject("userName", user.getName());
        return mv;
    }
}
