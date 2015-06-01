package com.calculadora.formulas;

import java.util.Scanner;

public class Pitagoras {
	private double cateto1;
	private double cateto2;
	private double hipotenuza;
	
	public double getCateto1() {
		return cateto1;
	}
	public void setCateto1(double cateto1) {
		this.cateto1 = cateto1;
	}
	public double getCateto2() {
		return cateto2;
	}
	public void setCateto2(double cateto2) {
		this.cateto2 = cateto2;
	}
	public double getHipotenuza() {
		return hipotenuza;
	}
	public void setHipotenuza(double hipotenuza) {
		this.hipotenuza = hipotenuza;
	}
	
	public double leNum(){
		Scanner input = new Scanner(System.in);
		
		System.out.print("Entre com o cosseno: ");
		return input.nextInt();
	}

	public void tiraHipotenuza(){
		this.setHipotenuza(Math.sqrt(Math.pow(this.getCateto1(), 2) + Math.pow(this.getCateto2(), 2)));
	}
	
	public void mostraHipotenuza(){
		System.out.println("Hipotenuza igual a: " + getHipotenuza());
	}
	
	public void fazCalculo(){
		this.setCateto1(this.leNum());
		this.setCateto2(this.leNum());
		this.tiraHipotenuza();
		this.mostraHipotenuza();
	}
}
