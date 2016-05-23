package com.calculadora.model;

public class Bhaskara {
	private Double a;
	private Double b;
	private Double c;
	private Double delta;
	private Double resultPositivo;
	private Double resultNegativo;
	
	public Bhaskara() {
	}
	
	public Bhaskara(Double a, Double b, Double c, Double delta, Double resultPositivo, Double resultNegativo) {
		this.a = a;
		this.b = b;
		this.c = c;
		this.delta = delta;
		this.resultPositivo = resultPositivo;
		this.resultNegativo = resultNegativo;
	}
	
	public Double getA() {
		return a;
	}
	public void setA(Double a) {
		this.a = a;
	}
	public Double getB() {
		return b;
	}
	public void setB(Double b) {
		this.b = b;
	}
	public Double getC() {
		return c;
	}
	public void setC(Double c) {
		this.c = c;
	}
	public Double getDelta() {
		return delta;
	}
	public void setDelta(Double delta) {
		this.delta = delta;
	}
	public Double getResultPositivo() {
		return resultPositivo;
	}
	public void setResultPositivo(Double resultPositivo) {
		this.resultPositivo = resultPositivo;
	}
	public Double getResultNegativo() {
		return resultNegativo;
	}
	public void setResultNegativo(Double resultNegativo) {
		this.resultNegativo = resultNegativo;
	}
	
}
