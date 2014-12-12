package ua.pti.myatm;

import ua.pti.myatm.ATM;
import static org.mockito.Mockito.when;
import org.junit.Test;
import static org.mockito.Mockito.mock;
import static org.junit.Assert.*;
import org.mockito.InOrder;
import static org.mockito.Mockito.inOrder;
/**
*
* @author nina
*/
public class ATMTest {
    
public ATMTest() {
}
/**
* Test of getMoneyInATM method, of class ATM.
*/
    @Test
    public void testGetMoneyInATM() {
        System.out.println("getMoneyInATM");
        double MoneyIstance=999;
        ATM instance = new ATM(MoneyIstance);
        double expResult = 999;
        double result = instance.getMoneyInATM();
        assertEquals(expResult, result, 0.0);
    }
/*
Test of IllegalArgumentException on ATM class
*/
    @Test (expected = IllegalArgumentException.class)
        public void testInputArgumentsInATMClassThrowsIllegalArgumentException(){
            ATM atm = new ATM(-5);
        
    }
    /*Test for zero-checking in ATM class*/
        
    @Test 
        public void testOfInitAtmWithZero() {
           System.out.println("zero money in ATM");
           ATM some_atm = new ATM(0); 
           double expResult = 0;
           double result=some_atm.getMoneyInATM();
           assertEquals(expResult, result, 0.0);
           
        }
            
    /* Test for functions order 
        (withdrow() goes only after getBalance()*/
        
    @Test 
        public void testOfOrderOfAccountFunctions()throws NoCardInsertedException, NotEnoughMoneyOnCardException, NotEnoughMoneyInATMExeption{
            System.out.println("check if getBalance is called before withdrow");
               
            double amount =1000;
            ATM atm = new ATM(10000);
            
            Card card = mock(Card.class);
            int pinCode = 1234;
      
            Account account = mock(Account.class);
            double actualValue = 10000;
            
            when(account.getBalance()).thenReturn(actualValue);
            when(card.getAccount()).thenReturn(account);
            when(card.isBlocked()).thenReturn(false);
            when(card.checkPin(pinCode)).thenReturn(true);
            
            atm.validateCard(card, pinCode);
            atm.getCash(amount);
            
            
            InOrder my_order = inOrder(account);
            my_order.verify(account).getBalance();
            //my_order.verify(atm).getMoneyInATM();
            my_order.verify(account).withdrow(amount);
                    

        }
        
        
        
        
        
/**
* Test of validateCard method, of class ATM.
*/
    @Test (expected = NullPointerException.class)
        public void testValidateCardCardIsNullThrownIllegalArgumentException() {
        ATM atm = new ATM(999);
        atm.validateCard(null, 999);
    }
        
    @Test
    public void testValidateCardSuccess() {
        System.out.println("validateCard-success expected");
        Card card = mock(Card.class);
        int pinCode = 1234;
        ATM instance = new ATM(999);
        when(card.isBlocked()).thenReturn(false);
        when(card.checkPin(pinCode)).thenReturn(false);
        boolean result = instance.validateCard(card, pinCode);
        assertFalse(result);
    }
    
    @Test
    public void testValidateCardBlockedOne() {
        System.out.println("validateCard-fail expected");
        Card card = mock(Card.class);
        int pinCode = 12345;
        ATM instance = new ATM(999);
        when(card.isBlocked()).thenReturn(true);
        boolean result = instance.validateCard(card, pinCode);
        assertFalse(result);
    }
/**
* Test of checkBalance method, of class ATM.
     * @throws atm_qaqc.NoCardInsertedException
*/
    @Test (expected = NoCardInsertedException.class)
    public void testCheckBalanceCardIsNullThrownNoCardInsertedException() throws NoCardInsertedException{
        ATM atm = new ATM(1000);
        atm.checkBalance();
    }
    @Test
    public void testCheckBalanceSuccessExpected() throws NoCardInsertedException{
        ATM instance = new ATM(100);
        Card card = mock(Card.class);
        int pinCode = 1234;
        Account account = mock(Account.class);
        double actualValue = 1000;
        
        when(account.getBalance()).thenReturn(actualValue);
        when(card.getAccount()).thenReturn(account);
        when(card.isBlocked()).thenReturn(false);
        when(card.checkPin(pinCode)).thenReturn(true);
        instance.validateCard(card,pinCode);
        double expectedResult = 1000;
        assertEquals(instance.checkBalance(),expectedResult,0.0);
    }
/**
* Test of getCash method, of class ATM.
     * @throws atm_qaqc.NoCardInsertedException
     * @throws atm_qaqc.NotEnoughMoneyOnCardException
     * @throws atm_qaqc.NotEnoughMoneyInATMExeption
*/
    @Test
    public void testGetCash() throws NoCardInsertedException, NotEnoughMoneyOnCardException, NotEnoughMoneyInATMExeption {
        System.out.println("getCash");
        double amount = 1000;
        ATM instance = new ATM(1000);
        Card card = mock(Card.class);
        int pinCode=1234;
        Account account=mock(Account.class);
        double value=1000;
        double expResult=1000;
        
        when(account.getBalance()).thenReturn(value);
        when(card.getAccount()).thenReturn(account);
        when(card.isBlocked()).thenReturn(false);
        when(card.checkPin(pinCode)).thenReturn(true);
        
        instance.validateCard(card, pinCode);
        double result=instance.getCash(amount);
        assertEquals(result, expResult,0.0);
    }
    
    @Test (expected = NotEnoughMoneyOnCardException.class)
    public void testGetCashThronwNotEnoughMoneyInAccountNotEnoughMoneyOnCardException() throws NoCardInsertedException, NotEnoughMoneyInATMExeption, NotEnoughMoneyOnCardException{
        double amount =1001;
        ATM instance = new ATM(10);
        Card card = mock(Card.class);
        int pinCode = 1234;
        Account account = mock(Account.class);
        double actualValue = 1000;
        
        when(account.getBalance()).thenReturn(actualValue);
        when(card.getAccount()).thenReturn(account);
        when(card.isBlocked()).thenReturn(false);
        when(card.checkPin(pinCode)).thenReturn(true);
        
        instance.validateCard(card,pinCode);
        instance.getCash(amount);
    }
    
    @Test (expected = NotEnoughMoneyInATMExeption.class)
    public void testGetCashThronwNotEnoughMoneyInATMExeption() throws NoCardInsertedException, NotEnoughMoneyInATMExeption, NotEnoughMoneyOnCardException{
        double amount =1001;
        ATM instance = new ATM(5);
        Card card = mock(Card.class);
        int pinCode = 1234;
        Account account = mock(Account.class);
        double actualValue = 1005;
        
        when(account.getBalance()).thenReturn(actualValue);
        when(card.getAccount()).thenReturn(account);
        when(card.isBlocked()).thenReturn(false);
        when(card.checkPin(pinCode)).thenReturn(true);
        
        instance.validateCard(card,pinCode);
        instance.getCash(amount);
    }
    

    @Test (expected = NoCardInsertedException.class)
    public void testGetCashCardIsNullThrownNoCardInserted() throws NoCardInsertedException, NotEnoughMoneyInATMExeption, NotEnoughMoneyOnCardException{
        ATM instance = new ATM(1000);
        double amount =123;
        assertNull(instance.getCash(amount));
    }
}
