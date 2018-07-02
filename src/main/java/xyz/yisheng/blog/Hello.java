package xyz.yisheng.blog;

import priv.framework.Tidy;
import java.io.PrintWriter;

public class Hello {
    public static void main(String[] args) throws Exception {
        Tidy tidy=new Tidy();

        //使用servlet开发是怎么样的，这是不是相当于一个servlet?
        tidy.get("/hello",(req, resp) -> {
            PrintWriter out= resp.getWriter();
            out.println("hello world!");
        });

        tidy.start();
    }
}
