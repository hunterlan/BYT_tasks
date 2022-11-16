package org.money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class CurrencyTest {
	Currency SEK, DKK, NOK, EUR;
	
	@Before
	public void setUp() throws Exception {
		/* Setup currencies with exchange rates */
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
	}

	@Test
	public void testGetName() {
		String expected = "SEK";
		String actual = SEK.getName();
		assertEquals(expected, actual);
	}
	
	@Test
	public void testGetRate() {
		double expected = 0.20;
		double actual = DKK.getRate();
		assertEquals(expected, actual, 0);
	}
	
	@Test
	public void testSetRate() {
		fail("Write test case here");
	}
	
	@Test
	public void testGlobalValue() {
		fail("Write test case here");
	}
	
	@Test
	public void testValueInThisCurrency() {
		fail("Write test case here");
	}

}
