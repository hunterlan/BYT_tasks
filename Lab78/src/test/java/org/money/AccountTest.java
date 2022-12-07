package org.money;

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;

import static org.junit.Assert.*;

public class AccountTest {
	Currency SEK, DKK;
	Bank Nordea;
	Bank DanskeBank;
	Bank SweBank;
	Account testAccount;
	
	@Before
	public void setUp() throws Exception {
		SEK = new Currency("SEK", 0.15);
		DKK = new Currency("DKK", 0.10);
		SweBank = new Bank("SweBank", SEK);
		testAccount = new Account(SEK);
		SweBank.openAccount("Alice", testAccount);

		SweBank.deposit("Alice", new Money(1000000d, SEK));
	}
	
	@Test
	public void testAddTimedPayment() throws AccountExistsException {
		Account secondAccount = new Account(DKK);
		SweBank.openAccount("Hans", secondAccount);
		testAccount.addTimedPayment("1", 15, 5, new Money(500d, SEK), SweBank, "Hans");
		assertTrue(testAccount.timedPaymentExists("1"));
	}

	@Test
	public void testRemoveTimedPayment() throws AccountExistsException {
		Account secondAccount = new Account(DKK);
		SweBank.openAccount("Hans", secondAccount);
		testAccount.addTimedPayment("1", 15, 5, new Money(500d, SEK), SweBank, "Hans");
		testAccount.removeTimedPayment("1");
		assertFalse(testAccount.timedPaymentExists("1"));
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException, AccountExistsException {
		Account secondAccount = new Account(DKK);
		SweBank.openAccount("Hans", secondAccount);
		testAccount.addTimedPayment("1", 15, 5, new Money(500d, SEK), SweBank, "Hans");
		for (int i = 0; i < 6; i++) {
			testAccount.tick();
		}
		Double expectedBalance = 750.0;
		Double realBalance = secondAccount.getBalance().getAmount();
		assertEquals(expectedBalance, realBalance, 0);
	}

	@Test
	public void testAddWithdraw() {
		Double previousAmount = this.testAccount.getBalance().getAmount();
		Money countWithdrawMoney = new Money(1000d, SEK);
		this.testAccount.withdraw(countWithdrawMoney);
		Double currentAmount = this.testAccount.getBalance().getAmount();
		assertTrue(!Objects.equals(currentAmount, previousAmount) && currentAmount == (previousAmount - countWithdrawMoney.getAmount()));
	}
	
	@Test
	public void testGetBalance() throws AccountDoesNotExistException {
		Double expectedBalance = 1000000d;
		Double currentBalance = SweBank.getBalance("Alice");
		assertEquals(expectedBalance, currentBalance, 0);
	}
}
