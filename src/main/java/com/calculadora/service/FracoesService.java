package com.calculadora.service;

import com.calculadora.model.Fracao;

public interface FracoesService {
	
	public Fracao calcular(Fracao firstFracao, Fracao secondFracao, String operacao);
}
