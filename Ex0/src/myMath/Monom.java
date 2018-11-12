/*
 * auther: sela goldenberg
 * 		   id: 204375455
 */

package myMath;
/**
 * This class represents a simple "Monom" of shape a*x^b, where a is a real number and a is an integer (summed a none negative), 
 * see: https://en.wikipedia.org/wiki/Monomial 
 * The class implements function and support simple operations as: construction, value at x, derivative, add and multiply. 
 * @author Boaz
 *
 */
public class Monom implements function
{
	private double _coefficient;
	private int _power; 
	
	/**
	 * a simple Monom constructor where the firs arg is the coefficient and the second arg is the power
	 * @param a the coefficient
	 * @param b the power (>= 0)
	 */
	
	public Monom(double a, int b)
	{
		this.set_coefficient(a);
		try 
		{
			this.set_power(b);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * recive a string that represnt a monom of the form "ax^b" or "x^b" of "a" where a is the coefficient and b is the power (b >= 0)
	 * @param str a string that represnt a Monom
	 */
	public Monom(String str)
	{
		str = str.replace(" ", "");
		str = str.replace('X', 'x');
		double num_coe;
		int num_power;
		if(str.charAt(0) == 'x')
			num_coe = 1; 
		else
		{
			String coe = str.substring(0, str.indexOf("x"));
			num_coe = Double.parseDouble(coe);
		}
		if(str.indexOf('^') == -1)
			num_power = 0;
		else
		{
			String pow = str.substring(str.indexOf('^') + 1);
			num_power = Integer.parseInt(pow);
		}
		
		this.set_coefficient(num_coe);
		try
		{
			this.set_power(num_power);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * a copy constructor
	 * @param ot the monom to copy from
	 */
	public Monom(Monom ot) 
	{
		this(ot.get_coefficient(), ot.get_power());
	}
	// ***************** add your code below **********************
	
	/**
	 * calculate this Monom - f(x)
	 */
	public double f(double x)
	{
		return (Math.pow(x, this.get_power())) * this.get_coefficient();
	}
	
	/**
	 * a get function
	 * @return return the Monom coefficient
	 */
	public double get_coefficient()
	{
		return this._coefficient;
	}
	
	/**
	 * a get function
	 * @return return the Monom power
	 */
	public int get_power()
	{
		return this._power;
	}
	
	/**
	 * add m1 to this Monom (only if the power of m1 and this are equles!)
	 * @param m1 the monom we add to this monom
	 */
	public void add(Monom m1)
	{
		Monom_Comperator comp = new Monom_Comperator();
		if(comp.compare(this, m1) == 0)
			this.set_coefficient(this._coefficient + m1.get_coefficient());
	}
	
	/**
	 * multiply m1 to this Monom
	 * @param m1 the Monom we multiply with this
	 */
	public void multiply(Monom m1)
	{
		double co = this.get_coefficient() * m1.get_coefficient();
		int po = this.get_power() + m1.get_power();
		this.set_coefficient(co);
		try 
		{
			this.set_power(po);
		}
		catch(Exception e)
		{
			System.out.println(e.getMessage());
		}
	}
	
	/**
	 * a function to calculate the derivative
	 * @return a Monom that is the derivative of this Monom
	 */
	public Monom derivative()
	{
		double co = this.get_coefficient() * this.get_power();
		int po = this.get_power() - 1;
		if(po >= 0)
			return new Monom(co,po);
		else
			return new Monom(0,0);
	}
	//****************** Private Methods and Data *****************
	
	/**
	 * a set function
	 * @param a the value to put in the coefficient
	 */
	private void set_coefficient(double a)
	{
		this._coefficient = a;
	}
	
	/**
	 * a set function
	 * @param p the value to put in the power
	 * @throws Exception  throw exception if p < 0
	 */
	private void set_power(int p) throws Exception 
	{
		Exception e = new Exception("power must be positive");
		if(p < 0)
			throw e;
		else
			this._power = p;
	}
	
	/**
	 * return a string representation of this Monom in the shape of "ax^b" 
	 */
	@Override
	public String toString()
	{
		if(this.get_power() == 0)
			return this.get_coefficient() + "";
		return this.get_coefficient() + "x^" + this.get_power();
	}

}
