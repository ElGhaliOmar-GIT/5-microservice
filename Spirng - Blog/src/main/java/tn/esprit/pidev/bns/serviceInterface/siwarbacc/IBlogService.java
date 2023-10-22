package tn.esprit.pidev.bns.serviceInterface.siwarbacc;

import tn.esprit.pidev.bns.entity.siwarbacc.Blog;

import java.util.List;

public interface IBlogService {
 public List<Blog> retrieveAllBlogs();
  public   Blog updateBlog (Blog b);
   public Blog addBlog (Blog b);
   public  Blog retrieveBlog ( Integer id);
   public void removeBlog(Integer id);


}
