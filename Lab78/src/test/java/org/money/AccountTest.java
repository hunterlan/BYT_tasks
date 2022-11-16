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
		SweBank = new Bank("SweBank", SEK);
		SweBank.openAccount("Alice");
		testAccount = new Account("Hans", SEK);
		testAccount.deposit(new Money(10000000, SEK));

		SweBank.deposit("Alice", new Money(1000000, SEK));
	}
	
	@Test
	public void testAddRemoveTimedPayment() {
		fail("Write test case here");
	}
	
	@Test
	public void testTimedPayment() throws AccountDoesNotExistException {
		fail("Write test case here");
	}

	@Test
	public void testAddWithdraw() {
		Integer previousAmount = this.testAccount.getBalance().getAmount();
		Money countWithdrawMoney = new Money(1000, SEK);
		this.testAccount.withdraw(countWithdrawMoney);
		Integer currentAmount = this.testAccount.getBalance().getAmount();
		assertTrue(!Objects.equals(currentAmount, previousAmount) && currentAmount == (previousAmount - countWithdrawMoney.getAmount()));
	}
	
	@Test
	public void testGetBalance() {
		fail("Write test case here");
	}
}
