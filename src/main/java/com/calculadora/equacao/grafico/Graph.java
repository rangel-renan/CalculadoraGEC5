package com.calculadora.equacao.grafico;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.JPanel;

import com.calculadora.util.equacao.Equacao;
import com.calculadora.util.equacao.MathsFunction;

/**
 * Graph class represented as a JPanel. Can take multiple input equations and plot them graphically
 * with an axis, and numbering.
 * @author Adam Black
 * modified: 24/04/06
 */
public class Graph extends JPanel
{
	private double minX, maxX, freq, minY, maxY;
	private ArrayList<double[]> function = new ArrayList<double[]>(20);
	
	private final int X_NUMBERING_SEPERATION = 70;
	private final int Y_NUMBERING_SEPERATION = 30;
	private final int TICK_LENGTH = 2;
	private final int X_OFFSET = 50;
	private final int Y_OFFSET = 30;
	
	public Graph(double lowX, double highX, double frequency, Equacao eq) throws GraphArgumentsException
	{
		//Call the base constructor to verify and store arguments
		this(lowX, highX, frequency);
		addEquation(eq);
	}

	/**
	 * Create a Graph instance with equations to plot 
	 * @param lowX minimum X-value to plot over
	 * @param highX maximum X-value to plot over
	 * @param frequency sample size between X-plots
	 * @param eq array of equations
	 * @throws GraphArgumentsException
	 */
	public Graph(double lowX, double highX, double frequency, Equacao[] eq) throws GraphArgumentsException
	{
		//Call the base constructor to verify and store arguments
		this(lowX, highX, frequency);
		for (int i = 0; i < eq.length; i++)
			addEquation(eq[i]);
	}
	
	/**
	 * Base constructor for creating a Graph object without any equations
	 * @param lowX minimum X-value to plot over
	 * @param highX maximum X-value to plot over
	 * @param frequency sample size between X-plots
	 * @throws GraphArgumentsException
	 */
	public Graph(double lowX, double highX, double frequency) throws GraphArgumentsException
	{
		freq = frequency;
		minX = minY = lowX;
		maxX = maxY = highX;
		
		if (freq < 0)
			throw new GraphArgumentsException("ERR5: Frequency cannot be negative");
		else if (maxX - minX <= 0)
			throw new GraphArgumentsException("The minimum X value cannot exceed the maximum X value");
		else if (freq > (maxX - minX))
			throw new GraphArgumentsException("The sample frequency is too large");
	}	
	
	/**
	 * Draws graph and axis to screen
	 * @param g Graphics object for drawing to panel
	 */
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		//Used for antialiasing on the lines drawn
	    Graphics2D g2 = (Graphics2D)g;
	    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);		
		
		drawGraph(g2);
		drawAxis(g2);
	}
	
	/**
	 * Draws the axis on the panel
	 * @param g Graphics object used for drawing to
	 */
	private void drawAxis(Graphics g)
	{
		int x0;
		int y0;
		
		if (minX > 0 || maxX < 0)
			//The minimum X-value is greater than 0, or the maximum X-value is less than 0
			x0 = X_OFFSET - 30;
		else
			//Show axis at x = 0
			x0 = translateX(0);
		
		if (minY > 0 || maxY < 0)
			//The minimum Y-value is greater than 0, or the maximum Y-Value is less than 0
			y0 = getGraphHeight() + Y_OFFSET + 10;
		else
			//Show axis at y = 0
			y0 = translateY(0);
		
		//Draw axis line
		g.drawLine(0, y0, getWidth(), y0);
		g.drawLine(x0, 0, x0, getHeight());
		drawNumbering(x0, y0, g);
	}
	
	/**
	 * Plots all the equations to the panel
	 * @param g Graphics object to draw onto
	 */
	private void drawGraph(Graphics g)
	{
		double y1, y2, x1, x2;
		double[] func;
		
		for (int i = 0; i < function.size(); i++)
		{
			//Get all the X-values for a particular function
			func = function.get(i);
			x1 = minX;
			y1 = func[0];
			for (int j = 1; j < func.length; j++)
			{
				x2 = minX + j * freq;
				y2 = func[j];
				
				if (y1 <= maxY && y2 <= maxY && y1 >= minY && y2 >= minY)
					g.drawLine(translateX(x1), translateY(y1), translateX(x2), translateY(y2));
				
				y1 = y2;
				x1 = x2;
			}
		}
	}
	
	/**
	 * Draws the numbering on the axis
	 * @param x0 x location of y-axis
	 * @param y0 y location of x-axis
	 * @param g Graphics object
	 */
	private void drawNumbering(int x0, int y0, Graphics g)
	{
		double numberXSteps = MathsFunction.roundDouble(Math.abs(getStepInterval(maxX-minX, 'x')), 6);
		double numberYSteps = MathsFunction.roundDouble(Math.abs(getStepInterval(maxY-minY, 'y')), 6);
		int startX = (int)( Math.ceil(MathsFunction.roundDouble(minX / numberXSteps, 2)));
		int endX = (int)( Math.floor(MathsFunction.roundDouble(maxX / numberXSteps, 2)));
		int startY = (int)(Math.ceil(MathsFunction.roundDouble(minY / numberYSteps, 2)));
		int endY = (int)(Math.floor(MathsFunction.roundDouble(maxY / numberYSteps, 2)));
		
		plotNumbers(startX, endX, x0, startY, endY, y0, numberXSteps, numberYSteps, g);
	}

	/**
	 * Plot the numbercs on the axis
	 * @param startX minimum X-value
	 * @param endX maximum X-value
	 * @param x0 X location of y-axis
	 * @param startY minimum Y-value
	 * @param endY maximum Y-value
	 * @param y0 Y location of x-axis
	 * @param numberXSteps Major x interval
	 * @param numberYSteps Major y interval
	 * @param g Graphics object
	 */
	private void plotNumbers(int startX, int endX, int x0, int startY, int endY, int y0, double numberXSteps, double numberYSteps, Graphics g)
	{
		int fontHeight = g.getFontMetrics().getHeight();
		for (int i = startX; i <= endX; i++)
		{
			double nextStep = i * numberXSteps;
			String label = String.valueOf(MathsFunction.roundDouble(nextStep,6));
			int xpos = translateX(nextStep);
			//Get the width of the label so it can be centered at the desired X-value
			int labelOffset = (int)(g.getFontMetrics().getStringBounds(label, g).getWidth()) / 2;
			//Draw number on axis with offset
			g.drawString(label, xpos-labelOffset, y0 + fontHeight);
			//Draw tick on axis.
			g.drawLine(xpos, y0 + TICK_LENGTH, xpos, y0 - TICK_LENGTH);
		}
		
		int labelOffset = fontHeight/4;
		for (int i = startY; i <= endY; i++)
		{
			if (i != 0)
			{
				double nextStep = i * numberYSteps;
				String label = String.valueOf(MathsFunction.roundDouble(nextStep,6));
				int ypos = translateY(nextStep);
				//Draw number on axis with offset
				g.drawString(label, x0+5, ypos + labelOffset);
				//Draw tick on axis
				g.drawLine(x0-TICK_LENGTH, ypos, x0 + TICK_LENGTH, ypos);
			}
		}	
	}
	
	/**
	 * This function is part of determining the step interval for labelling the axis scale. It takes any
	 * range value of x or y and translates it to a small value in the range [1,10), by either multiplying or dividing
	 * by 10. This means a simple set of rules can be applied later to determine the most suitable step interval
	 * for a given X or Y range.
	 * @param range value to be normalised
	 * @return value of n such that range * 10^n is greater or equal to 1 and less than 10
	 */
	private int normalisedRange(double range)
	{
		int power = 0;
		
		if (range < 1)
		{ 
			while (range < 1)
			{
				//range less than 1 so nultiply by 10				
				range *= 10;
				power--;
			}
		}
		else if (range >= 10)
		{
			while (range >= 10)
			{
				//range is greater or equal to 10, so divide by 10
				range /= 10;
				power++;
			}
		}
		
		return power;
	}	
	
	/**
	 * Obtains the optimal step interval for numbering and the axis based on the screen size and the numbering
	 * seperation
	 * @param range of values over the axis
	 * @param axis either 'x' or 'y' axis
	 * @return step interval for axis labelling. How regularly to label points
	 */
	private double getStepInterval(double range, char axis)
	{
		int scale10 = normalisedRange(range);
		double normRange = Math.abs(range / Math.pow(10, scale10));
		
		double labelSeperation;
		
		if (axis == 'x')
			labelSeperation = (double)getGraphWidth()/X_NUMBERING_SEPERATION;
		else
			//axis = 'y'
			labelSeperation = (double)getGraphHeight()/Y_NUMBERING_SEPERATION;		
		
		double interval = normRange / labelSeperation;
		
		return getSignificantInterval(interval) * Math.pow(10, scale10);
	}
	
	/**
	 * 
	 * @param interval normalised step interval before standardisation. This determines at what intervals
	 * the axis must be labelled at, this function will standardise the interval into a regular interval value.
	 * For example the true calculated interval may be 0.17, but a better step interval would be 0.2
	 * @param interval true interval
	 * @return standardised step interval
	 */
	private double getSignificantInterval(double interval)
	{
		if (interval < 1)
		{
			if (interval < 0.1)
				interval = 0.1;
			else if (interval < 0.2)
				interval = 0.2;
			else if (interval < 0.25)
				interval = 0.25;
			else if (interval < 0.5)
				interval = 0.5;
			else
				interval = 1;
		}
		else if (interval >= 1)
		{
			if (interval < 2)
				interval = 2;
			else if (interval < 2.5)
				interval = 2.5;
			else if (interval < 5)
				interval = 5;
			else
				interval = 10;
		}
		
		return interval;
	}
	
	/**
	 * Translates from cartesian coordinates to screen pixel coordinates 
	 * @param x x-coordinate to translate to a coordinate on the JPanel 
	 * @return screen coordinate translated from a cartesian x-coordinate
	 */
	private int translateX(double x)
	{
		//Calculate maximum range of x-values
		double range = maxX - minX;
		//Calculate proportion of the graph width to the x-range
		double prop = getGraphWidth()/range;
		//dialate and translate x coordinate
		double xcoord = prop * x - prop * minX + X_OFFSET;
		
		return (int) xcoord;
	}
	
	/**
	 * Translates from cartesian coordinates to screen pixel coordinates 
	 * @param y y-coordinate to translate to a coordinate on the screen 
	 * @return screen coordinate translated from a cartesian y-coordinate
	 */	
	private int translateY(double y)
	{
		//Similar to translateX method
		double range = maxY - minY;
		double prop =  getGraphHeight()/range;
		//This method is inverted in comparison to the translateX version, because the top of the screen
		//is the 0 point, and as you go down the screen vertically the height increases. This is opposite
		//to the cartesian coordinates which increase as you go up the axis.
		double ycoord =  getGraphHeight() - prop * y + prop * minY + Y_OFFSET;

		return (int) ycoord;
	}	
	
	/**
	 * This function solves a mathematical equation for all values of x at intervals of the sampling frequency
	 * @param eq equation to evaluate for all values of y at intervals of the sampling frequency
	 * @return result array of function
	 */
	private double[] evaluateFunction(Equacao eq)
	{
		//Determines how many samples are needed 
		int sampleCount = (int)(Math.floor((maxX-minX)/freq) + 1);
		double[] result = new double[sampleCount];
		
		for (int i = 0; i < sampleCount; i++)
		{
			result[i] = eq.evaluate(minX + i*freq);
			
			//determine minY and maxY values at the same time
			if (result[i] < minY && !Double.isInfinite(result[i]))
				minY = result[i];
			if (result[i] > maxY && !Double.isInfinite(result[i]))
				maxY = result[i];			
		}
		
		//This is only likely to occue for a constant function with doesn't depend on a variable, and has to
		//be dealt with as a special case, or else the Y range won't be valid.
		if (maxY == minY)
		{
			if (maxY > 0)
				minY = 0;
			else if (minY < 0)
				maxY = 0;
		}
		
		return result;
	}
	
	/**
	 * Calculate the max and min Y value from all equations 
	 */
	private void recalculateYRange()
	{
		double[] func;
		
		if (function.size() == 0)
		{
			minY = minX;
			maxY = maxX;
		}
		else
		{
			minY = java.lang.Double.POSITIVE_INFINITY;
			maxY = java.lang.Double.NEGATIVE_INFINITY;
			
			for (int i = 0; i < function.size(); i++)
			{
				func = function.get(i);
				for (int j = 0; j < func.length; j++)
				{
					//determine minY and maxY values at the same time					
					if (func[j] < minY && !Double.isInfinite(func[j]))
						minY = func[j];
					if (func[j] > maxY && !Double.isInfinite(func[j]))
						maxY = func[j];	
				}
			}
		}
		
		//This is only likely to occue for a constant function with doesn't depend on a variable, and has to
		//be dealt with as a special case, or else the Y range won't be valid.
		if (maxY == minY)
		{
			if (maxY > 0)
				minY = 0;
			else if (minY < 0)
				maxY = 0;
		}
	}
	
	private int getGraphWidth()
	{
		return getWidth() - 2 * X_OFFSET;
	}
	
	private int getGraphHeight()
	{
		return getHeight() - 2 * Y_OFFSET;
	}
	
	/**
	 * This function adds an equation to the function ArrayList for plotting
	 * @param eq equation to add
	 */
	public void addEquation(Equacao eq)
	{
		if (function.size() == 0)
		{
			minY = java.lang.Double.POSITIVE_INFINITY;
			maxY = java.lang.Double.NEGATIVE_INFINITY;			
		}
		function.add(evaluateFunction(eq));
		repaint();
	}
	
	public void removeAllEquations()
	{
		function.clear();
		recalculateYRange();
		repaint();
	}
	
	public void removeEquation(int index)
	{
		function.remove(index);
		recalculateYRange();
		repaint();
	}
	
	
}
