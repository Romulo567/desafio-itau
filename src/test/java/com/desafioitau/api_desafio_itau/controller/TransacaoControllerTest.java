package com.desafioitau.api_desafio_itau.controller;

import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.desafioitau.api_desafio_itau.controller.dtos.TransacaoRequestDTO;
import com.desafioitau.api_desafio_itau.exceptions.UnprocessableEntity;
import com.desafioitau.api_desafio_itau.services.TransacaoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@ExtendWith(MockitoExtension.class)
public class TransacaoControllerTest {

	@InjectMocks
	TransacaoController transacaoController;
	
	@Mock
	TransacaoService transacaoService;
	
	TransacaoRequestDTO transacao;
	
	MockMvc mockMvc;
	
	@Autowired
	final ObjectMapper objectMapper = new ObjectMapper();
	
	@BeforeEach
	void setup() {
		objectMapper.registerModule(new JavaTimeModule());
		mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
		transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.of(2025, 11, 03, 10, 30, 0, 0, ZoneOffset.UTC));
	}
	
	@Test
	void deveAdicionarTransacaoComSucesso() throws Exception{
		
		doNothing().when(transacaoService).criarTransacoes(transacao);
		
		mockMvc.perform(post("/transacao")
				.content(objectMapper.writeValueAsString(transacao))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isCreated());	
	}
	
	@Test
	void deveGerarExcecaoAoAdicionarTransacao() throws Exception {
		
		doThrow(new UnprocessableEntity("Erro de requisição")).when(transacaoService).criarTransacoes(transacao);
		
		mockMvc.perform(post("/transacao")
				.content(objectMapper.writeValueAsString(transacao))
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().is4xxClientError());	
	}
	
	@Test
	void deveDeletarTransacaoComSucesso() throws Exception {
		
		doNothing().when(transacaoService).limparTransacoes();
		
		mockMvc.perform(delete("/transacao"))
				.andExpect(status().isOk());	
	}
	
	@Test
	void deveGerarExcecaoQuandoJsonForInvalido() throws Exception {
		
		String jsonInvalido = "{\"valor\": \"vinte\", \"dataHora\": \"2025-11-03T10:30:00-04:00\"}";
		mockMvc.perform(post("/transacao")
				.content(jsonInvalido)
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
