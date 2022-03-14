package com.t1;

public class SumMachine {
    private int state = 0;

    public SumMachine(){

    }
    public SumMachine(int state){
        this.state = state;
    }
    public int getState(){
      return state;
    }

public void add(int a){
        state += a;
}

public void add(int a,int b){
        state += a+b;
}

    public void add(int a, int b, int c){
        state += a+b+c;
    }


}
