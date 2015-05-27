package com.calculadora.calculadora;

import java.util.Scanner;

public class OperacoesBasicasMenu {
	private static final int SAIR = 0;
	private static final int SOMA = 1;
	private static final int SUBTRACAO = 2;
	private static final int MULTIPLICACAO = 3;
	private static final int DIVISAO = 5;
	private static final int RAIZ_QUADRADA = 6;
	
	public void menu() {
		Scanner input = new Scanner(System.in);
		OperacoesBasicas operacaoBasica = new OperacoesBasicas();
		
		System.out.println("\n**Calculadora Básica**\n");
		System.out.println("1 - Soma");
		System.out.println("2 - Subtração");
		System.out.println("3 - Multiplicação");
		System.out.println("4 - Divisão");
		System.out.println("0 para Voltar");
		
		boolean sair = false;
		System.out.print("\nEntre com a Opção: ");
		while (!sair) {
			switch (input.nextInt()) {
			case SOMA:
				System.out.println("\n**Soma**\n");
				operacaoBasica.lerValores();
				System.out.printf("Resultado: %.2f", operacaoBasica.operacaodeSoma());
				break;
			case SUBTRACAO:
				System.out.println("\n**Subtracao**\n");
				operacaoBasica.lerValores();
				System.out.printf("Resultado: %.2f", operacaoBasica.operacaodeSubtracao());

				break;
			case MULTIPLICACAO:
				System.out.println("\n**Multiplicacao**\n");
				operacaoBasica.lerValores();
				System.out.printf("Resultado: %.2f", operacaoBasica.operacaodeMultiplicacao());
				break;
			case DIVISAO:
				System.out.println("\n**Divisao**\n");
				operacaoBasica.lerValores();
				System.out.printf("Resultado: %.2f", operacaoBasica.operacaodeDivisao());
				break;
			case SAIR:
				sair = true;
				break;
			default:
				System.out.println("Opção Incorreta.");
			}
			System.out.print("\n\nEntre com a Opção: ");
		}
		
	}
	
}
