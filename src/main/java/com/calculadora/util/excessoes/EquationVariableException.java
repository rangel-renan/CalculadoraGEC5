package com.calculadora.util.excessoes;
/**
 * Used by the Equation class to throw errors when the user tries to create an equation which contains more than
 * one variable
 * @author Adam Black
 * modified: 18/4/16
 */
public class EquationVariableException extends Exception 
{
	public EquationVariableException(String message)
	{
		super(message);
	}
	
	public EquationVariableException()
	{
		super();
	}	
}
