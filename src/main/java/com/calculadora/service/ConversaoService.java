package com.calculadora.service;

import java.math.BigDecimal;

import javax.measure.unit.Unit;

public interface ConversaoService {
	
	public BigDecimal converterComprimentos(BigDecimal valor1, Unit<?> tipoInicial, Unit<?> tipoFinal);
}
