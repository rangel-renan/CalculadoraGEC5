package com.calculadora.operacoesbasicas;

import java.util.Scanner;

/**
 * @author Tiago
 *
 */

public class OperacoesBasicasMenu {
	
	public void menu() {
		Scanner input = new Scanner(System.in);
		OperacoesBasicas operacaoBasica = new OperacoesBasicas();
		
		System.out.println("\n**Calculadora Básica**\n");
		
		for (OperacoesBasicasOpcoes op : OperacoesBasicasOpcoes.values()) {
			System.out.println(op.getNumOpcao() + " - " + op.nomeOperacao());
		}
		
		boolean sair = false;
		while (!sair) {
			System.out.print("\nEntre com a Opção: ");
			int opcao = input.nextInt();
			
			if (opcao == OperacoesBasicasOpcoes.SOMA.getNumOpcao()) {
				operacaoBasica.lerValores();
				System.out.printf("Resultado: %.2f", operacaoBasica.operacaodeSoma());
			} else if (opcao == OperacoesBasicasOpcoes.SUBTRACAO.getNumOpcao()) {
				operacaoBasica.lerValores();
				System.out.printf("Resultado: %.2f", operacaoBasica.operacaodeSubtracao());
			} else if (opcao == OperacoesBasicasOpcoes.MULTIPLICACAO.getNumOpcao()) {
				operacaoBasica.lerValores();
				System.out.printf("Resultado: %.2f", operacaoBasica.operacaodeMultiplicacao());
			} else if (opcao == OperacoesBasicasOpcoes.DIVISAO.getNumOpcao()) {
				operacaoBasica.lerValores();
				System.out.printf("Resultado: %.2f", operacaoBasica.operacaodeDivisao());
			} else if (opcao == OperacoesBasicasOpcoes.SAIR.getNumOpcao()) {
				sair = true;
			} else {
				System.out.println("Opção Incorreta.");
			}
		}
		System.out.println();
	}
	
}
