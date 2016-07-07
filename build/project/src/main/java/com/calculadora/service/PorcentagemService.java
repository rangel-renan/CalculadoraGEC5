package com.calculadora.service;

import java.math.BigDecimal;

import com.calculadora.util.enums.TipoCalPorcentagem;

public interface PorcentagemService {
	
	public BigDecimal calcular(BigDecimal valor, String porcentagem, TipoCalPorcentagem tipoPorcentagem) throws NumberFormatException;
}
