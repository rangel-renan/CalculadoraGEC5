package com.calculadora.service;

import java.math.BigDecimal;
import java.math.MathContext;

import com.calculadora.util.TipoCalPorcentagem;

public class PorcentagemServiceImpl implements PorcentagemService {

	@Override
	public BigDecimal calcular(BigDecimal valor, String porcentagem, TipoCalPorcentagem tipoPorcentagem) {
		
		switch (tipoPorcentagem) {
			case PORCENTAGEM:
				return calcularPorcentagemEmValor(valor, porcentagem);
			case QUANTIDADE:
				return calcularQuantidade(valor, porcentagem);
			case TOTAL:
				return calcularTotal(valor, porcentagem);
		}
		
		return null;
	}
	
	private BigDecimal calcularPorcentagemEmValor(BigDecimal valor, String valorTotal) throws NumberFormatException {
		return (valor.divide(new BigDecimal(valorTotal), MathContext.DECIMAL128))
				.multiply(new BigDecimal(100.0));
	}
	
	private BigDecimal calcularQuantidade(BigDecimal valor, String porcentagem) throws NumberFormatException {
		return ((valor.multiply(new BigDecimal(porcentagem)))
				.divide(new BigDecimal(100), MathContext.DECIMAL128));
	}
	
	private BigDecimal calcularTotal(BigDecimal valor, String porcentagem) throws NumberFormatException {
		return valor.multiply(new BigDecimal(porcentagem), MathContext.DECIMAL128);
	}
	
}
