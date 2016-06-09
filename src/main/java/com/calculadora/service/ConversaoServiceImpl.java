package com.calculadora.service;

import java.math.BigDecimal;

import javax.measure.converter.UnitConverter;
import javax.measure.unit.Unit;

public class ConversaoServiceImpl implements ConversaoService {
	
	/* km   hm	dam	 m	dm	cm	mm */
	@Override
	public BigDecimal converterComprimentos(BigDecimal valor, Unit<?> tipoInicial, Unit<?> tipoFinal) {
		UnitConverter toConverter = tipoInicial.getConverterTo(tipoFinal);
		return new BigDecimal(toConverter.convert(valor.doubleValue()));
	}
	
	/* YOTTA, ZETTA, EXA, PETA, TERA, GIGA, MEGA, KILO, HECTO, DEKA, 
	 * DECI, CENTI, MILLI, MICRO, NANO, PICO, FEMTO, ATTO, ZEPTO, YOCTO */
	public BigDecimal converterArmanezamentoDados(BigDecimal valor, Unit<?> tipoInicial, Unit<?> tipoFinal) {
		UnitConverter toConverter = tipoInicial.getConverterTo(tipoFinal);
		return new BigDecimal(toConverter.convert(valor.doubleValue()));
	}
	
}
