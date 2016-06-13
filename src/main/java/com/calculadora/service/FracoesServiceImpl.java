package com.calculadora.service;

import java.math.BigDecimal;

import com.calculadora.model.Fracao;
import com.calculadora.util.TipoOperacao;

public class FracoesServiceImpl implements FracoesService {
	private Fracao resposta;
	
	public FracoesServiceImpl() {
		resposta = new Fracao();
	}
	
	@Override
	public Fracao calcular(Fracao firstFracao, Fracao secondFracao, TipoOperacao tipoOperacao) {
		
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
		return addResposta((firstFracao.getNumerador().multiply(secondFracao.getDenominador()))
							.add((secondFracao.getNumerador().multiply(firstFracao.getDenominador()))), 
							firstFracao.getDenominador().multiply(secondFracao.getDenominador()));
	}
	
	private Fracao subtracao(Fracao firstFracao, Fracao secondFracao) {
		return addResposta((firstFracao.getNumerador().multiply(secondFracao.getDenominador()))
							.subtract((secondFracao.getNumerador().multiply(firstFracao.getDenominador()))),
							firstFracao.getDenominador().multiply(secondFracao.getDenominador()));
	}
	
	private Fracao multiplicacao(Fracao firstFracao, Fracao secondFracao) {
		return addResposta(firstFracao.getNumerador().multiply(secondFracao.getNumerador()),
						   firstFracao.getDenominador().multiply(secondFracao.getDenominador()));
	}
	
	private Fracao divisao(Fracao firstFracao, Fracao secondFracao) {
		return addResposta(firstFracao.getNumerador().multiply(secondFracao.getDenominador()),
						   firstFracao.getDenominador().multiply(secondFracao.getNumerador()));
	}
	
	private BigDecimal verificaSimplificacao(BigDecimal numerador, BigDecimal denominador) {
		BigDecimal resultadoSimplificado;
		
		if (numerador.remainder(denominador).equals(BigDecimal.ZERO)) {
			resultadoSimplificado = numerador.divide(denominador);
			return resultadoSimplificado;
		} else if (denominador.equals(BigDecimal.ONE)) {
			return denominador;
		}
		
		return null;
	}
	
	private Fracao addResposta(BigDecimal numeradorResult, BigDecimal denominadorResult) {
		BigDecimal simplificacao = verificaSimplificacao(numeradorResult, denominadorResult);
		
		if (simplificacao != null) {
			resposta.setNumerador(simplificacao);
			resposta.setDenominador(simplificacao);
		} else {
			resposta.setNumerador(numeradorResult);
			resposta.setDenominador(denominadorResult);
		}
		
		return resposta;
	}
	
}
