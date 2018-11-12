package myMath;

import java.util.Comparator;

public class Monom_Comperator implements Comparator<Monom> 
{
	@Override
	/**
	 * compare two Monoms by the power
	 */
	public int compare(Monom o1, Monom o2)
	{
		if(o1.get_power() > o2.get_power())
			return 1;
		else if(o1.get_power() == o2.get_power())
			return 0;
		else
			return -1;
	}
	
	/**
	 * compare two Monoms by the corfficient
	 * @param o1 the first Monom to compare
	 * @param o2 the second Monom to compare
	 * @return 1 if o1 is bigger then o2, 0 if they are equals, -1 if o2 is bigger then o2
	 */
	public int compare_coefficient(Monom o1, Monom o2)
	{
		if(o1.get_coefficient() > o2.get_coefficient())
			return 1;
		else if(o1.get_coefficient() == o2.get_coefficient())
			return 0;
		else
			return -1;
	}
}