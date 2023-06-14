package com.mjc.school.exception;

public enum Error {
    STRING_LENGTH_IS_INVALID(1001, "%s field should have length from %s to %s."),
    REQUIRED_FIELD_IS_NULL(1002, "%s field must not be null"),
    AUTHOR_ID_IS_INVALID(1003, "Minimum author id value must be 1"),
    NEWS_ID_IS_INVALID(1004, "Minimum news id value must be 1"),
    NEWS_DOES_NOT_EXIST(1005, "News with id %s does not exist"),
    AUTHOR_DOES_NOT_EXIST(1006, "Author with id %s does not exist"),
    ID_MUST_BE_AN_INTEGER(1007, "Id field must be an integer"),
    ID_DOES_NOT_MATCH(1008, "Id doesn't match"),
    ID_IS_A_NULL(1009, "Id is a null"),
    TAG_DOES_NOT_EXIST(1010, "Tag doesn't exist"),
    COMMENT_DOES_NOT_EXIST(1011, "Comment doesn't exist");

    private final int id;
    private final String message;

    Error(int id, String message) {
        this.id = id;
        this.message = message;
    }

    public int getId() {
        return id;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "Error - " + id + ", Message - " + message;
    }
}
