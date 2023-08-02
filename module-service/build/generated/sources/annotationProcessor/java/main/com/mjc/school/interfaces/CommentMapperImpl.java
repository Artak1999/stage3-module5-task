package com.mjc.school.interfaces;

import com.mjc.school.dto.CommentDtoRequest;
import com.mjc.school.dto.CommentDtoResponse;
import com.mjc.school.model.implementation.CommentModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-02T10:34:15+0400",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class CommentMapperImpl implements CommentMapper {

    @Override
    public CommentModel dtoRequestToModel(CommentDtoRequest createRequest) {
        if ( createRequest == null ) {
            return null;
        }

        CommentModel commentModel = new CommentModel();

        commentModel.setId( createRequest.getId() );
        commentModel.setContent( createRequest.getContent() );

        return commentModel;
    }

    @Override
    public CommentDtoResponse modelToDtoResponse(CommentModel model) {
        if ( model == null ) {
            return null;
        }

        CommentDtoResponse commentDtoResponse = new CommentDtoResponse();

        commentDtoResponse.setId( model.getId() );
        commentDtoResponse.setContent( model.getContent() );
        commentDtoResponse.setCreated( model.getCreated() );
        commentDtoResponse.setModified( model.getModified() );

        return commentDtoResponse;
    }

    @Override
    public List<CommentDtoResponse> listOfModelsToListOfResponses(List<CommentModel> model) {
        if ( model == null ) {
            return null;
        }

        List<CommentDtoResponse> list = new ArrayList<CommentDtoResponse>( model.size() );
        for ( CommentModel commentModel : model ) {
            list.add( modelToDtoResponse( commentModel ) );
        }

        return list;
    }
}
