package com.calculadora.potenciacao;

import java.util.Scanner;

public class Potenciacao {
	
	private int number;
	private int expoente;
	private int resultado;	
	private Scanner input = new Scanner(System.in);

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getExpoente() {
		return expoente;
	}

	public void setExpoente(int expoente) {
		this.expoente = expoente;
	}

	public int getResultado() {
		return resultado;
	}

	public void setResultado(int resultado) {
		this.resultado = resultado;
	}	

	public void resultadoPotencial(){
		
		System.out.print("Entre com o numero: ");
		this.setNumber(input.nextInt());
		
		System.out.print("Entre com o expoente: ");
		this.setExpoente(input.nextInt());
		
		this.setResultado(getNumber());
		
		if(expoente == 0){
			this.setResultado(1);
		}
		
		else{
			for(int contador = 1; contador < expoente; contador++){ 
			
			this.setResultado(this.getResultado() * this.getNumber());
			}
		}
		
		System.out.println("O numero: " + this.getNumber() + " elevado a: " + this.getExpoente() + " e igual a: " + this.getResultado());
	}
	
}
