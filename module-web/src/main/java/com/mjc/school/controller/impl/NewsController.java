package com.mjc.school.controller.impl;

import com.mjc.school.controller.BaseControllers;
import com.mjc.school.dto.NewsDtoRequest;
import com.mjc.school.dto.NewsDtoResponse;
import com.mjc.school.implementation.NewsRepo;
import com.mjc.school.implementation.NewsRepository;
import com.mjc.school.model.implementation.AuthorModel;
import com.mjc.school.model.implementation.NewsModel;
import com.mjc.school.services.NewsService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@ControllerAdvice
@RequestMapping(value = "/news")
public class NewsController implements BaseControllers<NewsDtoRequest, NewsDtoResponse, Long> {

    private final NewsService newsService;
    public final NewsRepository newsRepository;
    public final NewsRepo newsRepo;

    @Autowired
    public NewsController(NewsService newsService, NewsRepository newsRepository, NewsRepo newsRepo) {
        this.newsService = newsService;
        this.newsRepository = newsRepository;
        this.newsRepo = newsRepo;
    }

    @Override
    @PostMapping
    @ApiOperation(value = "Create an news", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully created news"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.CREATED)
    public NewsDtoResponse create(@RequestBody NewsDtoRequest request) {
        return this.newsService.create(request);
    }

    @Override
    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Get news by id", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully got news by id"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public NewsDtoResponse readById(@PathVariable Long id) {
        return this.newsService.readById(id);
    }

    @Override
    @GetMapping
    @ApiOperation(value = "Get all news", response = List.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully got all news"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<NewsDtoResponse> readAll(@RequestParam int size, @RequestParam int page, @RequestParam String sort) {
        return this.newsService.readAll(size, page, sort);
    }

    @Override
    @PatchMapping("/{id}")
    @ApiOperation(value = "Update an news", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully updated news"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public NewsDtoResponse update(@PathVariable Long id, @RequestBody NewsDtoRequest request) {
        return this.newsService.update(request);
    }

    @Override
    @DeleteMapping(value = "/{id}")
    @ApiOperation(value = "Delete news by id")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully deleted news"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable Long id) {
        this.newsService.deleteById(id);
    }

    @GetMapping("/name")
    @ApiOperation(value = "Get news by tag names", response = NewsDtoResponse.class)
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Request has succeeded"),
            @ApiResponse(code = 201, message = "Successfully got news by tag names"),
            @ApiResponse(code = 204, message = "There is no content to send for this request"),
            @ApiResponse(code = 404, message = "The server can not find the requested resource"),
    })
    @ResponseStatus(HttpStatus.OK)
    public List<NewsDtoResponse> getNewsByTagNames(@RequestParam(name = "name") List<String> tagNames, List<Long> tagIds, String authorName, String title, String content, int size, int page, String sort) {
        return this.newsService.getNewsByCriteria(tagNames, tagIds, authorName, title, content, size, page, sort);
    }

    @GetMapping("/count")
    public long countNews(){
        return newsRepository.countNews();
    }

    @GetMapping("/authors")
    public List<AuthorModel> getAuthors(){
        return newsRepository.getAuthor();
    }

    @GetMapping("/getAll")
    public List<NewsModel> getAllNews(){
        return newsRepository.getAll();
    }

    @PostMapping(value = "/add-news", consumes = {"application/json"})
    public void saveNews(@RequestBody NewsModel newNews){
        newNews.setCreateDate(LocalDateTime.now());
        newNews.setLastUpdateDate(LocalDateTime.now());
        newsRepo.save(newNews);
    }

    @DeleteMapping("/id/{id}")
    public void deleteNews(@PathVariable Long id){
        newsRepo.deleteById(id);
    }

    @PutMapping("/update/{id}")
    public void updateNews(@PathVariable Long id, @RequestBody NewsModel newsModel) throws Exception {
        NewsModel updateNews = newsRepo.findById(id).orElseThrow(Exception::new);
        updateNews.setTitle(newsModel.getTitle());
        updateNews.setAuthor(newsModel.getAuthor());
        updateNews.setContent(newsModel.getContent());
        updateNews.setCreateDate(LocalDateTime.now());
        updateNews.setLastUpdateDate(LocalDateTime.now());
        newsRepo.save(updateNews);
    }
}
