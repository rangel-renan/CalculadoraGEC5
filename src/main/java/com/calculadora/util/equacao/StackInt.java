package com.calculadora.util.equacao;
/**
 * Generic Stack data structure interface
 * @author Elliot B. Koffman, Data structures and ...... book
 * modified: 10/04/06 
 */
public interface StackInt<E> 
{
	E push(E obj);
	E peek();
	E pop();
	boolean empty();
}
