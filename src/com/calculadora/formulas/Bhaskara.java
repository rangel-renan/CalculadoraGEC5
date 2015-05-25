package com.calculadora.formulas;

import java.util.Scanner;

public class Bhaskara {
	private double a;
	private double b;
	private double c;
	private double delta;
	private double resultPositivo;
	private double resultNegativo;
	
	public double getA() {
		return a;
	}
	public void setA(double a) {
		this.a = a;
	}
	public double getB() {
		return b;
	}
	public void setB(double b) {
		this.b = b;
	}
	public double getC() {
		return c;
	}
	public void setC(double c) {
		this.c = c;
	}
	
	public double getDelta() {
		return delta;
	}
	public void setDelta(double delta) {
		this.delta = delta;
	}
		
	public double getResultPositivo() {
		return resultPositivo;
	}
	
	public void setResultPositivo(double resultPositivo) {
		this.resultPositivo = resultPositivo;
	}
	
	public double getResultNegativo() {
		return resultNegativo;
	}
	
	public void setResultNegativo(double resultNegativo) {
		this.resultNegativo = resultNegativo;
	}
	
	public void fazdelta(){
		
		double quadradoB = getB() * getB();
		this.setDelta(quadradoB - (4 * getA() * getB()));
	}
	
	public void levalores(){
		Scanner input = new Scanner(System.in);
		
		System.out.println("Entre com o valor de a");
		this.setA(input.nextDouble());
		System.out.println("Entre com o valor de b");
		this.setA(input.nextDouble());
		System.out.println("Entre com o valor de c");
		this.setA(input.nextDouble());
		input.close();
	}
	
	public void acharResultPositivo(){
		setResultPositivo( ( -( getB() ) + ( Math.sqrt(delta) ) ) / 2 * getA());
	}
	
	public void acharResultNegativo(){
		setResultNegativo( ( -( getB() ) - ( Math.sqrt(delta) ) ) / 2 * getA());
	}
	
	public void MostraResultados(){
		System.out.println("x1 = " + getResultPositivo());
		System.out.println("x2 = " + getResultNegativo());
	}
	
	public void formulaBhaskara(){
		this.levalores();
		this.fazdelta();
		if(getDelta() < 0){
			System.out.println("Delta com valor " + getDelta() + "Impossivel continuar a expressao");
		} else {
			this.acharResultPositivo();
			this.acharResultNegativo();
			this.MostraResultados();
		}
	}
}
