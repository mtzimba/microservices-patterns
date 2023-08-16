package com.mtzimba.circuitbreaker.dto;

import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PostDto {

    private Long userId;

    private Long id;

    private String title;

    private String body;

    private List<CommentsDto> comments;

    public PostDto(){

    }
    public PostDto(Long userId, Long id, String title, String body) {
        this.userId = userId;
        this.id = id;
        this.title = title;
        this.body = body;
    }

    public Long getUserId() {
        return userId;
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getBody() {
        return body;
    }

    public List<CommentsDto> getComments() {
        return comments;
    }

    public void addCommnent(CommentsDto newComment) {
        if (CollectionUtils.isEmpty(this.comments)) {
            this.comments = Collections.EMPTY_LIST;
        }
        this.comments.add(newComment);
    }

    public void addCommnents(List<CommentsDto> listComments) {
        if (CollectionUtils.isEmpty(this.comments)) {
            this.comments = new ArrayList<>();
        }
        this.comments.addAll(listComments);
    }
}
