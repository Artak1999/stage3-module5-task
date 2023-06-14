package com.mjc.school.interfaces;

import com.mjc.school.model.implementation.TagModel;
import com.mjc.school.dto.TagDtoRequest;
import com.mjc.school.dto.TagDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TagMapper {

    @Mapping(target = "news", ignore = true)
    TagModel dtoRequestToModel(TagDtoRequest dto);

    TagDtoResponse modelToDtoResponse(TagModel model);

    List<TagDtoResponse> listOfModelsToListOfResponses(List<TagModel> modelList);
}
