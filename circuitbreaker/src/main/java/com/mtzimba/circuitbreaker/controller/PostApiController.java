package com.mtzimba.circuitbreaker.controller;

import com.mtzimba.circuitbreaker.dto.PostDto;
import com.mtzimba.circuitbreaker.service.PostService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/posts")
public class PostApiController {

    private final PostService postService;

    public PostApiController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/{postId}")
    public PostDto buscarPorId(@PathVariable("postId") Long postId) {
        PostDto post = postService.buscarPorId(postId);
        return post;
    }
}
