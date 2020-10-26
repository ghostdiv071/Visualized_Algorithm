package com.company;

public class Counter {

    private int countSwap;
    private int countComp;

    public Counter(int countComp, int countSwap){
        this.countComp = countComp;
        this.countSwap = countSwap;
    }

    public int getCountSwap() {
        return countSwap;
    }

    public int getCountComp() {
        return countComp;
    }

    public void incCountComp(){
        countComp++;
    }

    public void incCountSwap(){
        countSwap++;
    }
}