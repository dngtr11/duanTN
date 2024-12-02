package com.fpoly.serviceImp;

import com.fpoly.dto.request.BlogRequest;
import com.fpoly.dto.response.BlogResponse;
import com.fpoly.entity.Blog;
import com.fpoly.exception.MessageException;
import com.fpoly.mapper.BlogMapper;
import com.fpoly.repository.BlogRepository;
import com.fpoly.servive.BlogService;
import com.fpoly.utils.CommonPage;
import com.fpoly.utils.UserUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Component
public class BlogServiceImp implements BlogService {

    @Autowired
    private BlogRepository blogRepository;

    @Autowired
    private UserUtils userUtils;

    @Autowired
    private BlogMapper blogMapper;

    @Autowired
    private CommonPage commonPage;

    @Override
    public BlogResponse save(BlogRequest request) {
        if(request.getPrimaryBlog() == true){
            blogRepository.unSetPrimary();
        }
        Blog blog = blogMapper.requestToBlog(request);
        blog.setUser(userUtils.getUserWithAuthority());
        blog.setCreatedDate(new Date(System.currentTimeMillis()));
        Blog result = blogRepository.save(blog);
        return blogMapper.blogToResponse(result);
    }

    @Override
    public BlogResponse update(BlogRequest request) {
        if(request.getPrimaryBlog() == true){
            blogRepository.unSetPrimary();
        }
        Blog blog = blogMapper.requestToBlog(request);
        blog.setUser(userUtils.getUserWithAuthority());
        blog.setCreatedDate(new Date(System.currentTimeMillis()));
        Blog result = blogRepository.save(blog);
        return blogMapper.blogToResponse(result);
    }

    @Override
    public void delete(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (blog.isEmpty()){
            throw new MessageException("Blog not found");
        }
        if(blog.get().getPrimaryBlog()){
            throw new MessageException("Blog is primary, can't delete");
        }
        blogRepository.delete(blog.get());
    }

    @Override
    public BlogResponse findById(Long id) {
        Optional<Blog> blog = blogRepository.findById(id);
        if (blog.isEmpty()){
            throw new MessageException("Blog not found");
        }
        return blogMapper.blogToResponse(blog.get());
    }

    @Override
    public BlogResponse blogPrimary() {
        Optional<Blog> blog = blogRepository.blogPrimary();
        if (blog.isEmpty()){
            throw new MessageException("Blog not found");
        }
        return blogMapper.blogToResponse(blog.get());
    }

    @Override
    public Page<BlogResponse> findAll(Pageable pageable) {
        Page<Blog> page = blogRepository.findAll(pageable);
        List<BlogResponse> list = blogMapper.listBlogToResponse(page.getContent());
        Page<BlogResponse> result = commonPage.restPage(page, list);
        return result;
    }
}
