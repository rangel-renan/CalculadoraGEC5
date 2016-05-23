package com.calculadora.service;

import com.calculadora.model.Pitagoras;

public class PitagorasServiceImpl {
	private Pitagoras pitagoras;
	
	public PitagorasServiceImpl() {
		pitagoras = new Pitagoras();
	}
	
	public <T extends Number> Double calcularHipotenusa(T cosseno1, T cosseno2) {
		return calcular();
	}
	
	public Double calcular(){
		Double hipotenusa = Math.sqrt(Math.pow(pitagoras.getCateto1(), 2) + Math.pow(pitagoras.getCateto2(), 2));
		pitagoras.setHipotenuza(hipotenusa);
		
		return hipotenusa;
	}
//	
//	public void mostraHipotenuza(){
//		System.out.println("Hipotenuza igual a: " + getHipotenuza());
//	}
//	
//	public void fazCalculo(){
//		this.setCateto1(this.leNum());
//		this.setCateto2(this.leNum());
//		this.tiraHipotenuza();
//		this.mostraHipotenuza();
//	}
//	public double leNum(){
//		Scanner input = new Scanner(System.in);
//		
//		System.out.print("Entre com o cosseno: ");
//		return input.nextInt();
//	}

}
