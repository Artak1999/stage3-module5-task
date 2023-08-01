package com.mjc.school.interfaces;

import com.mjc.school.dto.AuthorDtoRequest;
import com.mjc.school.dto.AuthorDtoResponse;
import com.mjc.school.model.implementation.AuthorModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-07-24T17:33:34+0400",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 20 (Oracle Corporation)"
)
@Component
public class AuthorMapperImpl implements AuthorMapper {

    @Override
    public AuthorModel dtoRequestToModel(AuthorDtoRequest dto) {
        if ( dto == null ) {
            return null;
        }

        AuthorModel authorModel = new AuthorModel();

        authorModel.setName( dto.getName() );

        return authorModel;
    }

    @Override
    public AuthorDtoResponse modelToDtoResponse(AuthorModel model) {
        if ( model == null ) {
            return null;
        }

        AuthorDtoResponse authorDtoResponse = new AuthorDtoResponse();

        authorDtoResponse.setId( model.getId() );
        authorDtoResponse.setName( model.getName() );
        authorDtoResponse.setCreateDate( model.getCreateDate() );
        authorDtoResponse.setLastUpdateDate( model.getLastUpdateDate() );

        return authorDtoResponse;
    }

    @Override
    public List<AuthorDtoResponse> listOfModelsToListOfResponses(List<AuthorModel> modelList) {
        if ( modelList == null ) {
            return null;
        }

        List<AuthorDtoResponse> list = new ArrayList<AuthorDtoResponse>( modelList.size() );
        for ( AuthorModel authorModel : modelList ) {
            list.add( modelToDtoResponse( authorModel ) );
        }

        return list;
    }
}
