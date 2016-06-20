package com.calculadora.equacao.grafico;
import java.awt.Color;
import java.awt.Container;

import javax.swing.JFrame;

import com.calculadora.equacao.Equacao;
import com.calculadora.equacao.SintaxeEquacaoIncorretaException;

/**
 * Plots a single equation graphically for a given x range and sample rate.
 * @author Adam Black
 * modified: 24/04/06
 *
 */
public class PlotEq extends JFrame
{	
	/**
	 * Set up a Graph object with the desired equation
	 * @param lowX minimum X-value
	 * @param highX maximum X-value
	 * @param frequency sample frequency
	 * @param eq equation
	 * @throws GraphArgumentsException
	 */
	public PlotEq(double lowX, double highX, double frequency, Equacao eq) throws GraphArgumentsException
	{
		super("PlotEq");
		//Create a Graph object which will plot the function
		Graph graph = new Graph(lowX, highX, frequency, eq);
		graph.setBackground(Color.WHITE);
		Container c = getContentPane();
		setSize(600,400);
		
		c.add(graph);
	}
	
	/**
	 * Seperate parameters, create an Equation object and create a PlotEq object to display graph.
	 * @param args[0] minX
	 * @param args[1] maxX
	 * @params args[2] frequency
	 * @params args[3+n] equation
	 */
	public static void main(String[] args)
	{
		try
		{
//			String [] params = extractParameters(args);
//			double minX = Double.parseDouble(params[0]);
//			double maxX = Double.parseDouble(params[1]);
//			double frequency = Double.parseDouble(params[2]);
			Equacao eq = new Equacao("sin(x^2)");
			   
			PlotEq plotterGUI = new PlotEq(-5, 5, 0.01, eq);
			plotterGUI.setVisible(true);
			plotterGUI.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			
		}
		catch (SintaxeEquacaoIncorretaException e)
		{
			System.out.println("ERR1: The equation is not well-formed");
		}
		catch (NullPointerException e)
		{
			System.out.println("ERR0: Insufficient arguments");
		}
		catch (NumberFormatException e)
		{
			System.out.println("ERR: Invalid arguments");
		}
		catch (GraphArgumentsException e)
		{
			System.out.println(e.getMessage());
		}
		
	}

	/**
	 * Extract the command line arguments into a seperate array for each argument and concatenate the
	 * equation argument.
	 * @param args arguments from command-line. See public static void main(String[] args) comments
	 * @return string array off parameters, with the entire equation in args[3]
	 * null if the number of arguments is less than required
	 */
	private static String[] extractParameters(String[] args)
	{
		StringBuilder eq = new StringBuilder();
		String[] param = new String[4];
		
		if (args.length >= 4)
		{
			for (int i = 3; --i >= 0; )
				param[i] = args[i];
			
			for (int i = 3; i < args.length; i++)
				//concatenate equation
				eq.append(args[i]);
			
			param[3] = eq.toString();
			
			return param;
		}
		else
			return null;
	}
}
