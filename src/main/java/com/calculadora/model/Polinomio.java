package com.calculadora.model;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.calculadora.util.excessoes.PolinomioFormatoInvalidoException;

public class Polinomio {
	private int grau;
	private List<Integer> coeficientes;
	
    public Polinomio() { 
    	coeficientes = new ArrayList<Integer>(1);
    	coeficientes.add(0,0); 
    }

    public Polinomio(int[] _coeficientes) {
    	coeficientes = new ArrayList<Integer>(_coeficientes.length);
    	grau = _coeficientes.length - 1;
    	
		for (int cont = 0 ; cont < _coeficientes.length; cont++) 
			coeficientes.add(_coeficientes[cont]); 	
    }
    
    public Polinomio(double[] _coeficientes) {
    	coeficientes = new ArrayList<Integer>(_coeficientes.length);
    	grau = _coeficientes.length - 1;
    	
		for (int cont = 0 ; cont < _coeficientes.length; cont++) 
			coeficientes.add((int) _coeficientes[cont]); 
    }

    public Polinomio(String polinomio) throws PolinomioFormatoInvalidoException {
    	coeficientes = new ArrayList<Integer>(1);
    	parse(polinomio);
    }
    
    public void setPolinomio(String polinomio) throws PolinomioFormatoInvalidoException {
    	parse(polinomio);
    }
    
    public int getGrau() { 
    	return grau; 
    }

    public List<Integer> getCoeficientes() {
		return coeficientes;
	}
    
    public double[] parseToDouble() {
    	double[] confDouble = new double[coeficientes.size()];
    	
    	for(int cont = 0; cont < coeficientes.size(); cont++) 
            confDouble[cont] = coeficientes.get(cont);
        
        return confDouble;
    } 
    
	private void parse(String polinomio) throws PolinomioFormatoInvalidoException {
    	Pattern grauZeroConstantPattern = Pattern.compile("^-?\\d+$");
		Matcher grauZeroConstantMatcher = grauZeroConstantPattern.matcher(polinomio);
		
		if (grauZeroConstantMatcher.matches()) {
			coeficientes.add(0, Integer.parseInt(polinomio)); 
		    grau = coeficientes.size() - 1;
		    return; 
		}
	
		// grau 1
	
		Pattern grauUmPattern = Pattern.compile("^(-?)(\\d*)x( ([+-]) (\\d+))?$");
		Matcher grauUmMatcher = grauUmPattern.matcher(polinomio);
	
		if (grauUmMatcher.matches()) {
		    int xCoeff = 1;
		    int constantTerm = 0;
	
		    String xCoeffSign = grauUmMatcher.group(1);
		    String xCoeffString = grauUmMatcher.group(2);
		    String constantTermSign = grauUmMatcher.group(4);
		    String constantTermString = grauUmMatcher.group(5);
	
		    if (xCoeffString != null && !xCoeffString.equals("")) 
		    	xCoeff = Integer.parseInt(xCoeffString);
		    if (xCoeffSign != null && xCoeffSign.equals("-"))
		    	xCoeff *= -1;
		    if (constantTermString != null && !constantTermString.equals(""))
		    	constantTerm = Integer.parseInt(constantTermString);
		    if (constantTermSign != null && constantTermSign.equals("-"))
		    	constantTerm *= -1;
		    
		    coeficientes.add(0,constantTerm); 
		    coeficientes.add(1,xCoeff); 
		    grau = coeficientes.size() - 1;
		    return;
		}

		// grau 2 ou maior
	
		Pattern grauDoisOuMaiorPattern  = Pattern.compile("^([-]?)(\\d*)x\\^(\\d+)(( [+-] \\d*x\\^\\d+)*)" 
													    + "( [+-] \\d*x)?( [+-] \\d+)?$"); 
		Matcher grauDoisOuMaiorMatcher = grauDoisOuMaiorPattern.matcher(polinomio);
	
		if (grauDoisOuMaiorMatcher.matches()) {
		    
			int firstCoeficiente = 1;
		    String startSign      = grauDoisOuMaiorMatcher.group(1);
		    String coeffString    = grauDoisOuMaiorMatcher.group(2);
		    String degreeString   = grauDoisOuMaiorMatcher.group(3);
		    String middleXtoTheTerms = grauDoisOuMaiorMatcher.group(4);
		    String optionalXTermPart = grauDoisOuMaiorMatcher.group(6);
		    String optionalConstantTermPart = grauDoisOuMaiorMatcher.group(7);
	
		    if (coeffString != null && !coeffString.equals("")) 
		    	firstCoeficiente = Integer.parseInt(coeffString);
		    if (startSign != null && startSign.equals("-")) 
		    	firstCoeficiente *= -1;
		    
		    int grauAtual = Integer.parseInt(degreeString);
	
		    grau = grauAtual + 1;
		    
		    for(int i = 0; i <= grauAtual; i++)
		    	coeficientes.add(0,0);
	
		    coeficientes.set(grauAtual, firstCoeficiente);
	
		    if (middleXtoTheTerms != null && !middleXtoTheTerms.equals("")) {
				Pattern addlXtoThePowerTermPattern  = Pattern.compile(" ([+-]) (\\d+)(x\\^)(\\d+)");
				Matcher addlXtoThePowerTermMatcher = addlXtoThePowerTermPattern.matcher(middleXtoTheTerms);
		
				while (addlXtoThePowerTermMatcher.find()) {
				    int coeficiente = 1;
				    String sign           = addlXtoThePowerTermMatcher.group(1);
				    String nextCoeffString    = addlXtoThePowerTermMatcher.group(2);
				    String nextDegreeString   = addlXtoThePowerTermMatcher.group(4);
				    
				    if (nextCoeffString != null && !nextCoeffString.equals("")) 
				    	coeficiente = Integer.parseInt(nextCoeffString);
				    if (sign != null && sign.equals("-")) 
				    	coeficiente *= -1;
				    
				    coeficientes.set(Integer.parseInt(nextDegreeString),coeficiente);
				    
				}
		    } 
	    
		    if (optionalXTermPart != null && !optionalXTermPart.equals("")) {
				Pattern optXTermPattern = Pattern.compile("^ ([+-]) (\\d*)x$");
				Matcher optXTermMatcher = optXTermPattern.matcher(optionalXTermPart);
				optXTermMatcher.find();
			    
				int xCoeff = 1;
				String xCoeffSign = optXTermMatcher.group(1);
				String xCoeffString = optXTermMatcher.group(2);
				
				if (xCoeffString != null && !xCoeffString.equals(""))
				    xCoeff = Integer.parseInt(xCoeffString);
				if (xCoeffSign != null && xCoeffSign.equals("-"))
				    xCoeff *= -1;
				
				coeficientes.set(1, xCoeff); 
		    } 
	
		    if (optionalConstantTermPart != null && !optionalConstantTermPart.equals("")) {
				Pattern constantTermPattern =  Pattern.compile("^ ([+-]) (\\d+)$");
				Matcher constantTermMatcher = constantTermPattern.matcher(optionalConstantTermPart);
				
				constantTermMatcher.find();
		
				int constant = 0;
				String sign = constantTermMatcher.group(1);
				String constantString = constantTermMatcher.group(2);
		
				if (constantString != null && !constantString.equals("")) 
				    constant = Integer.parseInt(constantString);
				if (sign!=null && sign.equals("-"))
				    constant *= -1;
				
				coeficientes.set(0,constant); 
		    } 
		    grau = coeficientes.size() - 1;
		    return;
		} 
		
		throw new PolinomioFormatoInvalidoException("Bad Polynomial String: [" + polinomio + "]");
    }
    
    public boolean equals(Object obj) {
    	if (obj == null) return false;
    	
    	if (obj instanceof Polinomio) {
    		Polinomio polinomio = (Polinomio) obj;
    		if (coeficientes.size() != polinomio.getCoeficientes().size()) return false;
    		
    		for (int cont = 0; cont < coeficientes.size(); cont++) {
    		    if (coeficientes.get(cont) !=  polinomio.getCoeficientes().get(cont))
    		    	return false;
    		}
    	}
    	
		return true;
    }
    
    public String toString() {
		String polinomioFormatado = "";
		DecimalFormat df = new DecimalFormat("#");
	
		if (getGrau() == 0) 
		    return df.format(coeficientes.get(0));	    
		
		double firstCoeficiente = (coeficientes.get(getGrau()));
	
		if (firstCoeficiente < 0) 
		    polinomioFormatado += "-";
		
		if (Math.abs(firstCoeficiente) != 1) 
		    polinomioFormatado += df.format(Math.abs(firstCoeficiente));

		polinomioFormatado += "x";
	
		if (getGrau() > 1) 
		    polinomioFormatado += "^" + getGrau();
		
		for (int cont = getGrau() - 1; cont >= 0; cont--) {
		    if (coeficientes.get(cont) == 0) 
		    	continue;
		    polinomioFormatado += (coeficientes.get(cont) < 0) ? " - " : " + ";
		    if (Math.abs(coeficientes.get(cont)) != 1)
		    	polinomioFormatado += df.format(Math.abs(coeficientes.get(cont)));
		    if (cont >= 2)
		    	polinomioFormatado += "x" + "^" + cont;
		    else if (cont == 1)
		    	polinomioFormatado += "x";
		    
		}
		return polinomioFormatado;
    }
    
}
