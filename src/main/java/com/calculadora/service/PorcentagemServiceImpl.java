package com.calculadora.service;

import java.math.BigDecimal;
import java.math.RoundingMode;

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
	
	private BigDecimal calcularPorcentagemEmValor(BigDecimal valor, String porcentagem) {
		BigDecimal quantidadeTotal = calcularQuantidade(valor, porcentagem);
		return quantidadeTotal.divide(new BigDecimal(porcentagem), 0, RoundingMode.HALF_UP);
	}
	
	private BigDecimal calcularQuantidade(BigDecimal valor, String porcentagem) {
		return valor.divide(new BigDecimal(porcentagem), 0, RoundingMode.HALF_UP);
	}
	
	private BigDecimal calcularTotal(BigDecimal valor, String porcentagem) {
		return valor.multiply(new BigDecimal(porcentagem)).setScale(0, RoundingMode.HALF_UP);
	}
	
}
