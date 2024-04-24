package core.mvc;

import next.model.ModelAndView;
import next.view.JsonView;
import next.view.JspView;
import next.view.RedirectView;
import next.view.View;

public class ViewDispatcher {
    public static View findView(ModelAndView modelAndView) {
        String viewName = modelAndView.getViewName();
        if(viewName.startsWith(RedirectView.DEFAULT_REDIRECT_PREFIX)) {
            return new RedirectView(viewName);
        }
        else if(viewName.endsWith(".jsp")) {
            return new JspView(modelAndView.getModel(), viewName);
        }
        else {
            return new JsonView(modelAndView.getModel());
        }
    }
}
