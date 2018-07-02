package xyz.yisheng.blog.dao;


import xyz.yisheng.blog.model.Article;
import priv.framework.jdbc.ActiveRecord;

import java.sql.*;
import java.util.List;


/**
 * Created by easom on 2017/9/8.
 */
public class ArticleDao {
    public void saveAticle(Article article) throws Exception{
       /* String sql=""+
                "insert into article" +
                "(content,title)"+
                "values("+
                "?,?)";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setString(1,article.getContent());
        ptmt.setString(2,article.getTitle());
        ptmt.execute();*/
        ActiveRecord activeRecord=new ActiveRecord();
        activeRecord.save(article);
    }
    public List<Article> listAticle()throws Exception{
      /*  List<Article> result=new ArrayList<Article>();
        Connection conn =DBUtil.getConnection();
        //全部取出
        String sql="select * from article";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ResultSet rs=ptmt.executeQuery();
        Article article=null;
        while (rs.next()){
            article=new Article();
            article.setContent(rs.getString("content"));
            article.setTitle(rs.getString("title"));
            article.setBid(rs.getInt("bid"));
            result.add(article);
        }
        return result;*/
        ActiveRecord activeRecord=new ActiveRecord();
        Article a=new Article();
        List<Article>  result=activeRecord.getAll(a);
        return result;
    }

    public void delete(Integer bid) throws SQLException {
  /*      Connection connection=DBUtil.getConnection();
        String sql="" +
                " delete from article" +
                " where bid=? ";
        PreparedStatement ptmt=connection.prepareStatement(sql);
        ptmt.setInt(1, bid);
        ptmt.execute();*/
        ActiveRecord activeRecord=new ActiveRecord();
        Article article=new Article();
        activeRecord.find(article).delete("bid",bid);
    }

    //用id查看文章
    public Article show(Integer bid) throws SQLException {
       /* Connection connection=DBUtil.getConnection();
        String sql="" +
                " select * from article" +
                " where bid=? ";

        PreparedStatement ptmt=connection.prepareStatement(sql);
        ptmt.setInt(1, bid);
        ResultSet rs=ptmt.executeQuery();
        Article article=new Article();
        while (rs.next()) {
            article.setContent(rs.getString("content"));
            article.setTitle(rs.getString("title"));
            article.setBid(rs.getInt("bid"));
        }
        return article;*/
        ActiveRecord activeRecord=new ActiveRecord();
        Article article= activeRecord.get(new Article(),bid);
        return article;
    }

    public void updateAticle(Article article) throws SQLException {
  /*    Connection conn= DBUtil.getConnection();
        String sql=""+
                " update article" +
                " set content=?,title=? "+
                " where bid= ? ";
        PreparedStatement ptmt=conn.prepareStatement(sql);
        ptmt.setString(1,article.getContent());
        ptmt.setString(2,article.getTitle());
        ptmt.setInt(3,article.getBid());
        ptmt.execute(); */
        ActiveRecord activeRecord=new ActiveRecord();
        activeRecord.update(article);

    }
}
