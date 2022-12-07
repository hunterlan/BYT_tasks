package org.money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class MoneyTest {
	Currency SEK, DKK, NOK, EUR;
	Money SEK100, EUR10, SEK200, EUR20, SEK0, EUR0, SEKn100;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.20);
		EUR = new Currency("EUR", 1.5);
		SEK100 = new Money(10000d, SEK);
		EUR10 = new Money(1000d, EUR);
		SEK200 = new Money(20000d, SEK);
		EUR20 = new Money(2000d, EUR);
		SEK0 = new Money(0d, SEK);
		EUR0 = new Money(0d, EUR);
		SEKn100 = new Money(-10000d, SEK);
	}

	@Test
	public void testGetAmount() {
		double expectedAmount = 10000;
		double currentAmount = SEK100.getAmount();
		assertEquals(expectedAmount, currentAmount, 0);
	}

	@Test
	public void testGetCurrency() {
		Currency currentCurrency = EUR10.getCurrency();
		assertEquals(EUR, currentCurrency);
	}

	@Test
	public void testToString() {
		String expected = "20.0 EUR";
		String actual = EUR20.toString();
		assertEquals(expected, actual);
	}

	@Test
	public void testGlobalValue() {
		double expectedGlobalValue = SEK200.getAmount() * SEK200.getCurrency().getRate();
		double actualGlobalValue = SEK200.universalValue();
		assertEquals(expectedGlobalValue, actualGlobalValue, 0);
	}

	@Test
	public void testEqualsMoney() {
		Money compare = new Money(0d, SEK);
		boolean resultCompare = SEK0.equals(compare);
		assertTrue(resultCompare);
	}

	@Test
	public void testAdd() {
		double expected = 1000;
		double real = EUR0.add(EUR10).getAmount();
		assertEquals(expected, real, 0);
	}

	@Test
	public void testSub() {
		double expected = -1000d;
		double real = EUR0.sub(EUR10).getAmount();
		assertEquals(expected, real, 0);
	}

	@Test
	public void testIsZero() {
		assertFalse(SEK200.isZero());
	}

	@Test
	public void testNegate() {
		double expected = -1000d;
		double real = EUR10.negate().getAmount();
		assertEquals(expected, real, 0);
	}

	@Test
	public void testCompareTo() {
		assertFalse(EUR0.equals(EUR10));
	}
}
