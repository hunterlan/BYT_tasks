package org.money;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

public class BankTest {
	Currency SEK, DKK;
	Bank SweBank, Nordea, DanskeBank;

	Account ulrika, bob, bobDKK, gertrud;
	
	@Before
	public void setUp() throws Exception {
		DKK = new Currency("DKK", 0.20);
		SEK = new Currency("SEK", 0.15);
		SweBank = new Bank("SweBank", SEK);
		Nordea = new Bank("Nordea", SEK);
		DanskeBank = new Bank("DanskeBank", DKK);

		ulrika = new Account(SEK);
		bob = new Account(SEK);
		bobDKK = new Account(DKK);
		gertrud = new Account(DKK);

		SweBank.openAccount("Ulrika", ulrika);
		SweBank.openAccount("Bob", bob);
		Nordea.openAccount("Bob", bobDKK);
		DanskeBank.openAccount("Gertrud", gertrud);
	}

	@Test
	public void testGetName() {
		String expectedName = "SweBank";
		String realName = SweBank.getName();
		assertEquals(expectedName, realName);
	}

	@Test
	public void testGetCurrency() {
		assertEquals(SweBank.getCurrency(), SEK);
	}

	@Test
	public void testOpenAccount() throws AccountExistsException, AccountDoesNotExistException {
		SweBank.openAccount("hunterlan", new Account(SEK));
		assertNotNull(SweBank.getBalance("hunterlan"));
	}

	@Test
	public void testDeposit() throws AccountDoesNotExistException {
		Money money = new Money(500d, SEK);
		SweBank.deposit("Bob", money);
		assertEquals(money.getAmount(), SweBank.getBalance("Bob"));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		Double expected = 0d;
		Double real = SweBank.getBalance("Ulrika");
		assertEquals(expected, real);
	}
	
	@Test
	public void testTransferToAnotherBank() throws AccountDoesNotExistException {
		Money money = new Money(500d, SEK);
		SweBank.deposit("Bob", money);
		SweBank.transfer("Bob", Nordea, "Bob", money);
		assertTrue(SweBank.getBalance("Bob") == 0 && Objects.equals(Nordea.getBalance("Bob"), 375d));
	}
	@Test
	public void testTransferInSameBank() throws AccountDoesNotExistException {
		Money money = new Money(500d, SEK);
		SweBank.deposit("Bob", money);
		SweBank.transfer("Bob", "Ulrika", money);
		assertTrue(SweBank.getBalance("Bob") == 0 && Objects.equals(SweBank.getBalance("Ulrika"), money.getAmount()));
	}
}
