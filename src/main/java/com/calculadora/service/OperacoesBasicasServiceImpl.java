package com.calculadora.service;

import java.math.BigDecimal;

import com.calculadora.util.TipoOperacao;

public class OperacoesBasicasServiceImpl implements OperacoesBasicasService {
	
	@Override
	public BigDecimal calcular(BigDecimal valor1, BigDecimal valor2, String operacao) {
		TipoOperacao tipoOperacao = TipoOperacao.getOperacao(operacao);
		
		switch (tipoOperacao) {
			case SOMA:
				return operacaodeSoma(valor1, valor2);
			case SUBTRACAO:
				return operacaodeSubtracao(valor1, valor2);
			case MULTIPLICACAO:
				return operacaodeMultiplicacao(valor1, valor2);
			case DIVISAO:
				return operacaodeDivisao(valor1, valor2);
			case RESTO:
				return operacaoResto(valor1, valor2);
			default:
				return null;
		}
		
	}
	
	public BigDecimal calcularRaizQuadrada(BigDecimal valor) {
		return operacaoRaizQuadrada(valor);
	}
	
	public BigDecimal calcularPI() {
		return new BigDecimal(Math.PI);
	}
	
	public BigDecimal changeSinal(BigDecimal valor) {
		return valor.negate();
	}

	public BigDecimal valorEuler() {
		return new BigDecimal(Math.E);
	}
	
	
//	public BigDecimal calcularPorcentagem(BigDecimal valor, String porcentagem) {
//		BigDecimal porcentagemEscala = new BigDecimal(new BigInteger(porcentagem), 2);	
//		return valor.multiply(porcentagemEscala).setScale(0);
//	}
//	
	private BigDecimal operacaodeSoma(BigDecimal valor1, BigDecimal valor2) {
		return valor1.add(valor2);
	}
	
	private BigDecimal operacaodeSubtracao(BigDecimal valor1, BigDecimal valor2) {
		return valor1.subtract(valor2);
	}
	
	private BigDecimal operacaodeMultiplicacao(BigDecimal valor1, BigDecimal valor2) {
		return valor1.multiply(valor2);
	}
	
	private BigDecimal operacaodeDivisao(BigDecimal valor1, BigDecimal valor2) {
		return valor1.divide(valor2);
	}
	
	private BigDecimal operacaoRaizQuadrada(BigDecimal valor1) {
		return new BigDecimal(Math.sqrt(valor1.doubleValue()));
	}

	public BigDecimal operacaoResto(BigDecimal valor, BigDecimal divisor) {
		return valor.remainder(divisor);
	}
	
}
