package com.calculadora.model;

public class Coordenadas {
	private Double XInc;
	private Double YInc;
	
	public Coordenadas() {
	}
	
	public Coordenadas(Double XInc, Double YInc) {
		this.XInc = XInc;
		this.YInc = YInc;
	}

	public Double getXInc() {
		return XInc;
	}

	public void setXInc(Double xInc) {
		XInc = xInc;
	}

	public Double getYInc() {
		return YInc;
	}

	public void setYInc(Double yInc) {
		YInc = yInc;
	}
	
	
}
