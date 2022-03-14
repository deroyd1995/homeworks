package org.example;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class ReadFile {

    public List<Customer> readCSV() {

        List<Customer> customers = new ArrayList<>(); //массив покупателей
        marketUUID marketUID = new marketUUID();

        //применяемые регулярки
        String regexFIO = "^[а-яa-zА-ЯA-ZёЁ\\-]{0,} [а-яa-zА-ЯA-ZёЁ\\-]{0,} [а-яa-zА-ЯA-ZёЁ\\-]{1,}$"; // ФИО латиница и кириллица, но обязательно 3 компоненты
        String regexLogin = "^[a-zA-Z]{3,20}$";                                                      // логин от 3 до 20 латинских символов
        String regexPassword = "^[a-zA-Z0-9]{3,20}$";                                                // пароль от 3 до 20 латинских символов и цифр
        String regexTelnumber = "\\+7[(]?(\\d{3})[)]?\\d{3}[-]?\\d{2}[-]?\\d{2}";                         // пароль от 3 до 20 латинских символов и цифр

        CSVReader reader = null;
        //parsing a CSV file into CSVReader class constructor

        String[] nextLine;
        //reads one line at a time


        try

        {
            reader = new CSVReader(new FileReader("newcustomers.csv"), ',');

            //на эту метку перекидывает в том случае, если поймали exception
            here:
            while ((nextLine = reader.readNext()) != null) {
                int i = 1; //при начале с новой строки будет возвращаться на 1 токен
                int x;   //необходимый параметр для передачи в MyException значения переменной цикла
                String id = marketUID.newUUID();

                for (String token : nextLine) {
                    try {
                        x = i;
                        switch (i) {
                            case 1:
                                if (Pattern.matches(regexFIO, token)) {i += 1;continue;}
                                else {i = 0;throw new MyException(x, token);}
                            case 2:
                                if (Pattern.matches(regexLogin, token)) {i += 1;continue;}
                                else {i = 0;throw new MyException(x, token);}
                            case 3:
                                if (Pattern.matches(regexPassword, token)) {i += 1;continue;}
                                else {i = 0;throw new MyException(x, token,nextLine[1]);}
                            case 4:
                                if (Pattern.matches(regexTelnumber, token)) {break;}
                                else {i = 0;throw new MyException(x, token);}
                            default:continue here;
                        }
                    } catch (MyException e) {
                        System.out.println(e);
                    }
                }
                if (i!=0){
                    customers.add(new Customer(nextLine[0], nextLine[1], nextLine[2], nextLine[3], id));
                }

            }
        }
         catch(IOException e) {System.out.println(e);}

        return customers;
    }
}
