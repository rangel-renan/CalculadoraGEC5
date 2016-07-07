package com.calculadora.service;

import com.calculadora.model.Fracao;
import com.calculadora.util.enums.TipoOperacao;

public interface FracoesService {
	
	public Fracao calcular(Fracao firstFracao, Fracao secondFracao, TipoOperacao tipoOperacao);
}
