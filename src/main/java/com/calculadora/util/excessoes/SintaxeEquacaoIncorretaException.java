package com.calculadora.util.excessoes;
/**
 * Used by the equation class for throwing errors when the user tries to create an equation which 
 * doesn't contain the correct syntax
 * @author Adam Black
 * modified: 14/4/06
 */
public class SintaxeEquacaoIncorretaException extends Exception 
{
	public SintaxeEquacaoIncorretaException(String message)
	{
		super(message);
	}
	
	public SintaxeEquacaoIncorretaException()
	{
		super();
	}	
}
