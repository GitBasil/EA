package FinalPractice.domain;

public class Counter {
    private int counterValue=0;
    public int increment(){
        return ++counterValue;
    }
    public int decrement(){
        return --counterValue;
    }
    public int getCounterValue() {
        return counterValue;
    }
}