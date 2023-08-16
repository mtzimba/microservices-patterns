package com.mtzimba.circuitbreaker.service;

import com.mtzimba.circuitbreaker.dto.CommentsDto;
import com.mtzimba.circuitbreaker.dto.PostDto;
import com.mtzimba.circuitbreaker.repository.CommentsRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class PostService {

    private final CommentsRepository commentsRepository;

    public PostService(RestTemplate restTemplate) {
        commentsRepository = new CommentsRepository(restTemplate);
    }

    public PostDto buscarPorId(Long postId) {
        PostDto newPost = new PostDto(1L, 1L, "Title", "Boby");
        newPost.addCommnents(carregarComentariosPorPostId(postId));

        return newPost;
    }

    private List<CommentsDto> carregarComentariosPorPostId(Long postId) {
        List<CommentsDto> comentarios = commentsRepository.buscarPorPostId(postId);
        return comentarios;
    }
}
