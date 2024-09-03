package next.controller.qna;

import core.mvc.ModelAndView;
import next.model.Result;


public class ApiDeleteController extends DeleteController {
    @Override
    protected ModelAndView onSuccess() {
        ModelAndView mv = new ModelAndView();
        mv.addObject("result", Result.ok());
        return mv;
    }

    @Override
    protected ModelAndView onFail() {
        ModelAndView mv = jsonView();
        mv.addObject("result", Result.fail("cannot delete QnA with comments"));
        return mv;
    }
}
