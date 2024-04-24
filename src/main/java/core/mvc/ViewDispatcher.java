package core.mvc;

import next.model.ModelAndView;
import next.view.JsonView;
import next.view.JspView;
import next.view.View;

public class ViewDispatcher {
    public static View findView(ModelAndView modelAndView) {
        String viewName = modelAndView.getViewName();
        if(viewName.endsWith(".jsp")) {
            return new JspView(modelAndView.getModel(), viewName);
        }
        else {
            return new JsonView(modelAndView.getModel());
        }
    }
}
