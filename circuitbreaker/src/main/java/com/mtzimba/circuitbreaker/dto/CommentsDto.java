package com.mtzimba.circuitbreaker.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class CommentsDto {
    private Long id;
    private Long postId;
    private String name;
    private String email;
    private String body;

    public CommentsDto() {
    }
    @JsonCreator
    public CommentsDto(@JsonProperty("postId") Long postId,
                       @JsonProperty("id") Long id,
                       @JsonProperty("name") String name,
                       @JsonProperty("email") String email,
                       @JsonProperty("body") String body) {
        this.postId = postId;
        this.id = id;
        this.name = name;
        this.email = email;
        this.body = body;
    }

    public Long getId() {
        return id;
    }

    public Long getPostId() {
        return postId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getBody() {
        return body;
    }
}
