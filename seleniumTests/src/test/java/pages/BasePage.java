package pages;

import org.openqa.selenium.WebDriver;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;

public class BasePage {

    //CONSTANTS
    public static final int DURATION = 15; //время ожидания, сек
    public static final int SLEEP = 5;     //время сна, сек
    public static final SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss"); //формат метки времени для результатов
    protected WebDriver webDriver;

    public void getTimeStamp(String text){
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+" "+text);
    }

    public BasePage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }
}