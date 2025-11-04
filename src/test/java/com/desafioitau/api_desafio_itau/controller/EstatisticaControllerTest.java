package com.desafioitau.api_desafio_itau.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

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
		mockMvc = MockMvcBuilders.standaloneSetup(estatisticasController).build();
		estatisticas = new EstatisticasResponseDTO(1L, 20.0, 20.0, 20.0, 20.0);
	}
	
	@Test
	void deveBuscarEstatisticasComSucesso() throws Exception {
		
		when(estatisticasService.calcularEstatisticasTransacoes(60)).thenReturn(estatisticas);
		
		mockMvc.perform(get("/estatistica")
				.param("intervaloBusca", "60")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.count").value(estatisticas.count()))
				.andExpect(jsonPath("$.sum").value(estatisticas.sum()))
			    .andExpect(jsonPath("$.avg").value(estatisticas.avg())) 
			    .andExpect(jsonPath("$.min").value(estatisticas.min())) 
			    .andExpect(jsonPath("$.max").value(estatisticas.max()));
	}
	
	@Test
	void deveGerarErro400AoBuscarEstatisticasComParamentroInvalido() throws Exception {
		
		mockMvc.perform(get("/estatistica")
				.param("intervaloBusca", "texto-invalido")
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isBadRequest());
	}
}
