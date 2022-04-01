package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.sql.Timestamp;

public class YandexWeatherPage extends YandexMainPage {
    public YandexWeatherPage(WebDriver webDriver) {
        super(webDriver);
    }

    //LOCATORS
    private static final String TOMORROW_TEMP = "//div[text()='Сегодня']/ancestor::div[1]/following-sibling::div[1]//span[text()='днём']/following-sibling::span[contains(@class,'temp__value')]";
    public static final String TOMORROW_TEMPERATURE_CROPP = "//div[text()='Сегодня']/ancestor::div[1]/following-sibling::div[1]";

    //METHODS
    public void getTomorrowTemp(){
        String tomorrow_temp = webDriver.findElement(By.xpath(TOMORROW_TEMP)).getText();
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        System.out.println(timeFormat.format(timestamp)+" Tomorrow is "+tomorrow_temp+"°");
    }

}
