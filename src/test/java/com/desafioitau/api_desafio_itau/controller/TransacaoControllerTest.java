package com.desafioitau.api_desafio_itau.controller;

import java.time.OffsetDateTime;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.desafioitau.api_desafio_itau.controller.dtos.TransacaoRequestDTO;
import com.desafioitau.api_desafio_itau.services.TransacaoService;

@ExtendWith(MockitoExtension.class)
public class TransacaoControllerTest {

	@InjectMocks
	TransacaoController transacaoController;
	
	@Mock
	TransacaoService transacaoService;
	
	TransacaoRequestDTO transacao;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(transacaoController).build();
		transacao = new TransacaoRequestDTO(20.0, OffsetDateTime.now());
	}
}
