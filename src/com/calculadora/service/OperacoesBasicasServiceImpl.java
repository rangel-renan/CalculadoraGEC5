package com.calculadora.service;

import java.math.BigDecimal;

import com.calculadora.util.TipoOperacao;

public class OperacoesBasicasServiceImpl implements OperacoesBasicasService {
	
	@Override
	public BigDecimal calcular(BigDecimal valor1, BigDecimal valor2, TipoOperacao tipoOperacao) {
		
		switch (tipoOperacao) {
			case SOMA:
				return operacaodeSoma(valor1, valor2);
			case SUBTRACAO:
				return operacaodeSubtracao(valor1, valor2);
			case MULTIPLICACAO:
				return operacaodeMultiplicacao(valor1, valor2);
			case DIVISAO:
				return operacaodeDivisao(valor1, valor2);
			default:
				return null;
		}
		
	}
	
	public BigDecimal calcularRaizQuadrada(BigDecimal valor) {
		return operacaoRaizQuadrada(valor);
	}
	
	public BigDecimal operacaodeSoma(BigDecimal valor1, BigDecimal valor2) {
		return valor1.add(valor2);
	}
	
	public BigDecimal operacaodeSubtracao(BigDecimal valor1, BigDecimal valor2) {
		return valor1.subtract(valor2);
	}
	
	public BigDecimal operacaodeMultiplicacao(BigDecimal valor1, BigDecimal valor2) {
		return valor1.multiply(valor2);
	}
	
	public BigDecimal operacaodeDivisao(BigDecimal valor1, BigDecimal valor2) {
		return valor1.divide(valor2);
	}
	
	public BigDecimal operacaoRaizQuadrada(BigDecimal valor1) {
		return new BigDecimal(Math.sqrt(valor1.doubleValue()));
	}

	
}
