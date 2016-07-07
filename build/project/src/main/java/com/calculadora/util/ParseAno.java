package com.calculadora.util;

import java.math.BigDecimal;
import java.math.MathContext;

import com.calculadora.util.enums.TipoPeriodos;

public class ParseAno {
	public static BigDecimal parseToAno(BigDecimal valor, TipoPeriodos tipoPeriodos) {

		switch (tipoPeriodos) {
		case DIAS:
			return diaToAno(valor);
		case ANOS:
			return valor;
		case MESES:
			return mesToAno(valor);
		default:
			return null;
		}
	}

	public static BigDecimal diaToAno(BigDecimal dias) {
		return (dias.divide(new BigDecimal("365"), MathContext.DECIMAL128));
	}

	public static BigDecimal mesToAno(BigDecimal anos) {
		return (anos.divide(new BigDecimal("12"), MathContext.DECIMAL128));
	}

	public static void main(String[] args) {
		System.out.println(ParseMes.diaToMes(new BigDecimal(20)));
	}
}
