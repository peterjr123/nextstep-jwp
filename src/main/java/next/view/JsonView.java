package next.view;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import next.controller.user.CreateUserController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.Map;

public class JsonView implements View{
    Map<String, Object> model;

    private static final Logger log = LoggerFactory.getLogger(JsonView.class);

    public JsonView(Map<String, Object> model) {
        this.model = model;
    }

    @Override
    public void createResponse(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        String json = createJson();
        log.debug("json: {}", json);
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().print(json);
    }

    private String createJson() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        if(model.size() == 1) {
            for(String key : model.keySet()) {
                return mapper.writeValueAsString(model.get(key));
            }
        }
        return mapper.writeValueAsString(model);
    }
}
