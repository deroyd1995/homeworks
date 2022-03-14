package org.example;

class MyException extends Exception {
    int a;
    String token;
    String login;
    String result;

    MyException(int a,String token) {
        this.a = a;
        this.token = token;
    }

    MyException(int a,String token, String login) {
        this.a = a;
        this.token = token;
        this.login = login;
    }

    public String toString(){
        //Failed input: 1 - FIO, 2 - login, 3 - password, 4 - telephone number

        switch(a){
            case 1: result="Ошибка чтения ФИО : "+"'"+token+"'";
            break;
            case 2: result="Ошибка чтения логина : "+"'"+token+"'";
            break;
            case 3: result="Ошибка чтения пароля для "+login;
            break;
            case 4: result="Ошибка чтения номера телефона : "+"'"+token+"'";
            break;
        }
       return result;
    }
}
