package ua.pti.myatm;

public class NotEnoughMoneyOnCardException extends Exception {

    public NotEnoughMoneyOnCardException(String msg) {
        super(msg);
    }
}
