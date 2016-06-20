package com.calculadora.equaca.derivada;

import java.io.*;
import java.text.*;
public class Derivative_Calculator
{
    public static void main (String args[])
    {
	try
	{
	    BufferedReader br = new BufferedReader (new InputStreamReader (System.in));
	    DecimalFormat df = new DecimalFormat ("#.####");
	    System.out.print ("Enter the highest degree of the polynomial: ");
	    int a = Integer.parseInt (br.readLine ());
	    double coefficient[] = new double [a + 1];
	    double dxdy[] = new double [a];
	    for (int i = 0 ; i <= a ; i++)
	    {
		System.out.print ("Enter number: ");
		coefficient [i] = Double.parseDouble (br.readLine ());
	    }
	    int ce = a;
	    for (int j = 0 ; j < a ; j++)
	    {
		dxdy [j] = coefficient [j] * ce;
		ce--;
	    }
	    System.out.print ("Derivative of Function: ");
	    if (a != 0)
	    {
		for (int k = 0 ; k < a ; k++)
		{
		    System.out.print (df.format (dxdy [k]) + " ");
		}
	    }
	    else
	    {
		System.out.print (0);
	    }
	}
	catch (Exception e)
	{
	    System.exit (0);
	}
    }
}
