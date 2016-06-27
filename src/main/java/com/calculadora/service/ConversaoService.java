package com.calculadora.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.MalformedURLException;

import javax.measure.unit.Unit;

import com.calculadora.util.excessoes.ImpossivelConverterException;

public interface ConversaoService {
	
	public BigDecimal converter(BigDecimal valor1, Unit<?> tipoInicial, Unit<?> tipoFinal) throws NumberFormatException;
	public BigDecimal converterMoeda(BigDecimal valor, String firstMoeda, String moedaResultante) throws NumberFormatException, MalformedURLException, IOException, ImpossivelConverterException;
}
