package xyz.yisheng.blog.service;

import priv.framework.jdbc.ActiveRecord;
import priv.framework.mvc.annotation.Service;
import xyz.yisheng.blog.model.Blog;

import java.util.List;

@Service
public class BlogDAO  {

    Blog blog=new Blog();

    public void save(Blog blog) {
        ActiveRecord activeRecord =new ActiveRecord();
        activeRecord.save(blog);
    }

    public void update(Blog blog) {
        ActiveRecord activeRecord =new ActiveRecord();
        activeRecord.update(blog);
    }

    public void delete(int bid) {
        ActiveRecord activeRecord =new ActiveRecord();
        activeRecord.find(blog).delete("bid",bid);
    }

    public Blog findByBid(int key) {
        ActiveRecord activeRecord =new ActiveRecord();
        return activeRecord.get(blog,key);
    }

    public List<Blog> findAll() {
        ActiveRecord activeRecord =new ActiveRecord();
        return activeRecord.getAll(blog);
    }

    public List<Blog> findByUid(int uid) {
        ActiveRecord activeRecord =new ActiveRecord();
        blog.setUid(uid);
        return activeRecord.get(blog);
    }

    //批量删除，@param flag 删除的标号
    public void deleteBatch(Integer[] flag) {

    }

    //分页，@param limit每页分几个条目，@param currentPage 当前第几页，@return 返回分页后的n个Blog对象
  /*  public List<Blog> paging(int limit, int currentPage) {
        return this.getHibernateTemplate().execute(new HibernateCallback<List<Blog>>() {
            @Transactional
            public List<Blog> doInHibernate(Session session) throws HibernateException {
                Query query = session.createQuery("from Blog");
                query.setFirstResult((limit) * (currentPage - 1));
                query.setMaxResults(limit);
                return query.list();
            }
        });
    }*/
}
