package com.calculadora.util;

public interface StackInt<E>  {
	
	public E push(E obj);
	public E peek();
	public E pop();
	public boolean empty();
}
