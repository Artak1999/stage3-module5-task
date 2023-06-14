package com.mjc.school.implementation;

import com.mjc.school.exception.Error;
import com.mjc.school.exception.ServiceException;
import com.mjc.school.interfaces.CommentMapper;
import com.mjc.school.BaseRepository;
import com.mjc.school.model.implementation.CommentModel;
import com.mjc.school.Validator;
import com.mjc.school.dto.CommentDtoRequest;
import com.mjc.school.dto.CommentDtoResponse;
import com.mjc.school.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    private final BaseRepository<CommentModel, Long> commentRepository;
    private final CommentMapper commentMapper;
    private final Validator validator;

    @Autowired
    public CommentServiceImpl(BaseRepository<CommentModel, Long> commentRepository,
                              CommentMapper commentMapper,
                              Validator validator) {
        this.commentRepository = commentRepository;
        this.commentMapper = commentMapper;
        this.validator = validator;
    }


    @Override
    public List<CommentDtoResponse> readAll(int size, int page, String sort) {
        return commentMapper.listOfModelsToListOfResponses(commentRepository.readAll(size, page, sort));
    }

    @Override
    public CommentDtoResponse readById(Long id) {
        validator.validateId(id);
        Optional<CommentModel> maybeNullModel = commentRepository.readById(id);
        if (maybeNullModel.isEmpty()) throw new ServiceException(String.format(Error.COMMENT_DOES_NOT_EXIST.toString(), id));
        return commentMapper.modelToDtoResponse(maybeNullModel.get());
    }

    @Override
    public CommentDtoResponse create(CommentDtoRequest createRequest) {
        validator.validateCommentRequestWithoutId(createRequest);
        CommentModel createdComment = commentRepository.create(commentMapper.dtoRequestToModel(createRequest));
        return commentMapper.modelToDtoResponse(createdComment);
    }

    @Override
    public CommentDtoResponse update(CommentDtoRequest updateRequest) {
        validator.validateCommentRequest(updateRequest);
        commentExistsOrThrowException(updateRequest.getId());
        CommentModel updatedComment = commentRepository.update(commentMapper.dtoRequestToModel(updateRequest));
        return commentMapper.modelToDtoResponse(updatedComment);
    }

    @Override
    public boolean deleteById(Long id) {
        validator.validateId(id);
        commentExistsOrThrowException(id);
        return commentRepository.deleteById(id);
    }

    @Override
    public List<CommentDtoResponse> getCommentsByNewsId(Long id) {
        validator.validateId(id);
        Optional<CommentModel> maybeNullComment = commentRepository.readById(id);
        if (maybeNullComment.isEmpty()) throw new ServiceException(String.format(Error.COMMENT_DOES_NOT_EXIST.toString(), id));
        return null;//commentMapper.listOfModelsToListOfResponses(maybeNullComment.get().getContent());
    }

    @Override
    public List<CommentDtoResponse> getTagsByNewsId(Long id) {
        validator.validateId(id);
        return null;
    }

    private void commentExistsOrThrowException(Long id) {
        if (!commentRepository.existById(id))
            throw new ServiceException(String.format(Error.COMMENT_DOES_NOT_EXIST.toString(), id));
    }
}
