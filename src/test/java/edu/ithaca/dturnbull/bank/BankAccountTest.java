package edu.ithaca.dturnbull.bank;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


class BankAccountTest {

    @Test
    void getBalanceTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals(200, bankAccount.getBalance(), 0.001);
        
        bankAccount= new BankAccount("a@b.com", 500);
        assertEquals(500, bankAccount.getBalance(), 0.001);

        bankAccount= new BankAccount("a@b.com", .01);
        assertEquals(.01,bankAccount.getBalance(),0.001);

    }

    @Test
    void isAmountValidTest(){
        assertTrue(BankAccount.isAmountValid(50));
        assertTrue(BankAccount.isAmountValid(.01));
        assertTrue(BankAccount.isAmountValid(9999.9));

        assertFalse(BankAccount.isAmountValid(-85));
        assertFalse(BankAccount.isAmountValid(.001));
        assertFalse(BankAccount.isAmountValid(10.9901));
    }

    @Test
    void withdrawTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);
        bankAccount.withdraw(100);

        assertEquals(100, bankAccount.getBalance(), 0.001);

        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(300));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(-50));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.withdraw(.001));

        bankAccount.withdraw(99.99);
        assertEquals(.01, bankAccount.getBalance(), 0.001);
    }

    @Test
    void depositTest() throws InsufficientFundsException{
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        bankAccount.deposit(100);

        assertEquals(300, bankAccount.getBalance(),0.001);
        bankAccount.deposit(.01);
        assertEquals(300.01, bankAccount.getBalance(),0.001);

        assertThrows(InsufficientFundsException.class, () -> bankAccount.deposit(-50));
        assertThrows(InsufficientFundsException.class, () -> bankAccount.deposit(.001));
    }

    @Test
    void isEmailValidTest(){
        assertTrue(BankAccount.isEmailValid( "a@b.com"));   // valid email address
        assertFalse( BankAccount.isEmailValid(""));         // empty string

        assertTrue(BankAccount.isEmailValid("abc-d@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));
        assertTrue(BankAccount.isEmailValid("abc@mail.com"));

        assertFalse(BankAccount.isEmailValid(".abc@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc-@mail.com"));
        assertFalse(BankAccount.isEmailValid("abc..def@mail.com"));


        assertTrue(BankAccount.isEmailValid("abc.def@mail.cc"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.org"));
        assertTrue(BankAccount.isEmailValid("abc.def@mail.com"));

        assertFalse(BankAccount.isEmailValid("abc.def@mail.c"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail"));
        assertFalse(BankAccount.isEmailValid("abc.def@mail..com"));

        
    }

    @Test
    void constructorTest() {
        BankAccount bankAccount = new BankAccount("a@b.com", 200);

        assertEquals("a@b.com", bankAccount.getEmail());
        assertEquals(200, bankAccount.getBalance(), 0.001);
        //check for exception thrown correctly
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("", 100));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("A@B.com",-95));
        assertThrows(IllegalArgumentException.class, ()-> new BankAccount("A@B.com",10.001));
    }

}