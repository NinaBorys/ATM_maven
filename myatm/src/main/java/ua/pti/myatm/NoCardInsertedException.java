package ua.pti.myatm;

public class NoCardInsertedException extends Exception {

    public NoCardInsertedException(String msg) {
        super(msg);
    }
    
}