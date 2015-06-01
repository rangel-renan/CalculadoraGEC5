package com.calculadora.execucao;

import java.util.Scanner;

import com.calculadora.formulas.Bhaskara;
import com.calculadora.formulas.CossenoEntreVetores;
import com.calculadora.formulas.Pitagoras;
import com.calculadora.formulas.ProjecaoDeUmVetor;
import com.calculadora.matriz.Determinante;
import com.calculadora.operacoesbasicas.OperacoesBasicasMenu;
import com.calculadora.potenciacao.Potenciacao;
import com.calculadora.raizquadra.RaizQuadrada;
import com.calculadora.vetores.ModuloVetor;
import com.calculadora.vetores.ProdutoEscalar;
import com.calculadora.vetores.ProdutoNumeroVetor;
import com.calculadora.vetores.ProdutoVetorial;

public class Menu {
	
	public static final int SAIR = 0;
	public static final int CALCULADORA = 1;
	public static final int BHASKARA = 2;
	public static final int PRODUTO_ESCALAR = 3;
	public static final int MODULO_VETOR = 4;
	public static final int COSSENO_ENTRE_VETORES = 5;
	public static final int RAIZ_QUADRADA = 6;
	public static final int POTENCIACAO = 7;
	public static final int DETERMINANTE = 8;
	public static final int PRODUTO_NUM_VETOR = 9;
	public static final int PRODUTO_VETORIAL = 10;
	public static final int PROJECAO_VETOR = 11;
	public static final int PITAGORAS = 12;

	public static void main(String Args[]){

		boolean sair = false;

		Scanner input = new Scanner(System.in);

		while(!sair){
			int opcaoMenu;
			System.out.println("********MENU********\n");
			System.out.println("1- Calculadora");
			System.out.println("2- Bhaskara");
			System.out.println("3- Produto Escalar de 2 vetores");
			System.out.println("4- Modulo de um vetor");
			System.out.println("5- Cosseno entre vetores");
			System.out.println("6- Raiz Quadrada de um numero");
			System.out.println("7- Potencialização");
			System.out.println("8- Determinante");
			System.out.println("9- Produto Entre Um Numero e Um Vetor");
			System.out.println("10- Produto Vetorial");
			System.out.println("11- Projecao de um vetor sobre o outro");
			System.out.println("12- Pitagoras");
			System.out.println("0-Sair");
			System.out.print("\nEntre com a opção desejada: ");
			opcaoMenu = Integer.parseInt(input.nextLine());
			switch(opcaoMenu){
			case CALCULADORA:
				OperacoesBasicasMenu operacoesSubMenu = new OperacoesBasicasMenu();
				operacoesSubMenu.menu();
				break;
			case BHASKARA:
				Bhaskara bhaskara = new Bhaskara();
				bhaskara.formulaBhaskara();
				break;
			case PRODUTO_ESCALAR:
				ProdutoEscalar produto = new ProdutoEscalar();
				produto.produto();
				break;
			case MODULO_VETOR:
				ModuloVetor modulo = new ModuloVetor();
				modulo.modulo();
				break;
			case COSSENO_ENTRE_VETORES:
				CossenoEntreVetores cosseno = new CossenoEntreVetores();
				cosseno.cosseno();
				break;
			case RAIZ_QUADRADA:
				RaizQuadrada raizquadrada = new RaizQuadrada();
				raizquadrada.raizQuadrada();
				break;
			case POTENCIACAO:
				Potenciacao potenciacao = new Potenciacao();
				potenciacao.resultadoPotencial();
				break;
			case DETERMINANTE:
				Determinante determinante = new Determinante();
				determinante.determinanteMatrizNxN();
				break;
			case PRODUTO_NUM_VETOR:
				ProdutoNumeroVetor produtoNumeroVetor = new ProdutoNumeroVetor();
				produtoNumeroVetor.produtoNumeroVetor();
				break;
			case PRODUTO_VETORIAL:
				ProdutoVetorial produtoVet = new ProdutoVetorial();
				produtoVet.produto();
				break;
			case PROJECAO_VETOR:
				ProjecaoDeUmVetor projecao = new ProjecaoDeUmVetor();
				projecao.projecao();
				break;
			case PITAGORAS:
				Pitagoras pitagoras = new Pitagoras();
				pitagoras.fazCalculo();
				break;
			case SAIR:
				sair = true;
				break;
			}
		}
		input.close();
		System.out.println("Calculadora finalizada!!!");
	}
}
