package xyz.yisheng.blog.controller;
import priv.framework.mvc.annotation.Action;
import priv.framework.mvc.annotation.Controller;
import priv.framework.mvc.bean.Param;
import priv.framework.mvc.bean.View;
import java.io.PrintWriter;

@Controller
public class HelloController {
    @Action("get:/hello")
    public View hello(Param param) throws Exception {
        PrintWriter out=param.getResponse().getWriter();
        out.println("hello world form Annotation!");
        return null;
    }
}
