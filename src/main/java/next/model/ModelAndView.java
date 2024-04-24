package next.model;

import next.view.JsonView;
import next.view.JspView;
import next.view.View;

import java.util.HashMap;
import java.util.Map;

public class ModelAndView {
    Map<String, Object> model = new HashMap<String, Object>();
    String viewName = "";

    public ModelAndView() {

    }
    public ModelAndView(String viewName) {
        this.viewName = viewName;
    }


    public Map<String, Object> getModel() {
        return model;
    }

    public void setAttribute(String name, Object value) {
        model.put(name, value);
    }

    public void setViewName(String viewName) {
        this.viewName = viewName;
    }
    public String getViewName() {
        return viewName;
    }

    public View getView() {
        if(viewName.endsWith(".jsp")) {
            return new JspView(model, viewName);
        }
        else {
            return new JsonView(model);
        }
    }
}
