package com.calculadora.execucao;

import java.util.Scanner;

import com.calculadora.formulas.Bhaskara;

public class Menu {
	public static final int CALCULADORA = 1;
	public static final int BHASKARA = 2;

	public static void main(String Args[]){

		boolean sair = false;

		Scanner input = new Scanner(System.in);

		while(!sair){
			int opcaoMenu;
			System.out.println("Entre com a opção desejada: ");
			System.out.println("1-Calculadora");
			System.out.println("2-Bhaskara");
			System.out.println("0-Sair");
			opcaoMenu = Integer.parseInt(input.next());
			switch(opcaoMenu){
			case BHASKARA:
				Bhaskara bhaskara = new Bhaskara();
				bhaskara.formulaBhaskara();
				break;
			case 0:
				sair = true;
				break;
			}
		input.close();
		}
		System.out.println("Calculadora finalizada!!!");
	}
}
