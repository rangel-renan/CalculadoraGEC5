package com.calculadora.equacao.grafico;

import com.calculadora.util.equacao.Equacao;
import com.calculadora.util.excessoes.EquationVariableException;
import com.calculadora.util.excessoes.SintaxeEquacaoIncorretaException;

/**
 * Command line program which can evaluate a mathematical expression without variables
 * @author Adam Black
 * modified: 20/04/2006
 */
public class EvalEq
{
	/**
	 * Evaluates a mathematical expression and outputs a numerical answer to the console
	 * @param args mathematical expression to be evaluated
	 */
	public static void main(String[] args)
	{
		StringBuilder eqInfix = new StringBuilder();
		for (int i = 0; i < args.length; i++)
			eqInfix.append(args[i]);
		
		try
		{
			//Set up equation from command line argument(s)
			Equacao eq = new Equacao("4 + 5");
			//Evaluate answer and print to console
			System.out.println(eq.evaluate());
		}
		catch (SintaxeEquacaoIncorretaException e)
		{
			System.out.println("ERR1: The equation is not well-formed");
		}
		catch (EquationVariableException e)
		{
			System.out.println("ERR2: Unable to evaluate equation");
		}
	}
}
