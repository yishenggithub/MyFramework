package controller;

import dao.ArticleDao;
import model.Article;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by easom on 2017/8/16.
 */
public class EditController {

    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws IOException, SQLException {
      //  PrintWriter out = response.getWriter();
       // String callback = request.getParameter("title");
      //  System.out.print("++"+callback);

        if( request.getParameter("bid")!=null) {
            Integer bid=  Integer.parseInt(String.valueOf(request.getParameter("bid")));

            ArticleDao articleDao = new ArticleDao();

            Article article = articleDao.show(bid);

            request.setAttribute("article", article);
        }
        return "/view/index2.jsp";
    }
}
