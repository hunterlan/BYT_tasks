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
		NOK = new Currency("NOK", 0.0);
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
		double valueToSet = 1.1;
		double previousValue = NOK.getRate();
		NOK.setRate(valueToSet);
		double currentValue = NOK.getRate();
		assertTrue(currentValue != previousValue && valueToSet == currentValue);
	}
	
	@Test
	public void testGlobalValue() {
		double expectedValue = 150;
		double currentValue = EUR.universalValue(100);
		assertEquals(expectedValue, currentValue, 0);
	}
	
	@Test
	public void testValueInThisCurrency() {
		double expectedValue = 10;
		double realValue = EUR.valueInThisCurrency(100d, SEK);
		assertEquals(expectedValue, realValue, 0);
	}

}
