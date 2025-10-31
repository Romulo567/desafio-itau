package com.desafioitau.api_desafio_itau.services;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class EstatisticasServiceTest {

	@InjectMocks
	EstatisticasService estatisticasService;
	
	@Mock
	TransacaoService transacaoService;
}
