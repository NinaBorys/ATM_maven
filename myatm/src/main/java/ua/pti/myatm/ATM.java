package ua.pti.myatm;

import ua.pti.myatm.Account;
import ua.pti.myatm.Card;
import ua.pti.myatm.NoCardInsertedException;
import ua.pti.myatm.NotEnoughMoneyInATMExeption;
import ua.pti.myatm.NotEnoughMoneyOnCardException;

public class ATM {
     private double moneyInATM;
     private Card card;  
     
    //Можно задавать количество денег в банкомате     
    public ATM(double moneyInATM) {
        if (moneyInATM <0) {
        throw new IllegalArgumentException("Incorrect input");
        }
        this.moneyInATM = moneyInATM;
        this.card = null;
}
    

    // Возвращает каоличестов денег в банкомате
    public double getMoneyInATM() {
         return this.moneyInATM;
    }
        
    //С вызова данного метода начинается работа с картой
    //Метод принимает карту и пин-код, проверяет пин-код карты и не заблокирована ли она
    //Если неправильный пин-код или карточка заблокирована, возвращаем false. 
    //При этом, вызов всех последующих методов у ATM с данной картой должен генерировать исключение NoCardInserted
    public boolean validateCard(Card card, int pinCode){
        if (card == null) {
            throw new NullPointerException("Enter real card");
        } 
        else if (card.isBlocked() || !card.checkPin(pinCode)) {
            return false;
        }
        else {
            this.card = card;
            return true;
        }       
    }   
    
    //Возвращает сколько денег есть на счету
 
    public double checkBalance() throws NoCardInsertedException {
        checkCard();
        return card.getAccount().getBalance();
    }
    
    //Checks if card was inserted
    
     private void checkCard() throws NoCardInsertedException {
        if (this.card == null) {
            throw new NoCardInsertedException("No card. Insert you card, please");
        }
    }
        
    //Метод для снятия указанной суммы
    //Метод возвращает сумму, которая у клиента осталась на счету после снятия
    //Кроме проверки счета, метод так же должен проверять достаточно ли денег в самом банкомате
    //Если недостаточно денег на счете, то должно генерироваться исключение NotEnoughMoneyInAccount 
    //Если недостаточно денег в банкомате, то должно генерироваться исключение NotEnoughMoneyInATM 
    //При успешном снятии денег, указанная сумма должна списываться со счета, и в банкомате должно уменьшаться количество денег
    public double getCash(double amount) throws NoCardInsertedException, NotEnoughMoneyOnCardException, NotEnoughMoneyInATMExeption {
        checkCard();
        Account account = this.card.getAccount();

        
        
        if (account.getBalance() < amount) {
            throw new NotEnoughMoneyOnCardException("Sorry, you have not enough money for this transaction");
        }
        //this.moneyInATM -= account.withdrow(amount); //for order
        if (this.getMoneyInATM() < amount) {
            throw new NotEnoughMoneyInATMExeption("No enough money in ATM for this transaction. Please, try later");
        }
       
            this.moneyInATM -= account.withdrow(amount);
            return account.getBalance();
        
   //return account.getBalance();  // for order
    }
    
}


