package com.calculadora.equacao.grafico;
/**
 * Used by the Graph class to throw errors when the user tries to create a Graph object with invalid arguments
 * such as a negative frequency for example.
 * @author Adam Black
 * modified: 19/4/16
 */
public class GraphArgumentsException extends Exception 
{
	public GraphArgumentsException(String message)
	{
		super(message);
	}
	
	public GraphArgumentsException()
	{
		super();
	}	
}
