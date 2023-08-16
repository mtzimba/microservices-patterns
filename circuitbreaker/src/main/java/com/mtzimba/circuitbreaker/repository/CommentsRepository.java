package com.mtzimba.circuitbreaker.repository;

import com.mtzimba.circuitbreaker.dto.CommentsDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CommentsRepository {

    private final static String API_URL = UriComponentsBuilder
            .fromHttpUrl("https://jsonplaceholder.typicode.com/comments?")
            .query("postId={postId}")
            .encode()
            .toUriString();
    private final Logger logger = LoggerFactory.getLogger(CommentsRepository.class);
    private final RestTemplate restTemplate;

    public CommentsRepository(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public List<CommentsDto> buscarPorPostId(Long postId) {
        final List<CommentsDto> comentarios = executarRequisicao(postId);
        return comentarios;
    }

    private List<CommentsDto> executarRequisicao(Long postId) {
        final Map<String, Object> parametros = new HashMap<>();
        parametros.put("postId", postId);

        final CommentsDto[] comentarios;

        try {
            comentarios = restTemplate.getForObject(API_URL, CommentsDto[].class, parametros);
        } catch (Exception e) {
            logger.error("Erro ao buscar os comentários");
            throw e;
        }

        return Arrays.asList(comentarios);
    }

}
