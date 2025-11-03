package com.desafioitau.api_desafio_itau.controller;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.desafioitau.api_desafio_itau.controller.dtos.EstatisticasResponseDTO;
import com.desafioitau.api_desafio_itau.services.EstatisticasService;

@ExtendWith(MockitoExtension.class)
public class EstatisticaControllerTest {

	@InjectMocks
	EstatisticasController estatisticasController;
	
	@Mock
	EstatisticasService estatisticasService;
	
	EstatisticasResponseDTO estatisticas;
	
	MockMvc mockMvc;
	
	@BeforeEach
	void setup() {
		mockMvc = MockMvcBuilders.standaloneSetup(estatisticasService).build();
		estatisticas = new EstatisticasResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
	}
}
