package com.mtzimba.circuitbreaker.integrado.infra;

import com.mtzimba.circuitbreaker.dto.CommentsDto;
import com.mtzimba.circuitbreaker.repository.CommentsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class CommentsApiExternaIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private CommentsRepository commentsRepository;

    @BeforeEach
    public void setup() {
        commentsRepository = new CommentsRepository(restTemplate.getRestTemplate());
    }

    @Test
    public void buscarPorPost_DeveRetornarComentarios_QuandoChamadaApiExterna() {

        List<CommentsDto> comentarios = commentsRepository.buscarPorPostId(1L);

        assertNotNull(comentarios);
        assertEquals(5, comentarios.size()); // Ajuste o valor de acordo com o cenário real
    }
}