package com.mjc.school.services;

import com.mjc.school.BaseService;
import com.mjc.school.dto.AuthorDtoResponse;
import com.mjc.school.dto.AuthorDtoRequest;


public interface AuthorService extends BaseService<AuthorDtoRequest, AuthorDtoResponse, Long> {
    AuthorDtoResponse getAuthorByNewsId(Long id);
}
