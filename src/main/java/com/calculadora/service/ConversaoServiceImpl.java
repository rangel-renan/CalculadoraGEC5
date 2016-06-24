package com.calculadora.service;

import java.math.BigDecimal;

import javax.measure.converter.UnitConverter;
import javax.measure.unit.Unit;

public class ConversaoServiceImpl implements ConversaoService {
	
	@Override
	public BigDecimal converter(BigDecimal valor, Unit<?> tipoInicial, Unit<?> tipoFinal) throws NumberFormatException {
		UnitConverter toConverter = tipoInicial.getConverterTo(tipoFinal);
		return new BigDecimal(toConverter.convert(valor.doubleValue()));
	}
	
	public BigDecimal converterMoeda() {
		
		return null;
	}
	
	
}
