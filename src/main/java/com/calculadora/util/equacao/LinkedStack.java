package com.calculadora.util.equacao;
import java.util.EmptyStackException;

/**
 * Generic Stack data structure
 * @author Elliot B. Koffman, Data structures and ...... book
 * modified: 10/04/06 
 */
public class LinkedStack<E> implements StackInt<E> 
{	
	private Node<E> topOfStackRef = null;
	
	/**
	 * Generic Node inner class
	 * @author Elliot B. Koffman, Data structures and ...... book
	 * modified: 10/04/06 
	 */
	private static class Node<E> 
	{
		private E data;
		private Node next;
		
		private Node(E dataItem)
		{
			data = dataItem;
			next = null;
		}
		private Node(E dataItem, Node<E> nodeRef)
		{
			data = dataItem;
			next=nodeRef;
		}
	}
	
	/**
	 * Push an object onto the stack
	 * O(1) complexity
	 * @param obj object to push onto stack
	 * @return object pushed onto stack
	 */
	public E push(E obj)
	{
		//Create a new node which references the previous node. This new node then becomes the topOfStackRef
		topOfStackRef = new Node<E>(obj, topOfStackRef);
		return obj;
	}
	
	/**
	 * Pop an object off the stack and return the object
	 * O(1) complexity
	 * @return object from top of stack
	 */
	public E pop()
	{
		if (empty())
		{
			throw new EmptyStackException();
		}
		else
		{
			//Get the topOfStack data
			E result = topOfStackRef.data;
			//remove the references to the topOfStack object
			topOfStackRef = topOfStackRef.next;
			return result;
		}
	}
	
	/**
	 * Return object at top of stack without removing it
	 * O(1) complexity
	 * @return object at top of stack
	 */
	public E peek()
	{
		if (empty())
		{
			throw new EmptyStackException();
		}
		else
		{
			return topOfStackRef.data;
		}
	}
	
	/**
	 * Check for empty stack
	 * O(1) complexity
	 */
	public boolean empty()
	{
		return topOfStackRef == null;
	}
}
