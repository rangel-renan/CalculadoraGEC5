package com.calculadora.util;
import java.util.EmptyStackException;

@SuppressWarnings({"rawtypes", "unchecked"})
public class LinkedStack<E> implements StackInt<E> {	
	private Node<E> topOfStackRef = null;
	
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
	
	public E push(E obj) {
		//Create a new node which references the previous node. This new node then becomes the topOfStackRef
		topOfStackRef = new Node<E>(obj, topOfStackRef);
		return obj;
	}
	
	
	public E pop() {
		if (empty())
			throw new EmptyStackException();
		else {
			//Get the topOfStack data
			E result = topOfStackRef.data;
			//remove the references to the topOfStack object
			topOfStackRef = topOfStackRef.next;
			return result;
		}
	}
	
	public E peek() {
		if (empty())
			throw new EmptyStackException();
		else
			return topOfStackRef.data;
		
	}
	
	public boolean empty() {
		return topOfStackRef == null;
	}
}
