package com.mjc.school.services;

import com.mjc.school.BaseService;
import com.mjc.school.dto.TagDtoRequest;
import com.mjc.school.dto.TagDtoResponse;
import java.util.List;

public interface TagService extends BaseService<TagDtoRequest, TagDtoResponse, Long> {
    List<TagDtoResponse> getTagsByNewsId(Long id);
}
