package com.mjc.school.services;

import com.mjc.school.BaseService;
import com.mjc.school.dto.CommentDtoRequest;
import com.mjc.school.dto.CommentDtoResponse;

import java.util.List;

public interface CommentService extends BaseService<CommentDtoRequest, CommentDtoResponse, Long> {
    List<CommentDtoResponse> getCommentsByNewsId(Long id);
    List<CommentDtoResponse>getTagsByNewsId(Long id);
}
