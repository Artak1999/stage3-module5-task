package com.mjc.school.interfaces;

import com.mjc.school.model.implementation.CommentModel;
import com.mjc.school.dto.CommentDtoRequest;
import com.mjc.school.dto.CommentDtoResponse;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CommentMapper {
    @Mapping(target = "created", ignore = true)
    @Mapping(target = "modified", ignore = true)
    CommentModel dtoRequestToModel(CommentDtoRequest createRequest);
    CommentDtoResponse modelToDtoResponse(CommentModel model);
    List<CommentDtoResponse> listOfModelsToListOfResponses(List<CommentModel> model);
}
