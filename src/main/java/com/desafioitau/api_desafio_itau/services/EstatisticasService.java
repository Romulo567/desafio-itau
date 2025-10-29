package com.desafioitau.api_desafio_itau.services;

import java.util.DoubleSummaryStatistics;
import java.util.List;

import org.springframework.stereotype.Service;

import com.desafioitau.api_desafio_itau.controller.dtos.EstatisticasResponseDTO;
import com.desafioitau.api_desafio_itau.controller.dtos.TransacaoRequestDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EstatisticasService {

	private final TransacaoService transacaoService;
	
	public EstatisticasResponseDTO calcularEstatisticasTransacoes(Integer intervaloBusca) {
		
		log.info("Iniciada busca de estatisticas de transações pelo periodo de tempo " + intervaloBusca);
		
		List<TransacaoRequestDTO> transacoes = transacaoService.buscarTransacoes(intervaloBusca);
		
		DoubleSummaryStatistics estatisticasTransacoes = transacoes.stream()
				.mapToDouble(TransacaoRequestDTO::valor).summaryStatistics();
		
		log.info("Estatisticas retornadas com sucesso");
		
		return new EstatisticasResponseDTO(estatisticasTransacoes.getCount(), 
				estatisticasTransacoes.getSum(), 
				estatisticasTransacoes.getAverage(),
				estatisticasTransacoes.getMin(),
				estatisticasTransacoes.getMax());
	}
}
