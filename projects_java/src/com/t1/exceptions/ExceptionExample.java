package com.t1.exceptions;

public class ExceptionExample {
    public static void main (String args[]){
        try{
            int d = 0;
            int n = 10;
            int y = n/d;
            //System.out.println("End of calculations");
        }

        catch (ArithmeticException e){
           System.out.printf("In the catch block due to Exception = %s %n",e);
        }

        finally {
            System.out.println("Inside the finally block");
        }
    }

}




