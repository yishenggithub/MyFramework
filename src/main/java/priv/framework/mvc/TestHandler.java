package priv.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by easom on 2017/11/14.
 */
public interface TestHandler {
    void handle(HttpServletRequest req, HttpServletResponse resp);
}
