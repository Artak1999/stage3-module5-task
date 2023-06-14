package com.mjc.school.interfaces;

import com.mjc.school.model.implementation.AuthorModel;
import com.mjc.school.model.implementation.NewsModel;
import com.mjc.school.model.implementation.TagModel;
import com.mjc.school.dto.NewsDtoRequest;
import com.mjc.school.dto.NewsDtoResponse;
import org.mapstruct.Mapper;
import java.util.List;

@Mapper(componentModel = "spring")
public abstract class NewsMapper {

    public NewsModel dtoRequestToModel(NewsDtoRequest dto) {
        List<TagModel> tags = dto.getTagIds().stream().map(id -> new TagModel(id, "",null)).toList();
        return new NewsModel(dto.getId(), dto.getTitle(), dto.getContent(), null, null, new AuthorModel(dto.getAuthorId(), "", null, null,null), tags);
    }

    public NewsDtoResponse modelToDtoResponse(NewsModel model) {
        return new NewsDtoResponse(model.getId(), model.getTitle(), model.getContent(), model.getCreateDate(), model.getLastUpdateDate(), model.getAuthor().getId());
    }

    public abstract List<NewsDtoResponse> listOfModelsToListOfResponses(List<NewsModel> modelList);
}
