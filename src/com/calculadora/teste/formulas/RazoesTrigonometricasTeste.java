package com.calculadora.teste.formulas;

import com.calculadora.formulas.RazoesTrigonometricas;

public class RazoesTrigonometricasTeste {

	public static void main(String[] args) {
		
		RazoesTrigonometricas razoes = new RazoesTrigonometricas();
		
		razoes.setCatetoAdjacente(3);
		razoes.setCatetoOposto(4);
		razoes.setHipotenuza(5);
		razoes.calculaCosseno();
		System.out.println("Cosseno: " + razoes.getResultado());
		razoes.calculaSeno();
		System.out.println("Seno: " + razoes.getResultado());
		razoes.calculaTangente();
		System.out.println("Tangente: " + razoes.getResultado());
	}
}
