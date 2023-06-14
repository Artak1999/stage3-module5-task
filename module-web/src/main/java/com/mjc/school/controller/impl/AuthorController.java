package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseControllers;
import com.mjc.school.dto.AuthorDtoRequest;
import com.mjc.school.dto.AuthorDtoResponse;
import com.mjc.school.services.AuthorService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping(value = "/authors")
public class AuthorController implements BaseControllers<AuthorDtoRequest, AuthorDtoResponse, Long> {

    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Create an author", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully created author"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDtoResponse create(@RequestBody AuthorDtoRequest request) {
        return this.authorService.create(request);
    }

    @Override
    @GetMapping("/{id}")
    @ApiOperation(value = "Get author by id", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully got author by id"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse readById(@PathVariable Long id) {
        return this.authorService.readById(id);
    }

    @Override
    @GetMapping
    @ApiOperation(value = "Get all authors", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully got all authors"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<AuthorDtoResponse> readAll(@RequestParam int size, @RequestParam int page, @RequestParam String sort) {
        return this.authorService.readAll(size, page, sort);
    }

    @Override
    @PatchMapping("/{id}")
    @ApiOperation(value = "Update an author", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully updated author"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse update(@PathVariable Long id, @RequestBody AuthorDtoRequest request) {
        return this.authorService.update(request);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete author by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully deleted author"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.authorService.deleteById(id);
    }

    @GetMapping(value = "?news=newsId")
    @ApiOperation(value = "Get author by news id", response = AuthorDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully got author by news id"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public AuthorDtoResponse getAuthorByNewsId(@PathVariable Long id){
        return this.authorService.getAuthorByNewsId(id);
    }
}
