package com.mjc.school.interfaces;

import com.mjc.school.dto.AuthorDtoResponse;
import com.mjc.school.model.implementation.AuthorModel;
import com.mjc.school.dto.AuthorDtoRequest;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AuthorMapper {

    @Mapping(target = "createDate", ignore = true)
    @Mapping(target = "lastUpdateDate", ignore = true)
    @Mapping(target = "news", ignore = true)
    AuthorModel dtoRequestToModel(AuthorDtoRequest dto);

    AuthorDtoResponse modelToDtoResponse(AuthorModel model);

    List<AuthorDtoResponse> listOfModelsToListOfResponses(List<AuthorModel> modelList);
}
