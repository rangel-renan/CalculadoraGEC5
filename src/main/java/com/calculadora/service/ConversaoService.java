package com.calculadora.service;

import java.math.BigDecimal;

import javax.measure.unit.Unit;

public interface ConversaoService {
	
	public BigDecimal converter(BigDecimal valor1, Unit<?> tipoInicial, Unit<?> tipoFinal) throws NumberFormatException;
}
