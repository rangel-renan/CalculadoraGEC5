package com.calculadora.service;

import java.math.BigDecimal;

import com.calculadora.model.OperacoesBasicas;
import com.calculadora.model.RaizQuadrada;
import com.calculadora.util.TipoOperacao;

public class OperacoesBasicasServiceImpl implements OperacoesBasicasService {
	
	private OperacoesBasicas operacoesBasicas;
	private RaizQuadrada raizQuadrada;
	
	public OperacoesBasicasServiceImpl(OperacoesBasicas operacoesBasicas) {
		this.operacoesBasicas = operacoesBasicas;
	}
	
	public OperacoesBasicasServiceImpl(RaizQuadrada raizQuadrada) {
		this.raizQuadrada = raizQuadrada;
	}

	@Override
	public BigDecimal calcular(TipoOperacao tipoOperacao) {
		
		switch (tipoOperacao) {
			case SOMA:
				return operacaodeSoma();
			case SUBTRACAO:
				return operacaodeSubtracao();
			case MULTIPLICACAO:
				return operacaodeMultiplicacao();
			case DIVISAO:
				return operacaodeDivisao();
			case RAIZ_QUADRADA:
				return operacaoRaizQuadrada();
			default:
				return null;
		}
		
	}
	
	public BigDecimal operacaodeSoma() {
		return operacoesBasicas.getValorA().add(operacoesBasicas.getValorB());
	}
	
	public BigDecimal operacaodeSubtracao() {
		return operacoesBasicas.getValorA().subtract(operacoesBasicas.getValorB());
	}
	
	public BigDecimal operacaodeMultiplicacao() {
		return operacoesBasicas.getValorA().multiply(operacoesBasicas.getValorB());
	}
	
	public BigDecimal operacaodeDivisao() {
		return operacoesBasicas.getValorA().divide(operacoesBasicas.getValorB());
	}
	
	public BigDecimal operacaoRaizQuadrada() {
		return new BigDecimal(Math.sqrt(raizQuadrada.getNumber().doubleValue()));
	}

	
}
