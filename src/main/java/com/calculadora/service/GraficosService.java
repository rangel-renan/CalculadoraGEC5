package com.calculadora.service;

import com.calculadora.util.Eixos;
import com.calculadora.util.Reta;
import com.calculadora.util.equacao.Equacao;

public interface GraficosService {
	
	public Eixos gerarEixos(int largura, int altura, double xLow, double xHi, double yLow, double yHi);
	public Reta gerarGrafico(Equacao equacao, int largura, int altura, double xLow, double xHi, double yLow, double yHi);
}
