package org.example;

import java.util.List;

public class OnlineMarket {
    public static void main(String[] args) {
        //   файл newcustomers.csv - файл для чтения новых пользователей
        //   файл customers.csv - файл с пользователями, по которым успешно был проведён парсинг

        ReadFile readfile = new ReadFile();
        Trader trader = new Trader("admin","admin");
        List<Customer> customers = readfile.readCSV();

        trader.getActiveCustomers(customers);
    }

}
