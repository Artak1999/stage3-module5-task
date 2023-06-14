package com.mjc.school.interfaces;

import com.mjc.school.dto.TagDtoRequest;
import com.mjc.school.dto.TagDtoResponse;
import com.mjc.school.model.implementation.TagModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-06-11T19:45:20+0400",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.6 (JetBrains s.r.o.)"
)
@Component
public class TagMapperImpl implements TagMapper {

    @Override
    public TagModel dtoRequestToModel(TagDtoRequest dto) {
        if ( dto == null ) {
            return null;
        }

        TagModel tagModel = new TagModel();

        tagModel.setId( dto.getId() );
        tagModel.setName( dto.getName() );

        return tagModel;
    }

    @Override
    public TagDtoResponse modelToDtoResponse(TagModel model) {
        if ( model == null ) {
            return null;
        }

        TagDtoResponse tagDtoResponse = new TagDtoResponse();

        tagDtoResponse.setId( model.getId() );
        tagDtoResponse.setName( model.getName() );

        return tagDtoResponse;
    }

    @Override
    public List<TagDtoResponse> listOfModelsToListOfResponses(List<TagModel> modelList) {
        if ( modelList == null ) {
            return null;
        }

        List<TagDtoResponse> list = new ArrayList<TagDtoResponse>( modelList.size() );
        for ( TagModel tagModel : modelList ) {
            list.add( modelToDtoResponse( tagModel ) );
        }

        return list;
    }
}