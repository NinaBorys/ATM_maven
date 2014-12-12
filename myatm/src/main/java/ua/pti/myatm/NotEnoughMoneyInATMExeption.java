package ua.pti.myatm;

public class NotEnoughMoneyInATMExeption extends Exception {

    public NotEnoughMoneyInATMExeption(String msg) {
        super(msg);
    }
}
