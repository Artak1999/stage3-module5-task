package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseControllers;
import com.mjc.school.dto.CommentDtoRequest;
import com.mjc.school.dto.CommentDtoResponse;
import com.mjc.school.services.CommentService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping(value = "/comments")
public class CommentController implements BaseControllers<CommentDtoRequest, CommentDtoResponse, Long> {

    private final CommentService commentService;

    @Autowired
    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Create an comment", response = CommentDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully created comment"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDtoResponse create(@RequestBody CommentDtoRequest request) {
        return this.commentService.create(request);
    }

    @Override
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get comment by id", response = CommentDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully got comment by id"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse readById(@PathVariable Long id) {
        return this.commentService.readById(id);
    }

    @Override
    @GetMapping
    @ApiOperation(value = "Get all comments", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully got all comments"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDtoResponse> readAll(@RequestParam int size, @RequestParam int page, @RequestParam String sort) {
        return this.commentService.readAll(size, page, sort);
    }

    @Override
    @PutMapping("/{id}")
    @ApiOperation(value = "Update an comment", response = CommentDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully updated comment"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public CommentDtoResponse update(@PathVariable Long id, @RequestBody CommentDtoRequest request) {
        return this.commentService.update(request);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete comment by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully deleted comment"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.commentService.deleteById(id);
    }

    @GetMapping(value = "?news=newsId")
    @ApiOperation(value = "Get comment by news id", response = CommentDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully got comment by news id"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<CommentDtoResponse> getCommentsByNewsId(@PathVariable Long id) {
        return this.commentService.getTagsByNewsId(id);
    }
}
