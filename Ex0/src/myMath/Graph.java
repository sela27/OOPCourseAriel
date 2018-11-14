package myMath;


import de.erichseifert.gral.data.DataSource;
import de.erichseifert.gral.data.DataTable;
import de.erichseifert.gral.plots.XYPlot;
import de.erichseifert.gral.plots.lines.DefaultLineRenderer2D;
import de.erichseifert.gral.plots.lines.LineRenderer;
import de.erichseifert.gral.ui.InteractivePanel;

import java.awt.Color;
import java.util.LinkedList;

import javax.swing.JFrame;




/**
 * 
 * @author sela goldenberg
 * the class paint the polynom 0.2x^4-1.5x^3+3.0x^2-x-5 , its max and min point
 * and calculate the area above and under the x axis
 */
public class Graph extends JFrame
{
	/**
	 * paint the polynom p and it max and min points from x0 to x1
	 * @param p the polynom to paint
	 * @param x0 the starting point (x0 < x1)
	 * @param x1 the ending point
	 * @param eps the epsilon value used for calculation (should be very small)
	 */
	public Graph(Polynom p, double x0, double x1, double eps) 
	{
		setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
		
		//data for all the point
		DataTable data = new DataTable(Double.class, Double.class);
		//data for only the max and min point
		DataTable MaxAndMindata = new DataTable(Double.class, Double.class);
		
		double before,after;
		
		for (double x = x0; x <= x1; x+=eps) 
		{
		    double y = p.f(x);
		    data.add(x,y);
		    before = p.f(x-eps);
		    after = p.f(x+eps);
		    if((before < y && after < y) || (before > y) && (after > y))
		    	MaxAndMindata.add(x,y);
		}
		XYPlot plot = new XYPlot(data,MaxAndMindata);
		getContentPane().add(new InteractivePanel(plot));
		
		LineRenderer lines = new DefaultLineRenderer2D();
		plot.setLineRenderers(data, lines);
		
		Color color = new Color(0.0f, 0.3f, 1.0f);
		Color MaxAndMinColor = new Color(1.0f, 0.0f, 0.0f);
		plot.getPointRenderers(data).get(0).setColor(color);
		plot.getLineRenderers(data).get(0).setColor(color);
		//paint the max and min point
		plot.getPointRenderers(MaxAndMindata).get(0).setColor(MaxAndMinColor);
        
	}
	/**
	 * paint the polynom 0.2x^4 - 1.5x^3 + 3.0x^2 - x - 5
	 * and calculate its area above and under the x axis
	 * @param args
	 */
	public static void main(String[] args)
	{
		Polynom p = new Polynom("0.2x^4 - 1.5x^3 + 3.0x^2 - x - 5");
		System.out.println("the polynom area above the x axis: " + p.area(-2, 6, 0.001));
		
		Graph frame = new Graph(p,-2,6,0.001);
		frame.setVisible(true);
		p.multiply(new Polynom("-1"));
		System.out.println("the polynom area under the x axis: " + p.area(-2, 6, 0.01));
		
		
	}

}
