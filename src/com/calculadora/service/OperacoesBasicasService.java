package com.calculadora.service;

import java.math.BigDecimal;

import com.calculadora.util.TipoOperacao;

public interface OperacoesBasicasService {
	
	public BigDecimal calcular(TipoOperacao tipoOperacao);
}
