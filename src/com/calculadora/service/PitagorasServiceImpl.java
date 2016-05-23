package com.calculadora.service;

import com.calculadora.model.Pitagoras;

public class PitagorasServiceImpl implements PitagorasService {
	private Pitagoras pitagoras;
	
	public PitagorasServiceImpl(Pitagoras _pitagoras) {
		pitagoras = _pitagoras;
	}
	
	@Override
	public Double calcularHipotenusa() {
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
