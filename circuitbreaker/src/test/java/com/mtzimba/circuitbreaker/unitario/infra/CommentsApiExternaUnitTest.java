package com.mtzimba.circuitbreaker.unitario.infra;


import com.mtzimba.circuitbreaker.dto.CommentsDto;
import com.mtzimba.circuitbreaker.repository.CommentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

public class CommentsApiExternaUnitTest {

    @Mock
    private RestTemplate restTemplate;

    private CommentsRepository commentsRepository;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        commentsRepository = new CommentsRepository(restTemplate);
    }

    @Test
    public void buscarPorPost_DeveRetornarListaDeComentarios_QuandoChamadaApiExternaComSucesso() {

        CommentsDto[] comentariosMock = {
                new CommentsDto(1L, 1L, "Nome", "email@example.com", "Corpo do comentário"),
                new CommentsDto(2L, 1L, "Nome", "email@example.com", "Corpo do comentário")
        };

        ResponseEntity<CommentsDto[]> responseEntity = new ResponseEntity<>(comentariosMock, HttpStatus.OK);

        Mockito.when(restTemplate.getForObject(any(String.class), eq(CommentsDto[].class), (Map<String, ?>) any()))
                .thenReturn(responseEntity.getBody());

        List<CommentsDto> comentarios = commentsRepository.buscarPorPostId(1L);

        assertEquals(2, comentarios.size());
        assertEquals(comentariosMock[0], comentarios.get(0));
        assertEquals(comentariosMock[1], comentarios.get(1));
    }

    @Test
    public void buscarPorPost_DeveLancarExcecao_QuandoChamadaApiExternaComErro() {
        Mockito.when(restTemplate.getForObject(any(String.class), eq(CommentsDto[].class), (Map<String, ?>) any()))
                .thenThrow(new RuntimeException("Erro ao buscar os comentários"));

        try {
            commentsRepository.buscarPorPostId(1L);
        } catch (Exception e) {
            assertEquals(RuntimeException.class, e.getClass());
            assertEquals("Erro ao buscar os comentários", e.getMessage());
        }
    }

}

