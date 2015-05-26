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
	
	public void fazDelta(){
		
		double quadradoB = getB() * getB();
		double delta = getA() * getC();
		delta *= 4;
		setDelta(quadradoB - delta);
	}
	
	public void levalores(){
		Scanner input = new Scanner(System.in);
		String leitura;
		
		System.out.println("Entre com o valor de a");
		leitura = input.nextLine();
		this.setA(Double.parseDouble(leitura));
		System.out.println("Entre com o valor de b");
		this.setB(input.nextDouble());
		System.out.println("Entre com o valor de c");
		this.setC(input.nextDouble());
		input.close();
	}
	
	public void acharResultPositivo(){
		double raizQuadrada = Math.sqrt(getDelta());
		double result = raizQuadrada - getB();
		result /= 2 * getA();
		setResultPositivo(result);
	}
	
	public void acharResultNegativo(){
		double raizQuadrada = Math.sqrt(getDelta());
		double result = -raizQuadrada - getB();
		result /= 2 * getA();
		setResultNegativo(result);
	}
	
	public void MostraResultados(){
		System.out.println("x1 = " + getResultPositivo());
		System.out.println("x2 = " + getResultNegativo());
	}
	
	public void formulaBhaskara(){
		this.levalores();
		this.fazDelta();
		if(getDelta() < 0){
			System.out.println("Delta com valor " + getDelta() + "Impossivel continuar a expressao");
		} else {
			this.acharResultPositivo();
			this.acharResultNegativo();
			this.MostraResultados();
		}
	}
}
