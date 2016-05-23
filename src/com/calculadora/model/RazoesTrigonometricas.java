package com.calculadora.model;

import java.util.Scanner;

public class RazoesTrigonometricas {
	public static final int SENO = 1;
	public static final int COSSENO = 2;
	public static final int TANGENTE = 3;
	public static final int VOLTAR = 4;
	
	private Double catetoOposto;
	private Double catetoAdjacente;
	private Double hipotenuza;
	private Double resultado;
	
	public Double getCatetoOposto() {
		return catetoOposto;
	}
	public void setCatetoOposto(Double catetoOposto) {
		this.catetoOposto = catetoOposto;
	}
	public Double getCatetoAdjacente() {
		return catetoAdjacente;
	}
	public void setCatetoAdjacente(Double catetoAdjacente) {
		this.catetoAdjacente = catetoAdjacente;
	}
	public Double getHipotenuza() {
		return hipotenuza;
	}
	public void setHipotenuza(Double hipotenuza) {
		this.hipotenuza = hipotenuza;
	}
	public Double getResultado() {
		return resultado;
	}
	public void setResultado(Double resultado) {
		this.resultado = resultado;
	}
	
	
	
}
