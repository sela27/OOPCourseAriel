/*
 * auther: sela goldenberg
 * 		   id: 204375455
 */
package myMath;

public class Test
{

	public static void main(String[] args)
	{
		
		
		Monom m1 = new Monom("5x^4");
		System.out.println("m1: " + m1);
		System.out.println("m1(2): " + m1.f(2));
		m1.add(new Monom("2x^4"));
		System.out.println("adding 2x^4: " + m1);
		m1.multiply(new Monom("x"));
		System.out.println("multiply by x: " + m1);
		System.out.println("m1 derivative: " + m1.derivative());
		
		
		Polynom p1 = new Polynom("5x^2 - 6x^3");
		System.out.println("p1: " + p1);
		System.out.println("p1(0.7): " + p1.f(0.7));
		System.out.println("p1(0.9): " + p1.f(0.9));
		System.out.println("root of p1 between 0.7 and 0.9: " + p1.root(0.7, 0.9, 0.0000001));
		p1.add(m1);
		System.out.println("adding m1: " + p1);
		p1.add(p1);
		System.out.println("adding p1 to himself: " + p1);
		p1.substract(new Polynom("12x^3"));
		System.out.println("subtracting 12x^3 from p1: " + p1);
		p1.multiply(new Polynom("x"));
		System.out.println("multiply p1 with x: " + p1);
		System.out.println("p1 derivative:" + p1.derivative());
		System.out.println("p1 area between 0 and 2: " + p1.area(0, 2, 0.00001));
		System.out.println("p1 is zero?: "  + p1.isZero());
		System.out.println("p1 is equal to x^2?: " + p1.equals(new Polynom("x^2")));
	}

}
