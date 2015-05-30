package com.calculadora.calculadora;

import java.util.Scanner;

/**
 * @author Tiago
 *
 */

public class OperacoesBasicasMenu {
	private static final int SAIR = 0;
	private static final int SOMA = 1;
	private static final int SUBTRACAO = 2;
	private static final int MULTIPLICACAO = 3;
	private static final int DIVISAO = 4;
	
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
				operacaoBasica.lerValores();
				System.out.printf("Resultado: %.2f", operacaoBasica.operacaodeSoma());
				break;
			case SUBTRACAO:
				operacaoBasica.lerValores();
				System.out.printf("Resultado: %.2f", operacaoBasica.operacaodeSubtracao());
				break;
			case MULTIPLICACAO:
				operacaoBasica.lerValores();
				System.out.printf("Resultado: %.2f", operacaoBasica.operacaodeMultiplicacao());
				break;
			case DIVISAO:
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
