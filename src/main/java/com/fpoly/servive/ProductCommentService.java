package com.fpoly.servive;

import com.fpoly.dto.request.CommentRequest;
import com.fpoly.dto.response.ProductCommentResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductCommentService {

    public ProductCommentResponse create(CommentRequest commentRequest);

    public ProductCommentResponse update(CommentRequest commentRequest);

    public void delete(Long id);

    public ProductCommentResponse findById(Long productId);

    public List<ProductCommentResponse> findByProductId(Long productId);
}
