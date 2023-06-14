package com.mjc.school.services;

import com.mjc.school.BaseService;
import com.mjc.school.model.implementation.NewsModel;
import com.mjc.school.dto.NewsDtoRequest;
import com.mjc.school.dto.NewsDtoResponse;
import java.util.List;

public interface NewsService extends BaseService<NewsDtoRequest, NewsDtoResponse, Long> {
    List<NewsDtoResponse> getNewsByCriteria(List<String> tagNames, List<Long> tagIds, String authorName, String title, String content, int size, int page, String sort);

    NewsModel dtoRequestToModel(NewsDtoRequest dto);

    NewsDtoResponse modelToDtoResponse(NewsModel model);
}