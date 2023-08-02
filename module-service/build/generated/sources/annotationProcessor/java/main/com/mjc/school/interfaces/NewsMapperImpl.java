package com.mjc.school.interfaces;

import com.mjc.school.dto.NewsDtoResponse;
import com.mjc.school.model.implementation.NewsModel;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-08-02T10:54:39+0400",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-7.6.jar, environment: Java 17.0.7 (Oracle Corporation)"
)
@Component
public class NewsMapperImpl extends NewsMapper {

    @Override
    public List<NewsDtoResponse> listOfModelsToListOfResponses(List<NewsModel> modelList) {
        if ( modelList == null ) {
            return null;
        }

        List<NewsDtoResponse> list = new ArrayList<NewsDtoResponse>( modelList.size() );
        for ( NewsModel newsModel : modelList ) {
            list.add( modelToDtoResponse( newsModel ) );
        }

        return list;
    }
}
