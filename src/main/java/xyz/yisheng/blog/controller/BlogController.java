package xyz.yisheng.blog.controller;

import priv.framework.mvc.annotation.Action;
import priv.framework.mvc.annotation.Controller;
import priv.framework.mvc.annotation.Inject;
import priv.framework.mvc.bean.Data;
import priv.framework.mvc.bean.Param;
import priv.framework.mvc.bean.View;
import xyz.yisheng.blog.model.Blog;
import xyz.yisheng.blog.service.BlogDAO;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/4/3.
 * 控制层
 */
@Controller
public class BlogController {
    @Inject
    private BlogDAO blogDAO;

    private static String textParser(String source) {
        if (source == null) {
            return "";
        }
        StringBuilder buffer = new StringBuilder();
        for (int i = 0; i < source.length(); i++) {
            char c = source.charAt(i);
            switch (c) {
                case '<':
                    buffer.append("&lt;");
                    break;
                case '>':
                    buffer.append("&gt;");
                    break;
                case '&':
                    buffer.append("&amp;");
                    break;
                case '"':
                    buffer.append("&quot;");
                    break;
                case '\n':
                    buffer.append("<br/>");
                    break;
                default:
                    buffer.append(c);
            }
        }
        return buffer.toString();
    }

    @Action("post:/addArticle")
    public Data add(Param param) throws Exception {
        Map<String, String> res = new HashMap();
        Blog blog = new Blog();
        blog.setTitle(param.getString("title"));
        blog.setContent(param.getString("content"));
        blog.setUid(param.getInt("uid"));
        blogDAO.save(blog);
        res.put("result", "SUCCESS");
        return new Data(res);
    }

    @Action("get:/articleList")
    public View getAllBlog(Param param) throws Exception {
        // 查询表中所有记录
        // 将所有记录传递给要返回的jsp页面，放在blogList当中
        int uid=param.getInt("uid");

        if (blogDAO.findByUid(uid) == null) {
            return new View("index.jsp");
        } else {
            List<Blog>bloglist=blogDAO.findByUid(uid);
            // 返回原本jsp页面
            return new View("articleList.jsp").addModel("blogList",bloglist);

        }
    }

    @Action("get:/show")
    public View get(Param param) throws Exception {
        return new View("article.jsp").addModel("blog",blogDAO.findByBid(param.getInt("bid")));
    }

    /**
     * 转到update的目录
     * @param param
     * @return 页面
     * @throws Exception
     */
    @Action("get:/toupdate")
    public View toUpdatePage(Param param) throws Exception {
        Blog blog=blogDAO.findByBid(param.getInt("bid"));
        return new View("articleUpdate.jsp").addModel("blog",blog);
    }

    @Action("post:/update")
    public View update(Param param) throws Exception {

        Blog blog=new Blog();
        blog.setBid(param.getInt("bid"));
        blog.setContent(param.getString("content"));
        blog.setTitle(param.getString("title"));
        blog.setUid(param.getInt("uid"));
        blogDAO.update(blog);
        return new View("articleList.jsp");
    }

    @Action(value = "get:/delete")
    public View delete(Param param) throws Exception {
        blogDAO.delete(param.getInt("bid"));
        return new View("index.jsp");
    }

}