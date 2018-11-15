import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import myMath.*;

class PolynomTesting
{

	@BeforeAll
	static void setUpBeforeClass() throws Exception
	{
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception
	{
	}

	@BeforeEach
	void setUp() throws Exception
	{
	}

	@AfterEach
	void tearDown() throws Exception
	{
	}

	@Test
	void testPolynom()
	{
		Polynom p = new Polynom();
		assertNotNull(p);
	}

	@Test
	void testPolynomPolynom_able()
	{
		Polynom p1 = new Polynom("x^2 + x - 2");
		Polynom p = new Polynom(p1);
		Iterator<Monom> i = p.iteretor();
		Monom m = i.next();
		assertEquals(m.get_coefficient(),1);
		assertEquals(m.get_power(), 2);
		m = i.next();
		assertEquals(m.get_coefficient(),1);
		assertEquals(m.get_power(), 1);
		m = i.next();
		assertEquals(m.get_coefficient(),-2);
		assertEquals(m.get_power(), 0);
	}

	@Test
	void testPolynomString()
	{
		Polynom p = new Polynom("x^2 + x - 2");
		Iterator<Monom> i = p.iteretor();
		Monom m = i.next();
		assertEquals(m.get_coefficient(),1);
		assertEquals(m.get_power(), 2);
		m = i.next();
		assertEquals(m.get_coefficient(),1);
		assertEquals(m.get_power(), 1);
		m = i.next();
		assertEquals(m.get_coefficient(),-2);
		assertEquals(m.get_power(), 0);
	}

	@Test
	void testAddMonom()
	{
		Polynom p = new Polynom("5x^2");
		p.add(new Monom(2,2));
		Iterator<Monom> i = p.iteretor();
		Monom m = i.next();
		assertEquals(m.get_coefficient(), 7);
		assertEquals(m.get_power(), 2);
	}

	@Test
	void testAddPolynom_able()
	{
		Polynom p = new Polynom("7x^3");
		p.add(new Polynom("x^3"));
		Iterator<Monom> i = p.iteretor();
		Monom m = i.next();
		assertEquals(m.get_coefficient(), 8);
		assertEquals(m.get_power(), 3);
	}

	@Test
	void testSubstract()
	{
		Polynom p = new Polynom("2x - 5");
		Polynom p1 = new Polynom("-5");
		p.substract(p1);
		Iterator<Monom> i = p.iteretor();
		Monom m = i.next();
		assertEquals(2, m.get_coefficient());
		assertEquals(1, m.get_power());
	}

	@Test
	void testMultiply()
	{
		Polynom p = new Polynom("2x^2");
		Polynom p1 = new Polynom("3x^5");
		p.multiply(p1);
		Iterator<Monom> i = p.iteretor();
		Monom m = i.next();
		assertEquals(6, m.get_coefficient());
		assertEquals(7, m.get_power());
		
	}

	@Test
	void testEqualsPolynom_able()
	{
		Polynom p = new Polynom("2x^2");
		Polynom p1 = new Polynom("2x^2");
		assertTrue(p.equals(p1));
	}

	@Test
	void testIsZero()
	{
		Polynom p = new Polynom("0");
		assertTrue(p.isZero());
		p.add(new Monom("x^2"));
		assertFalse(p.isZero());
	}

	@Test
	void testRoot()
	{
		Polynom p = new Polynom("x^2 - 16");
		assertEquals(p.root(3, 5, 0.000001), 4);
		assertEquals(p.root(-5, -3, 0.000001), -4);
		
	}

	@Test
	void testCopy()
	{
		Polynom p = new Polynom("x^2 + x + 5");
		Polynom_able p1 = p.copy();
		assertTrue(p.equals(p1));
	}

	@Test
	void testDerivative()
	{
		Polynom p = new Polynom("x^3 + x^2");
		Polynom_able p1 = p.derivative();
		Polynom p2 = new Polynom("3x^2 + 2x");
		assertTrue(p1.equals(p2));
	}

	@Test
	void testArea()
	{
		Polynom p = new Polynom("x");
		assertEquals(p.area(0, 3, 0.00001), 4.5, 0.001);
		p = new Polynom("x^2");
		assertEquals(p.area(-3, 3, 0.0000001), 18, 0.001);
	}

	@Test
	void testF()
	{
		Polynom p = new Polynom("x^2 + 5");
		assertEquals(p.f(2), 9);
		assertEquals(p.f(-4), 21);
		assertEquals(p.f(0), 5);
	}

}
