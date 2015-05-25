package com.calculadora.execucao;

import java.util.Scanner;

public class Menu {
	public static final int BHASKARA = 2;
	public static final int CALCULADORA = 1;
	
	public static void main(){
		
		boolean sair = false;
		int opcao;
		
		Scanner input = new Scanner(System.in);
		
		while(!sair){
			System.out.println("Entre com a opção desejada: ");
			System.out.println("1-Calculadora");
			System.out.println("2-Bhaskara");
			System.out.println("0-Sair");
			opcao = input.nextInt();
			switch(opcao){
				case 0:
					sair = true;
					break;
			}
		}
	input.close();
	}
}
