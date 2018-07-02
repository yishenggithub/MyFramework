package priv.framework.mvc;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by easom on 2017/11/13.
 */
@FunctionalInterface
public interface RouteHandler {
    //todo还不封装req，resp
    void handle(HttpServletRequest req, HttpServletResponse resp) throws Exception;

}

