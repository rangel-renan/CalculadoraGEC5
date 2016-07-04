package com.calculadora.util.equacao;
/**
 * Provides all the mathematics functions necessary for the Equation class and a rounding function
 * @author Adam Black
 * modified: 22/04/06
 */
public class MathsFunction
{
	public static double add(double x, double y)
	{
		return (x + y);
	}
	
	public static double sub(double x, double y)
	{
		return (x - y);
	}
	
	public static double mod(double x, double y)
	{
		return (x % y);
	}
	
	public static double multiply(double x, double y)
	{
		return (x * y);
	}
	
	public static double div(double x, double y)
	{
		return (x / y);
	}
	
	public static double pow(double x, double y)
	{
		return (Math.pow(x, y));
	}
	
	public static double sin(double x)
	{
		return (Math.sin(x));
	}
	
	public static double cos(double x)
	{
		return (Math.cos(x));
	}

	public static double tan(double x)
	{
		return (Math.tan(x));
	}
	
	public static double asin(double x)
	{
		return (Math.asin(x));
	}
	
	public static double acos(double x)
	{
		return (Math.acos(x));
	}
	
	public static double atan(double x)
	{
		return (Math.atan(x));
	}
	
	public static double exp(double x)
	{
		return (Math.exp(x));
	}
	
	public static double sqrt(double x)
	{
		return (Math.sqrt(x));
	}
	
	public static double log(double x)
	{
		return (Math.log(x));
	}
	
	public static double neg(double x)
	{
		return (-x);
	}
	
	public static double roundDouble(double x, int places)
	{
		double shift = Math.pow(10,places);
		//Round x to 2 decimal places
		return (Math.floor(x * shift + 0.5)/shift);	
	}
}
