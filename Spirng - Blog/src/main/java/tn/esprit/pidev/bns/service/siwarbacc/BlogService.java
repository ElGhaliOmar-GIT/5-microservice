package tn.esprit.pidev.bns.service.siwarbacc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.esprit.pidev.bns.entity.siwarbacc.Blog;
import tn.esprit.pidev.bns.repository.siwarbacc.BlogRepository;
import tn.esprit.pidev.bns.serviceInterface.siwarbacc.IBlogService;

import java.util.List;

@Slf4j
@Service
public class BlogService implements IBlogService {
    @Autowired
    BlogRepository blogRepository;
    public List<Blog> retrieveAllBlogs (){
        return(List<Blog>) blogRepository.findAll();
    }
    public Blog updateBlog (Blog b) {return blogRepository.save(b);}
    public Blog addBlog (Blog b) {return blogRepository.save(b);}
    public Blog retrieveBlog ( Integer id) { return blogRepository.findById(id).orElse( null);}

    @Override
    public void removeBlog(Integer id) {
        Blog b=retrieveBlog(id);
        blogRepository.delete(b);
    }

    // public List<Blog> findBlogsWithSorting(String field){
       //return blogRepository.findAll(Sort.by(Sort.Direction.ASC,field));}

    //public Page<Blog> findBlogsWithPagination(int offset,int pageSize){
        //Page<Blog> blogs = blogRepository.findAll(PageRequest.of(offset, pageSize));
        //return  blogs;
    //





}




