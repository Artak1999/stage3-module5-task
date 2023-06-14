package com.mjc.school.implementation;

import com.mjc.school.BaseRepository;
import com.mjc.school.model.implementation.AuthorModel;
import com.mjc.school.model.implementation.NewsModel;
import com.mjc.school.model.implementation.TagModel;
import com.mjc.school.Validator;
import com.mjc.school.dto.NewsDtoRequest;
import com.mjc.school.dto.NewsDtoResponse;
import com.mjc.school.exception.Error;
import com.mjc.school.exception.ServiceException;
import com.mjc.school.interfaces.NewsMapper;
import com.mjc.school.services.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.function.Predicate;

@Service
public class NewsServiceImpl implements NewsService {
    private final BaseRepository<NewsModel, Long> newsRepository;
    private final BaseRepository<AuthorModel, Long> authorRepository;
    private final BaseRepository<TagModel, Long> tagRepository;
    private final Validator validator;
    private final NewsMapper newsMapper;

    @Autowired
    public NewsServiceImpl(BaseRepository<NewsModel, Long> newsRepository,
                           BaseRepository<AuthorModel, Long> authorRepository,
                           BaseRepository<TagModel, Long> tagRepository,
                           Validator validator,
                           NewsMapper newsMapper) {
        this.newsRepository = newsRepository;
        this.authorRepository = authorRepository;
        this.tagRepository = tagRepository;
        this.validator = validator;
        this.newsMapper = newsMapper;
    }

    @Override
    public List<NewsDtoResponse> readAll(int size, int page, String sort) {
        return newsMapper.listOfModelsToListOfResponses(newsRepository.readAll(size, page, sort));
    }

    @Override
    public NewsDtoResponse readById(Long id) {
        validator.validateId(id);
        Optional<NewsModel> maybeNullModel = newsRepository.readById(id);
        if (maybeNullModel.isEmpty()) throw new ServiceException(String.format(Error.NEWS_DOES_NOT_EXIST.toString(), id));
        return modelToDtoResponse(maybeNullModel.get());
    }

    @Override
    public NewsDtoResponse create(NewsDtoRequest createRequest) {
        validator.validateNewsRequestWithoutId(createRequest);
        authorExistsOrThrowException(createRequest.getAuthorId());
        tagsExistOrThrowException(createRequest.getTagIds());
        NewsModel createdNews = newsRepository.create(newsMapper.dtoRequestToModel(createRequest));
        return modelToDtoResponse(createdNews);
    }

    @Override
    public NewsDtoResponse update(NewsDtoRequest updateRequest) {
        validator.validateNewsRequest(updateRequest);
        newsExistsOrThrowException(updateRequest.getId());
        authorExistsOrThrowException(updateRequest.getAuthorId());
        tagsExistOrThrowException(updateRequest.getTagIds());
        NewsModel updatedNews = newsRepository.update(newsMapper.dtoRequestToModel(updateRequest));
        return modelToDtoResponse(updatedNews);
    }

    @Override
    public boolean deleteById(Long id) {
        validator.validateId(id);
        newsExistsOrThrowException(id);
        return newsRepository.deleteById(id);
    }

    public List<NewsDtoResponse> getNewsByCriteria(List<String> tagNames,
                                                   List<Long> tagIds,
                                                   String authorName,
                                                   String title,
                                                   String content,
                                                   int size,
                                                   int page,
                                                   String sort) {
        List<NewsModel> newsModels = newsRepository.
                readAll(size,page,sort)
                .stream()
                .filter(allNotNullParameterPredicate(tagNames, tagIds, authorName, title, content))
                .toList();
        return newsMapper.listOfModelsToListOfResponses(newsModels);
    }

    @Override
    public NewsModel dtoRequestToModel(NewsDtoRequest dto) {
        return null;
    }

    @Override
    public NewsDtoResponse modelToDtoResponse(NewsModel model) {
        return null;
    }

    private Predicate<NewsModel> allNotNullParameterPredicate(List<String> tagNames,
                                                                              List<Long> tagIds,
                                                                              String authorName,
                                                                              String title,
                                                                              String content) {
        Predicate<NewsModel> newsPredicate = news -> true;
        if (tagNames != null && !tagNames.isEmpty()) newsPredicate = newsPredicate.and(news -> new HashSet<>(news.getTag().stream().map(TagModel::getName).toList()).containsAll(tagNames));
        if (tagIds != null && !tagIds.isEmpty()) newsPredicate = newsPredicate.and(news -> new HashSet<>(news.getTag().stream().map(TagModel::getId).toList()).containsAll(tagIds));
        if (authorName != null && !authorName.isBlank()) newsPredicate = newsPredicate.and(news -> news.getAuthor().getName().equalsIgnoreCase(authorName));
        if (title != null && !title.isBlank()) newsPredicate = newsPredicate.and(news -> news.getTitle().toLowerCase().contains(title.toLowerCase()));
        if (content != null && !content.isBlank()) newsPredicate = newsPredicate.and(news -> news.getContent().toLowerCase().contains(content.toLowerCase()));
        return newsPredicate;
    }

    private void authorExistsOrThrowException(Long id) {
        if (!authorRepository.existById(id)) throw new ServiceException(String.format(Error.AUTHOR_DOES_NOT_EXIST.toString(), id));
    }

    private void newsExistsOrThrowException(Long id) {
        if (!newsRepository.existById(id)) throw new ServiceException(String.format(Error.NEWS_DOES_NOT_EXIST.toString(), id));
    }

    private void tagsExistOrThrowException(List<Long> ids) {
        ids.forEach(id -> { if (!tagRepository.existById(id)) throw new ServiceException(String.format(Error.TAG_DOES_NOT_EXIST.toString(), id));});
    }
}
