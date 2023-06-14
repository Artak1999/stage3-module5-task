package com.mjc.school.implementation;

import com.mjc.school.Validator;
import com.mjc.school.dto.TagDtoRequest;
import com.mjc.school.exception.Error;
import com.mjc.school.exception.ServiceException;
import com.mjc.school.exception.ValidatorException;
import com.mjc.school.interfaces.TagMapper;
import com.mjc.school.services.TagService;
import org.springframework.stereotype.Service;
import com.mjc.school.BaseRepository;
import com.mjc.school.model.implementation.NewsModel;
import com.mjc.school.model.implementation.TagModel;
import com.mjc.school.dto.TagDtoResponse;

import java.util.List;
import java.util.Optional;

@Service
public class TagServiceImpl implements TagService {

    private final BaseRepository<TagModel, Long> tagRepository;
    private final BaseRepository<NewsModel, Long> newsRepository;

    private final Validator validator;
    private final TagMapper tagMapper;

    public TagServiceImpl(BaseRepository<TagModel, Long> tagRepository,
                          BaseRepository<NewsModel, Long> newsRepository,
                          Validator validator,
                          TagMapper tagMapper) {
        this.tagRepository = tagRepository;
        this.newsRepository = newsRepository;
        this.validator = validator;
        this.tagMapper = tagMapper;
    }

    @Override
    public List<TagDtoResponse> readAll(int size, int page, String sort) {
        return tagMapper.listOfModelsToListOfResponses(tagRepository.readAll(size, page, sort));
    }

    @Override
    public TagDtoResponse readById(Long id) {
        validator.validateId(id);
        Optional<TagModel> maybeNullModel = tagRepository.readById(id);
        if (maybeNullModel.isEmpty()) throw new ValidatorException(String.format(Error.TAG_DOES_NOT_EXIST.toString(), id));
        return tagMapper.modelToDtoResponse(maybeNullModel.get());
    }

    @Override
    public TagDtoResponse create(TagDtoRequest createRequest) {
        TagModel createdTag = tagRepository.create(tagMapper.dtoRequestToModel(createRequest));
        return tagMapper.modelToDtoResponse(createdTag);
    }

    @Override
    public TagDtoResponse update(TagDtoRequest updateRequest) {
        validator.validateTagRequest(updateRequest);
        tagExistsOrThrowException(updateRequest.getId());
        TagModel updateModel = tagRepository.update(tagMapper.dtoRequestToModel(updateRequest));
        return tagMapper.modelToDtoResponse(updateModel);
    }

    @Override
    public boolean deleteById(Long id) {
        validator.validateId(id);
        tagExistsOrThrowException(id);
        return tagRepository.deleteById(id);
    }

    public List<TagDtoResponse> getTagsByNewsId(Long id) {
        validator.validateId(id);
        Optional<NewsModel> maybeNullNews = newsRepository.readById(id);
        if (maybeNullNews.isEmpty()) throw new ServiceException(String.format(Error.NEWS_DOES_NOT_EXIST.toString(), id));
        return tagMapper.listOfModelsToListOfResponses(maybeNullNews.get().getTag());
    }

    private void tagExistsOrThrowException(Long id) {
        if (!tagRepository.existById(id)) throw new ServiceException(String.format(Error.TAG_DOES_NOT_EXIST.toString(), id));
    }
}
