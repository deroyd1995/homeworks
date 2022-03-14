package dz6;

import java.util.List;

public class Trader extends AbstractUser{
    public Trader(String login, String password) {
        super(login,password);
    }

    public void getActiveCustomers(List<Customer> customers){
       WriteFile writefile = new WriteFile();
       writefile.writeCSV(customers);

    }


}






