package com.t1.exceptions;

public class OwnExceptionExample {

    public void foo() throws MyException{
       throw new MyException(2);
    }

    public void bar() throws MyException {
        foo();
    }

    public static void main (String args[]){
        try{
            OwnExceptionExample x = new OwnExceptionExample();
            x.bar();
        }

        catch (MyException e){
            e.printStackTrace();
        }
    }
}
