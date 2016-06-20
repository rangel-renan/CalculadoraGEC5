package com.calculadora.equacao;

public class Text {

	public static void main(String[] args) {
		
		try {
			Equacao eq1 = new Equacao("5+sin(x)/9.4");
			System.out.println(eq1.getPostfix());
		} catch (SintaxeEquacaoIncorretaException e) {
			System.out.println("Equação não formatada corretamente.");
			System.exit(1);
		}
		
		
	}

}
