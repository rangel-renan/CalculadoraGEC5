package com.calculadora.model;

public class Pitagoras {
	private Double cateto1;
	private Double cateto2;
	private Double hipotenuza;
	
	public Pitagoras() {
	}
	
	public Pitagoras(Double cateto1, Double cateto2, Double hipotenuza) {
		this.cateto1 = cateto1;
		this.cateto2 = cateto2;
		this.hipotenuza = hipotenuza;
	}
	
	public Double getCateto1() {
		return cateto1;
	}
	public void setCateto1(Double cateto1) {
		this.cateto1 = cateto1;
	}
	public Double getCateto2() {
		return cateto2;
	}
	public void setCateto2(Double cateto2) {
		this.cateto2 = cateto2;
	}
	public Double getHipotenuza() {
		return hipotenuza;
	}
	public void setHipotenuza(Double hipotenuza) {
		this.hipotenuza = hipotenuza;
	}
	
}
