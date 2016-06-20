package com.calculadora.equaca.derivada;

abstract class Expr {
	public abstract Expr d(String v);

	public Expr plus(Expr e2) {
		return new Plus(this, e2);
	}

	public Expr times(Expr e2) {
		return new Times(this, e2);
	}
}

class Int extends Expr {
	private final int n;

	public Int(int m) {
		n = m;
	}

	public Expr d(String v) {
		return new Int(0);
	}

	public String toString() {
		return Integer.toString(n);
	}
}

class Var extends Expr {
	private final String var;

	public Var(String v) {
		var = v;
	}

	public Expr d(String v) {
		return new Int(var.equals(v) ? 1 : 0);
	}

	public String toString() {
		return var;
	}
}

class Plus extends Expr {
	private final Expr e1, e2;

	public Plus(Expr a, Expr b) {
		e1 = a;
		e2 = b;
	}

	public Expr d(String v) {
		return new Plus(e1.d(v), e2.d(v));
	}

	public String toString() {
		return "(" + e1 + " + " + e2 + ")";
	}
}

class Times extends Expr {
	private final Expr e1, e2;

	public Times(Expr a, Expr b) {
		e1 = a;
		e2 = b;
	}

	public Expr d(String v) {
		return new Plus(new Times(e1, e2.d(v)), new Times(e1.d(v), e2));
	}

	public String toString() {
		return "(" + e1 + " * " + e2 + ")";
	}
}

public class Derivative {
	public static void main(String[] args) {
		Expr x = new Var("x"), a = new Var("a"), b = new Var("b"), c = new Var("c");
		Expr e = a.times(x).times(x).plus(b.times(x)).plus(c);
		System.out.println("d(" + e + ", x) = " + e.d("x"));
	}
}