package com.calculadora.service;

import com.calculadora.model.Fracao;
import com.calculadora.util.TipoOperacao;

public class FracoesServiceImpl implements FracoesService {
	private Fracao resposta;
	
	public FracoesServiceImpl() {
		resposta = new Fracao();
	}
	
	@Override
	public Fracao calcular(Fracao firstFracao, Fracao secondFracao, String operacao) {
		
		TipoOperacao tipoOperacao = TipoOperacao.getOperacao(operacao);
		
		switch (tipoOperacao) {
			case SOMA:
				return adicao(firstFracao, secondFracao);
			case SUBTRACAO:
				return subtracao(firstFracao, secondFracao);
			case MULTIPLICACAO:
				return multiplicacao(firstFracao, secondFracao);
			case DIVISAO:
				return divisao(firstFracao, secondFracao);
			default:
				return null;
		}
	}
	
	private Fracao adicao(Fracao firstFracao, Fracao secondFracao) {
		resposta.setNumerador(firstFracao.getNumerador().multiply(secondFracao.getDenominador())
				   										.add(secondFracao.getNumerador().multiply(firstFracao.getDenominador())));
		resposta.setDenominador(firstFracao.getNumerador().multiply(secondFracao.getDenominador()));
		
		return resposta;
	}
	
	private Fracao subtracao(Fracao firstFracao, Fracao secondFracao) {
		resposta.setNumerador(firstFracao.getNumerador().multiply(secondFracao.getDenominador())
					.subtract(secondFracao.getNumerador().multiply(firstFracao.getDenominador())));
		resposta.setDenominador(firstFracao.getNumerador().multiply(secondFracao.getDenominador()));
		
		return resposta;
	}
	
	private Fracao multiplicacao(Fracao firstFracao, Fracao secondFracao) {
		resposta.setNumerador(firstFracao.getNumerador().multiply(secondFracao.getNumerador()));
		resposta.setNumerador(firstFracao.getDenominador().multiply(secondFracao.getDenominador()));

		return resposta;
	}
	
	private Fracao divisao(Fracao firstFracao, Fracao secondFracao) {
		resposta.setNumerador(firstFracao.getNumerador().multiply(secondFracao.getDenominador()));
		resposta.setDenominador(firstFracao.getDenominador().multiply(secondFracao.getNumerador()));
		
		return resposta;
	}

}
