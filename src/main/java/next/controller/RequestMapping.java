package next.controller;

import java.util.HashMap;
import java.util.Map;

public class RequestMapping {
    private static Map<String, MyServlet> servletMap = new HashMap<>();
    static {
        servletMap.put("/user/create", new CreateUserServlet());
        servletMap.put("/user/list", new ListUserServlet());
        servletMap.put("/user/login", new LoginUserServlet());
        servletMap.put("/user/logout", new LogoutUserServlet());
        servletMap.put("/user/profile", new ProfileServlet());
        servletMap.put("/user/update", new UpdateUserServlet());
    }

    public static MyServlet getServlet(String url) {
        return servletMap.get(url);
    }
}
