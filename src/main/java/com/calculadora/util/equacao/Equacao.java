package com.calculadora.util.equacao;
import java.lang.reflect.Method;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.calculadora.util.excessoes.EquationVariableException;
import com.calculadora.util.excessoes.SintaxeEquacaoIncorretaException;

/**
 * @author Adam Black
 */

@SuppressWarnings({"unused", "rawtypes"})
public class Equacao
{
	private final String[] OPERADORES = {"+", "-", "*", "/", "^", "~", "%", "sin", "cos", "tan", "asin", "acos", "atan", "log", "exp", "sqrt", "(", ")"};
	private final String[] NOME_OPERADORES = {"add", "sub", "multiply", "div", "pow", "neg", "mod", "sin", "cos", "tan", "asin", "acos", "atan", "log", "exp", "sqrt"};
	
	private final int[] PRECEDENCIA = {1,1,2,2,3,4,2,4,4,4,4,4,4,4,4,4,-1,-1};
	private LinkedStack<String> operatorStack = new LinkedStack<String>();
	private StringBuilder funcaoPosFormatada = new StringBuilder();
	private String[] funcaoPosFormatadaArray;
	private String funcaoPreFormatada;
	
	private boolean operadorEmProcesso = true;
	private char varChar;
	
	/**
	 * O Construtor irá ler a equação e validá-la, passando para 'funcaoPosFormatada'.
	 * @param eq The postfix form of a mathematical equation
	 * @throws SintaxeEquacaoIncorretaException
	 */
	public Equacao(String eq) throws SintaxeEquacaoIncorretaException
	{
		StringBuilder parsedEqn = new StringBuilder();
		
		//The matcher pattern will match all parts of the equation for splitting. 
		Matcher matcher = (Pattern.compile("\\.\\d+|\\d+(?:\\.\\d+)?|[+-/*()%^]|\\w{3,4}|[a-zA-Z]")).matcher(eq);		
		funcaoPreFormatada = removerEspacamentos(eq);
		
		while (matcher.find())
			parsedEqn.append(matcher.group() + ' ');
		
		//split equation components into array
		String[] equation = parsedEqn.toString().split(" ");
		
		//Error checking for valid equation
		if (!(isValidEquation(equation) && removerEspacamentos(parsedEqn.toString()).equals(funcaoPreFormatada)))
			throw new SintaxeEquacaoIncorretaException();
		else if ((varChar = findVariable(equation))=='1')
			//The equation may have more than one variable and hence is invalid.
			throw new SintaxeEquacaoIncorretaException();
		
		converToPostfix(equation);
		funcaoPosFormatadaArray = funcaoPosFormatada.toString().split(" ");
	}
	
	private Equacao() {}
	
	public char getVariable()
	{
		return varChar;
	}
	
	/**
	 * Evaluates the mathematical expression and returns a numerical answer given there are no variables
	 * in the equation
	 * precondition: There are no variables in the equation.
	 * @return numerical answer to the equation stored, rounded to 4 digits
	 * @throws EquationVariableException
	 */
	public double evaluate() throws EquationVariableException
	{
		LinkedStack<Double> resultStack = new LinkedStack<Double>();
		//There is no variable present in the equation
		if (varChar == '0')
		{
			for (int i = 0; i < funcaoPosFormatadaArray.length; i++)
			{
				String tmp = funcaoPosFormatadaArray[i];
				if (isOperand(tmp))
					resultStack.push(Double.parseDouble(tmp));
				else if (isBinaryOperator(tmp))
				{
					double[] arg = {resultStack.pop(), resultStack.pop()};
					resultStack.push(evaluateExpression(arg[1], arg[0], tmp));
				}
				else
					//The operator is unary
					resultStack.push(evaluateExpression(resultStack.pop(), tmp));
			}
		}
		else
			throw new EquationVariableException();
		
		return (MathsFunction.roundDouble(resultStack.pop(), 4));
	}
	
	/**
	 * Evaluates a mathematical expression where a variable is present. If there are no variables present
	 * in the equation this method will work, however the public double evaluate() method is more suitable
	 * @param variableValue substitute this value for the variable in the equation
	 * @return numerical answer to the number stored, rounded to 4 digits
	 */
	public double evaluate(double variableValue)
	{
		LinkedStack<Double> resultStack = new LinkedStack<Double>();
		for (int i = 0; i < funcaoPosFormatadaArray.length; i++)
		{
			String tmp = funcaoPosFormatadaArray[i];
			if (isOperand(tmp))
				if (tmp.charAt(0) == varChar)
					//substitute parameter for variable
					resultStack.push(variableValue);
				else
					resultStack.push(Double.parseDouble(tmp));
			else if (isBinaryOperator(tmp))
			{
				double[] arg = {resultStack.pop(), resultStack.pop()};
				resultStack.push(evaluateExpression(arg[1], arg[0], tmp));
			}
			else
				//unary operator
				resultStack.push(evaluateExpression(resultStack.pop(), tmp));
		}
		
		return (MathsFunction.roundDouble(resultStack.pop(), 4));
	}	
	
	/**
	 * Evaluates two operands with a binary operator and returns the result
	 * @param arg0 first operand
	 * @param arg1 second operand
	 * @param op binary operator such as +,-,*,/,%,^
	 * @return result of the operation
	 */
	private double evaluateExpression(double arg0, double arg1, String op)
	{
		double result = 0;

		try
		{
			//Create an instance of the MathsFunction class and then call a suitable method based on the operator
			MathsFunction math = new MathsFunction();
			Class paramType[] = {Double.TYPE, Double.TYPE};
			Method callingMethod = MathsFunction.class.getMethod(operatorToFunction(op), paramType);
			Object argList[] = {arg0, arg1};
			result = (Double)callingMethod.invoke(math, argList);
		}
		catch(Exception e){}
		
		return result;
	}
	
	/**
	 * Evaluates an operand with a unary operator and returns the result
	 * @param arg0 operand
	 * @param op unary operator such as sin, cos, log, sqrt.
	 * NB: '-' can sometimes apply as both a binary and unary operator depending on the circumstances
	 * @return result of the operation
	 */
	private double evaluateExpression(double arg0, String op)
	{
		double result = 0;

		try
		{
			//Create an instance of the MathsFunction class and then call a suitable method based on the operator
			MathsFunction math = new MathsFunction();			
			Class paramType[] = {Double.TYPE};
			Method callingMethod = MathsFunction.class.getMethod(operatorToFunction(op), paramType);
			Object argList[] = {arg0};
			result = (Double)callingMethod.invoke(math, argList);
		}
		
		catch(Exception e){}
		
		return result;
	}
	
	/**
	 * Converts a binary or unary operator to a function name in the MathsFunction class
	 * @param op binary or unary operator
	 * @return function call which performs the desired operation
	 */
	private String operatorToFunction(String op)
	{
		String fn = null;
		for (int i = OPERADORES.length; --i >= 0 && fn==null ;)
		{
			if (OPERADORES[i].equals(op))
				fn = NOME_OPERADORES[i];
		}
		
		return fn;
	}
	
	/**
	 * Search the equation for candidate variables and return the variable character
	 * @param eq Equation seperated into individual elements
	 * @return variable character
	 * 0 - no variable in equation
	 * 1 - more than 1 variable, treated as an error
	 * 
	 */
	private char findVariable(String[] eq)
	{
		char variable='0';
		char currentVar;
		
		for (int i = eq.length; --i >= 0 && variable != '1'; )
		{
			if (eq[i].matches("[a-zA-Z]"))
			{
				//Found potential variable
				currentVar = eq[i].charAt(0);
				
				if (variable=='0')
					//No variable found previously, so make this the variable
					variable = currentVar;
				else if (variable != currentVar)
					//Variable already found, so there must be mutliple variables
					variable = '1';
			}
		}
		
		return variable;
	}
	
	/**
	 * Removes spaces from anywhere within a string and return the resultant string
	 * @param s string to strip off spaces
	 * @return string without any spaces
	 */
	private String removerEspacamentos(String s)
	{
		char tmp;
		StringBuilder sb = new StringBuilder();
		for (int i=0; i<s.length(); i++)
		{
			tmp = s.charAt(i);
			if (tmp != ' ')
				//not a space character so it can be allowed in the resultant string
				sb.append(tmp);
		}
		
		return sb.toString();
	}
	
	public String getPostfix()
	{
		return funcaoPosFormatada.toString();
	}
	
	public String getPrefix()
	
	{
		return funcaoPreFormatada;
	}
	
	/**
	 * Convert a prefix equation into postfix form
	 * @param equation equation split into elements
	 */
	private void converToPostfix(String[] equation)
	{
		String tmp;
		
		for (int i = 0; i < equation.length;i++)
		{
			tmp = equation[i];
			if (isOperand(tmp))
			{
				//operatorProcess variable is used to track where a minus operator is used in a unary sense
				operadorEmProcesso = false;
				funcaoPosFormatada.append(tmp);
				funcaoPosFormatada.append(' ');
			}
			else if (isOperator(tmp))
			{
				processOperator(tmp);
				//if the operator is a closing brace and the next operator is a - or +, it cannot be unary
				if (!tmp.equals(")"))
					operadorEmProcesso = true;
			}
		}
		while (!operatorStack.empty())
		{
			String o = operatorStack.pop();
			funcaoPosFormatada.append(o + ' ');
		}
	}
	
	private boolean isOperand(String s)
	{
		//Checks if the string matches an operand, this includes a single character variable, or number
		return s.matches("[a-zA-Z]|\\.\\d+|\\d+(?:\\.\\d+)?");
	}
	
	private boolean isOperator(String s)
	{
		//Checks if the string matches a binary or unary operator
		return (s.matches("[+\\-*/^~%()]|sin|cos|tan|asin|acos|atan|log|exp|sqrt"));
	}
	
	private boolean isBinaryOperator(String s)
	{
		//Checks if the string matches a binary operator
		return (s.matches("[+\\-*/^%]"));
	}
	
	private boolean isUnaryOperator(String s)
	{
		//Checks if the string matches a unary operator
		return (s.matches("~|sin|cos|tan|asin|acos|atan|log|exp|sqrt"));
	}
	
	/**
	 * This method is part of the postfix conversion
	 * @param o operator to be processed
	 */
	private void processOperator(String o)
	{
		if (operadorEmProcesso && o.matches("[+//-]"))
		{
			//Last part of equation processed was an operator, so if this operator is + or -, it must be unary
			if (o.equals("-"))
				//Special notation for unary operator to distringuish from the usual - binary operator
				operatorStack.push("~");
		}
		else if (o.equals("(") || isUnaryOperator(o) || operatorStack.empty())
			//Open braces and unary operators are always pushed onto the stack
			operatorStack.push(o);
		else
		{
			//process operator according to the usual precedence rules
			String topOp = operatorStack.peek();
			
			if (precedence(o) > precedence(topOp))
				operatorStack.push(o);
			else
			{
				while (!operatorStack.empty() && precedence(o) <= precedence(topOp))
				{
					if (operatorStack.pop().equals("("))
						break;
					
					funcaoPosFormatada.append(topOp + ' ');
					
					if (!operatorStack.empty())
						topOp = operatorStack.peek();
				}
				if (!o.equals(")"))
					operatorStack.push(o);				
			}
		}
	}
	
	/**
	 * Returns the numerical precedence of an operator
	 * @param op operator
	 * @return precedence value od operator
	 */
	private int precedence(String op)
	{
		int prec = 0;
		for (int i = OPERADORES.length; --i >= 0 ;)
		{
			if (OPERADORES[i].equals(op))
			{
				prec = PRECEDENCIA[i];
			}
		}
		return prec;
	}
	
	/**
	 * Step through the equation 2 elements at a time and check the sequence of the equation to determine
	 * whether the syntax is correct. Brackets are also counted to check for balance
	 * @param eq equation broken up into individual elements
	 * @return validity of equation
	 */
	private boolean isValidEquation(String[] eq)
	{
		boolean isValid = true;
		int bracketCount = 0;
		String lastPart="";
		String nextPart;		
		
		if (eq.length == 0)
			isValid = false;
		
		for (int i = 0; i < eq.length && isValid==true; i++)
		{
			nextPart = eq[i];
			isValid = checkEquationSyntax(lastPart, nextPart);
			if (nextPart.equals("("))
				bracketCount++;
			else if (nextPart.equals(")"))
				bracketCount--;
			
			lastPart = nextPart;
		}
	
		if (bracketCount != 0 || !isOperand(lastPart) && !lastPart.equals(")"))
			isValid = false;
			
		return isValid;
	}
	
	/**
	 * Checks if two elements in the equation are valid
	 * @param last first equation element
	 * @param next second equation element
	 * @return whether the syntax is valid
	 */
	private boolean checkEquationSyntax(String last, String next)
	{
		boolean isValid = true;
		
		if ((last.equals("") || last.equals("(")) && !next.matches("[(+\\-]") && !isOperand(next) && !isUnaryOperator(next))
			//First element is either nothing or an opening brace and the second operand isn't a unary operator or operand
			isValid = false;
		else if ((isOperand(last) || last.equals(")")) && !isBinaryOperator(next) && !next.equals(")"))
			//First element is an operand or close brace and the second element isn't a binary operator or closing brace
			isValid = false;
		else if (isUnaryOperator(last) && !next.equals("("))
			//First element is a unary operator and the second element isn't an opening brace
			isValid = false;
		else if (isBinaryOperator(last) && !next.matches("[(+\\-]") && !isOperand(next) && !isUnaryOperator(next))
			//First element is a binary operator and the second element isn't a unary operator or operand
			isValid = false;
		
		return isValid;
	}
}