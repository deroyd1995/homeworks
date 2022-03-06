package dz6;

import com.opencsv.CSVWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

public class WriteFile {

    public static void writeCSV(List<Customer> customers) {

        // create FileWriter object with file as parameter
        try {
            FileWriter outputfile = new FileWriter("customers.csv");

            // create CSVWriter object filewriter object as parameter
            CSVWriter writer = new CSVWriter(outputfile);


            //По заданию надо было реализовать метод comparable, но также я видел, вы упомянули, что можно и этим методом обойтись
            //почему-то compareTo не получилось реализовать, всё равно неверно сортирует
            Collections.sort(customers);



            // add data to csv
            for (Customer s:customers){
                String[] data = s.toString().split(",");
                writer.writeNext(data);
            }
            String[] data=new String[]{};
            writer.writeNext(data);

            // closing writer connection
            writer.close();
        }
        catch (IOException e) {System.out.println(e.getMessage());}
    }
}
