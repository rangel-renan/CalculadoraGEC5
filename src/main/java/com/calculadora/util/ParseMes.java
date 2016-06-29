package com.calculadora.util;

import java.math.BigDecimal;

import com.calculadora.util.enums.TipoPeriodos;

public class ParseMes {
	
	public static BigDecimal parseToMes(BigDecimal valor, TipoPeriodos tipoPeriodos) {
		
		switch (tipoPeriodos) {
			case DIAS:
				return diaToMes(valor);
			case ANOS:
				return anoToMes(valor);
			case MESES:
				return valor;
			default:
				return null;
		}
	}
	
	public static BigDecimal diaToMes(BigDecimal dias) {
		return (dias.multiply(new BigDecimal("0.0328767")));
	}
	
	public static BigDecimal anoToMes(BigDecimal anos) {
		return (anos.multiply(new BigDecimal("12")));
	}
	
	public static void main(String[] args) {
		System.out.println(ParseMes.diaToMes(new BigDecimal(20)));
	}
}
