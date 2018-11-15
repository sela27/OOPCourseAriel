/*
 * auther: sela goldenberg
 * 		   id: 204375455
 */

package myMath;

import java.util.ArrayList;
import java.util.Iterator;


import myMath.Monom;
/**
 * This class represents a Polynom with add, multiply functionality, it also should support the following:
 * 1. Riemann's Integral: https://en.wikipedia.org/wiki/Riemann_integral
 * 2. Finding a numerical value between two values (currently support root only f(x)=0).
 * 3. Derivative
 * 
 * @author Boaz
 *
 */
public class Polynom implements Polynom_able
{
	private ArrayList<Monom> Pol;
	
	/**
	 * a simple polynom constractor represent p(x) = 0
	 */
	public Polynom()
	{
		Pol = new ArrayList<Monom>();
	}
	
	/**
	 * a copy constractor the create of this as a copy of the argumant
	 * @param copy the polynom to copy from
	 */
	public Polynom(Polynom_able copy)
	{
		Pol = new ArrayList<Monom>();
		Iterator<Monom> Icopy = copy.iteretor();
		while(Icopy.hasNext())
		{
			this.add(Icopy.next());
		}
	}
	
	/**
	 * a constractor that create a polynom from str
	 * @param str a string representation of a polynom of the shape "ax^b + cx^d + ..."
	 */
	public Polynom(String str)
	{
		str = str.replace(" ", "");
		str = str.replace("-", "+-");
		String[] pol_array = str.split("\\+");
		Pol = new ArrayList<Monom>();
		for(int i = 0; i < pol_array.length; i++)
		{
			if(!pol_array[i].isEmpty())
			this.add(new Monom(pol_array[i]));
		}
	}
	
	/**
	 * add m1 to this Monom. if m1 power exist in this polynom the function add the coefficients
	 */
	public void add(Monom m1)
	{
		if(m1.get_coefficient() == 0)
			return;
		boolean PowerExist = false;
		Iterator<Monom> Ipol = this.iteretor();
		Monom next;
		Monom_Comperator comp = new Monom_Comperator();
		int compAnswer, place = 0;
		while(Ipol.hasNext() && !PowerExist)
		{
			next = Ipol.next();
			compAnswer = comp.compare(next, m1);
			if(compAnswer == 0)
			{
				next.add(m1);
				PowerExist = true;
			}
			else if(compAnswer == -1)
			{
				Pol.add(place, m1);
				PowerExist = true;
			}
			else
			{
				place++;
			}
		}
		if(!PowerExist)
			Pol.add(place,m1);
	}
	
	/**
	 * add p1 to this polynom. break p1 to Monoms and send each one to add(Monom m1)
	 */
	public void add(Polynom_able p1)
	{
		if(p1 != null)
		{
			Iterator<Monom> Ip1 = p1.iteretor();
			while(Ip1.hasNext())
				this.add(Ip1.next());
		}
	}
	/**
	 * substract p1 from this polynom. break p1 to Monoms and multyply each coefficient with -1 and use add(Monom m1)
	 */
	public void substract(Polynom_able p1)
	{
		if(p1 != null)
		{
			Iterator<Monom> Ip1 = p1.iteretor();
			Monom toSubstract;
			int power;
			double coefficient;
			while(Ip1.hasNext())
			{
				toSubstract = Ip1.next();
				power = toSubstract.get_power();
				coefficient = toSubstract.get_coefficient();
				toSubstract = new Monom(-1 * coefficient, power);
				this.add(toSubstract);
				
			}
		}
	}
	
	/**
	 * multiply p1 with this. break this and p1 to Monoms and use the multiply(Monom m1) in Monom to multiply every couple of Monoms
	 */
	public void multiply(Polynom_able p1)
	{
		if(p1 != null)
		{
			Iterator<Monom> Ithis = this.iteretor();
			Iterator<Monom> Ip1 = p1.iteretor();
			Monom p1Next, thisNext;
			//run on every monom in this and multiply it with every monom in p1
			while(Ip1.hasNext())
			{
				p1Next = Ip1.next();
				while(Ithis.hasNext())
				{
					thisNext = Ithis.next();
					thisNext.multiply(p1Next);
				}
				Ithis = this.iteretor();
			}
		}
	}
	
	/**
	 * cheack if p1 is mathematicaly equal to this polynom 
	 */
	@Override
	public boolean equals(Polynom_able p1)
	{
		if(p1 != null)
		{
			Iterator<Monom> Ip1 = p1.iteretor();
			Iterator<Monom> Ithis = this.iteretor();
			Monom p1Next = null , thisNext = null;
			Monom_Comperator comp = new Monom_Comperator();
			while(Ip1.hasNext() && Ithis.hasNext())
			{
				p1Next = Ip1.next();
				thisNext = Ithis.next();
				if(comp.compare(p1Next, thisNext) != 0)
					return false;
				else if(comp.compare_coefficient(p1Next, thisNext) !=0)
					return false;
			}
			if((p1Next == null && thisNext != null) || (p1Next != null && thisNext == null))
				return false;
			else
				return true;
		}
		
		else
		{
			return false;
		}
	}
	
	/**
	 * check if this Polynom is p(x) = 0, by cheaking if Monoms List is empty
	 */
	public boolean isZero()
	{
		return Pol.isEmpty();
	}
	
	/**
	 * calculate the root between two number using the bisection method. assuming f(x0) * f(x1) < 0
	 */
	public double root(double x0, double x1, double eps)
	{
		double middle = (x0 + x1) / 2;
		double f_x0, f_x1, f_middle;
		do
		{
			middle = (x0 + x1) / 2;
			f_x0 = this.f(x0);
			f_x1 = this.f(x1);
			f_middle = this.f(middle);
			if(f_x0 * f_middle <= 0)
			{
				x1 = middle;
				
			}
			else if(f_x1 * f_middle <= 0)
			{
				x0 = middle;
				
			}
		}while(Math.abs(f_middle) > eps);
		
		return middle;
	}
	
	/**
	 * return a copy of this polynom
	 */
	public Polynom_able copy()
	{
		Polynom_able cp = new Polynom(this);
		return cp;
	}
	
	/**
	 * return a derivative of this polynom
	 */
	public Polynom_able derivative()
	{
		Polynom_able der = new Polynom();
		Iterator<Monom> Ithis = this.iteretor();
		while(Ithis.hasNext())
		{
			der.add(Ithis.next().derivative());
		}
		
		return der;
	}
	
	/**
	 * claculate the area under the polynom between x0 and x1 using riemann sums
	 */
	public double area(double x0,double x1, double eps)
	{
		double area_sum = 0;
		double f_x0, f_x1 = this.f(x1);
		double rec_sum;
		do
		{
			f_x0 = this.f(x0);
			rec_sum = f_x0 * eps;
			if(rec_sum >= 0)
				area_sum+=rec_sum;
			 x0 += eps;
			
		}while(x0 <= x1);
		
		return area_sum;
	}
	
	/**
	 * calculate to value of this polynom at x
	 */
	public double f(double x)
	{
		double sum = 0;
		Iterator<Monom> Ithis = this.iteretor();
		while(Ithis.hasNext())
		{
			sum += Ithis.next().f(x);
		}
		return sum;
	}
	
	/**
	 * return a Iterator of the arrayList using to represent the polynom
	 */
	public Iterator<Monom> iteretor()
	{
		return Pol.iterator();
	}
	
	/**
	 * return a sting representation of the polynom in the shape of "(ax^b) + (cx^d) + ..."
	 */
	@Override
	public String toString()
	{
		String str = "";
		Iterator<Monom> Ithis = this.iteretor();
		
		if(Ithis.hasNext())
		{
			str += "(" + Ithis.next() + ") ";
		}
		
		while(Ithis.hasNext())
		{
			str += "+" + " (" + Ithis.next() + ") " ;
		}
		
		return str;
		
	}
}
