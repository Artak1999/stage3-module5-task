package com.mjc.school;

import com.mjc.school.dto.AuthorDtoRequest;
import com.mjc.school.dto.CommentDtoRequest;
import com.mjc.school.dto.TagDtoRequest;
import com.mjc.school.exception.Error;
import com.mjc.school.exception.ValidatorException;
import com.mjc.school.dto.NewsDtoRequest;
import org.springframework.stereotype.Component;

@Component
public class Validator {
    private static final int NEWS_TITLE_MIN_LENGTH = 5;
    private static final int NEWS_TITLE_MAX_LENGTH = 30;
    private static final int NEWS_CONTENT_MIN_LENGTH = 5;
    private static final int NEWS_CONTENT_MAX_LENGTH = 255;
    private static final int AUTHOR_NAME_MIN_LENGTH = 3;
    private static final int AUTHOR_NAME_MAX_LENGTH = 15;
    private static final int COMMENT_CONTENT_MIN_LENGTH = 5;
    private static final int COMMENT_CONTENT_MAX_LENGTH = 255;


    private void validateStringLength(String str, String fieldName, int min, int max) {
        if (str == null) {
            throw new ValidatorException(String.format(Error.REQUIRED_FIELD_IS_NULL.toString(), fieldName));
        }
        if (str.length() < min || str.length() > max) {
            throw new ValidatorException(String.format(Error.STRING_LENGTH_IS_INVALID.toString(), fieldName, min, max));
        }
    }

    public void validateNewsId(Long id) {
        validateNullId(id, "news id");
        if (id < 1) {
            throw new ValidatorException(Error.NEWS_ID_IS_INVALID.toString());
        }
    }

    public void validateAuthorId(Long id) {
        validateNullId(id, "author id");
        if (id < 1) {
            throw new ValidatorException(Error.AUTHOR_ID_IS_INVALID.toString());
        }
    }

    private void validateNullId(Long id, String fieldName) {
        if (id == null) {
            throw new ValidatorException(String.format(Error.REQUIRED_FIELD_IS_NULL.toString(), fieldName));
        }
    }

    public void validateNewsRequest(NewsDtoRequest request) {
        validateStringLength(request.getTitle(), "news title", NEWS_TITLE_MIN_LENGTH, NEWS_TITLE_MAX_LENGTH);
        validateStringLength(request.getContent(), "news content", NEWS_CONTENT_MIN_LENGTH, NEWS_CONTENT_MAX_LENGTH);
        validateAuthorId(request.getAuthorId());
    }

    public void validateAuthorRequest(AuthorDtoRequest request) {
        validateStringLength(request.getName(), "author name", AUTHOR_NAME_MIN_LENGTH, AUTHOR_NAME_MAX_LENGTH);
    }

    public void validateId(Long id) {
        if (id == null) throw new ValidatorException(String.format(Error.ID_IS_A_NULL.toString()));
        if (id < 1) throw new ValidatorException(Error.ID_DOES_NOT_MATCH.toString());
    }

    public void validateNewsRequestWithoutId(NewsDtoRequest request) {
        validateStringLength(request.getTitle(), "news title", NEWS_TITLE_MIN_LENGTH, NEWS_TITLE_MAX_LENGTH);
        validateStringLength(request.getContent(), "news content", NEWS_CONTENT_MIN_LENGTH, NEWS_CONTENT_MAX_LENGTH);
        validateId(request.getAuthorId());
        request.getTagIds().forEach(this::validateId);
    }

    public void validateAuthorRequestWithoutId(AuthorDtoRequest request) {
        validateStringLength(request.getName(), "author name", AUTHOR_NAME_MIN_LENGTH, AUTHOR_NAME_MAX_LENGTH);
    }

    public void validateCommentRequestWithoutId(CommentDtoRequest request) {
        validateStringLength(request.getContent(), "content ", COMMENT_CONTENT_MIN_LENGTH, COMMENT_CONTENT_MAX_LENGTH);
    }

    public void validateTagRequest(TagDtoRequest updateRequest) {
        validateId(updateRequest.getId());
    }

    public void validateCommentRequest(CommentDtoRequest request) {
        validateStringLength(request.getContent(), "content ", COMMENT_CONTENT_MIN_LENGTH, COMMENT_CONTENT_MAX_LENGTH);
    }
}
