package com.calculadora.equacao;


/**
 * Convert a prefix mathematical expression into postfix notation
 * @author Adam Black
 * modified: 12/04/06
 */
public class RPNConverter {

	/**
	 * Convert prefix into postfix
	 * @param args[n] equation
	 */
	public static void main(String[] args) {
		StringBuilder eqInfix = new StringBuilder();
		for (int i = 0; i < args.length; i++)
			eqInfix.append(args[i]);
		
		try
		{
			Equacao eq = new Equacao("9");
			//Postfix conversion is done here
			System.out.println(eq.getPostfix());
			System.out.println(eq.evaluate());
		}
		catch (SintaxeEquacaoIncorretaException e)
		{
			System.out.println("ERR1: The equation is not well-formed");
		} catch (EquationVariableException e) {
			System.out.println("Mais de uma váriavel.");
		}
	}

}
