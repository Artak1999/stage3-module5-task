package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseControllers;
import com.mjc.school.dto.TagDtoRequest;
import com.mjc.school.dto.TagDtoResponse;
import com.mjc.school.services.TagService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping(value = "/tags")
public class TagController implements BaseControllers<TagDtoRequest, TagDtoResponse, Long> {

    private final TagService tagService;

    @Autowired
    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Create an tag", response = TagDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully created tag"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public TagDtoResponse create(@RequestBody TagDtoRequest request) {
        return this.tagService.create(request);
    }

    @Override
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get tag by id", response = TagDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully got tag by id"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public TagDtoResponse readById(@PathVariable Long id) {
        return this.tagService.readById(id);
    }

    @Override
    @GetMapping
    @ApiOperation(value = "Get all tags", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully got all tags"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<TagDtoResponse> readAll(@RequestParam int size, @RequestParam int page, @RequestParam String sort) {
        return this.tagService.readAll(size, page, sort);
    }

    @Override
    @PatchMapping("/{id}")
    @ApiOperation(value = "Update an tag", response = TagDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully updated tag"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public TagDtoResponse update(@PathVariable Long id, @RequestBody TagDtoRequest request) {
        return this.tagService.update(request);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete tag by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully deleted tag"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.tagService.deleteById(id);
    }

    @GetMapping(value = "?news=newsId")
    @ApiOperation(value = "Get tag by news id", response = TagDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully got tag by news id"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<TagDtoResponse> getTagByNewsId(@PathVariable Long id) {
        return this.tagService.getTagsByNewsId(id);
    }
}
