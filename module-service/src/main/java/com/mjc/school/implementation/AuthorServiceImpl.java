package com.mjc.school.implementation;

import com.mjc.school.dto.AuthorDtoResponse;
import com.mjc.school.exception.Error;
import com.mjc.school.exception.ServiceException;
import com.mjc.school.interfaces.AuthorMapper;
import com.mjc.school.BaseRepository;
import com.mjc.school.model.implementation.AuthorModel;
import com.mjc.school.model.implementation.NewsModel;
import com.mjc.school.Validator;
import com.mjc.school.dto.AuthorDtoRequest;
import com.mjc.school.services.AuthorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class AuthorServiceImpl implements AuthorService {
    private final BaseRepository<AuthorModel, Long> authorRepository;
    private final BaseRepository<NewsModel, Long> newsRepository;
    private final Validator validator;
    private final AuthorMapper authorMapper;

    @Autowired
    public AuthorServiceImpl(BaseRepository<AuthorModel, Long> authorRepository,
                             BaseRepository<NewsModel, Long> newsRepository,
                             Validator authorValidator,
                             AuthorMapper authorMapper) {
        this.authorRepository = authorRepository;
        this.newsRepository = newsRepository;
        this.validator = authorValidator;
        this.authorMapper = authorMapper;
    }

    @Override
    public List<AuthorDtoResponse> readAll(int size, int page, String sort) {
        return authorMapper.listOfModelsToListOfResponses(authorRepository.readAll(size,page,sort));
    }

    @Override
    public AuthorDtoResponse readById(Long id) {
        validator.validateId(id);
        Optional<AuthorModel> maybeNullModel = authorRepository.readById(id);
        if (maybeNullModel.isEmpty()) throw new ServiceException(String.format(Error.AUTHOR_DOES_NOT_EXIST.toString(), id));
        return authorMapper.modelToDtoResponse(maybeNullModel.get());
    }

    @Override
    public AuthorDtoResponse create(AuthorDtoRequest createRequest) {
        validator.validateAuthorRequestWithoutId(createRequest);
        AuthorModel createdAuthor = authorRepository.create(authorMapper.dtoRequestToModel(createRequest));
        return authorMapper.modelToDtoResponse(createdAuthor);
    }

    @Override
    public AuthorDtoResponse update(AuthorDtoRequest updateRequest) {
        validator.validateAuthorRequest(updateRequest);
        AuthorModel updatedAuthor = authorRepository.update(authorMapper.dtoRequestToModel(updateRequest));
        return authorMapper.modelToDtoResponse(updatedAuthor);
    }


    public AuthorDtoResponse getAuthorByNewsId(Long id) {
        validator.validateId(id);
        Optional<NewsModel> maybeNullNews = newsRepository.readById(id);
        if (maybeNullNews.isEmpty()) throw new ServiceException(String.format(Error.NEWS_DOES_NOT_EXIST.toString(), id));
        return authorMapper.modelToDtoResponse(maybeNullNews.get().getAuthor());
    }

    @Override
    public boolean deleteById(Long id) {
        validator.validateId(id);
        authorExistsOrThrowException(id);
        return authorRepository.deleteById(id);
    }

    private void authorExistsOrThrowException(Long id) {
        if (!authorRepository.existById(id)) throw new ServiceException(String.format(Error.AUTHOR_DOES_NOT_EXIST.toString(), id));
    }
}
