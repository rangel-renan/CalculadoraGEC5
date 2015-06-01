package com.calculadora.teste.formulas;

import com.calculadora.formulas.Pitagoras;

public class PitagorasTeste {

	public static void main(String args[]){
		Pitagoras pitagoras = new Pitagoras();
		
		pitagoras.setCateto1(4);
		pitagoras.setCateto2(3);
		pitagoras.tiraHipotenuza();
		pitagoras.mostraHipotenuza();
		if(pitagoras.getHipotenuza() == 5){
			System.out.println("Teste concluido com sucesso");
		} else {
			System.out.println("Teste nao concluido!!!");
		}
	}
}
