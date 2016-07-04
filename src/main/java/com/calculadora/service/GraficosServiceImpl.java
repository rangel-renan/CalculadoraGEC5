package com.calculadora.service;

import com.calculadora.util.Eixos;
import com.calculadora.util.Reta;
import com.calculadora.util.equacao.Equacao;

public class GraficosServiceImpl implements GraficosService {
	
	public Eixos gerarEixos(int largura, int altura, double xLow, double xHi, double yLow, double yHi) {
		return new Eixos(largura, altura, xLow, xHi, 1, yLow, yHi, 1);
	}
	
	public Reta gerarGrafico(Equacao equacao, int largura, int altura, double xLow, double xHi, double yLow, double yHi) {
		Eixos axes = new Eixos(largura, altura, xLow, xHi, 1, yLow, yHi, 1);
		return new Reta(equacao, xLow, xHi, 0.1, axes);
	}
	
}
