package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount("12345", "John Doe");
    }

    @Test
    @DisplayName("Test creation of Bank Account without deposits (initial balance is 0)")
    void testBankAccountCreationWithoutDeposit() {
        assertEquals(0.0, account.getAvailableBalance(), "Initial balance should be 0.0");
        assertEquals("12345", account.getAccountNumber(), "Account number should be set correctly");
    }

    @Test
    @DisplayName("Deposit a valid amount")
    void testDepositValidAmount() {
        double initialBalance = account.getAvailableBalance();
        double depositAmount = 100.00;
        account.deposit(depositAmount);
        assertEquals(initialBalance + depositAmount, account.getAvailableBalance(), "Balance should increase by deposit amount");
    }

    @Test
    @DisplayName("Deposit an invalid amount (negative)")
    void testDepositInvalidAmountNegative() {
        double initialBalance = account.getAvailableBalance();
        double depositAmount = -50.00;
        account.deposit(depositAmount);
        assertEquals(initialBalance, account.getAvailableBalance(), "Balance should not change for negative deposit");
    }

    @Test
    @DisplayName("Deposit an invalid amount (zero)")
    void testDepositInvalidAmountZero() {
        double initialBalance = account.getAvailableBalance();
        double depositAmount = 0.00;
        account.deposit(depositAmount);
        assertEquals(initialBalance, account.getAvailableBalance(), "Balance should not change for zero deposit");
    }

    @Test
    @DisplayName("Withdraw a valid amount")
    void testWithdrawValidAmount() {
        account.deposit(200.00);
        double initialBalance = account.getAvailableBalance();
        double withdrawalAmount = 50.00;
        account.withdraw(withdrawalAmount);
        assertEquals(initialBalance - withdrawalAmount, account.getAvailableBalance(), "Balance should decrease by withdrawal amount");
    }

    @Test
    @DisplayName("Withdraw an invalid amount (negative)")
    void testWithdrawInvalidAmountNegative() {
        account.deposit(100.00);
        double initialBalance = account.getAvailableBalance();
        double withdrawalAmount = -20.00;
        account.withdraw(withdrawalAmount);
        assertEquals(initialBalance, account.getAvailableBalance(), "Balance should not change for negative withdrawal");
    }

    @Test
    @DisplayName("Withdraw an invalid amount (zero)")
    void testWithdrawInvalidAmountZero() {
        account.deposit(100.00);
        double initialBalance = account.getAvailableBalance();
        double withdrawalAmount = 0.00;
        account.withdraw(withdrawalAmount);
        assertEquals(initialBalance, account.getAvailableBalance(), "Balance should not change for zero withdrawal");
    }

    @Test
    @DisplayName("Withdraw an amount that exceeds the balance")
    void testWithdrawAmountExceedsBalance() {
        account.deposit(50.00);
        double initialBalance = account.getAvailableBalance();
        double withdrawalAmount = 100.00;
        account.withdraw(withdrawalAmount);
        assertEquals(initialBalance, account.getAvailableBalance(), "Balance should not change if withdrawal exceeds balance");
    }

    @Test
    @DisplayName("Test the getAccountNumber() method")
    void testGetAccountNumber() {
        assertEquals("12345", account.getAccountNumber(), "getAccountNumber should return the correct account number");
    }
}