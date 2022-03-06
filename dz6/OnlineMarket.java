package dz6;

import java.util.List;

public class OnlineMarket {
    public static void main(String[] args) {
        ReadFile readfile = new ReadFile();
        Trader trader = new Trader("admin","admin");
        List<Customer> customers = readfile.readCSV();

        trader.getActiveCustomers(customers);
    }

}
