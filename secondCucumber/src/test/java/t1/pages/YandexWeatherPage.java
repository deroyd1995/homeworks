package t1.pages;

import org.openqa.selenium.By;

public class YandexWeatherPage extends YandexMainPage {

    @Override
    public String getPageName() {
        return "Яндекс погода";
    }

    private static final String TOMORROW_TEMPERATURE_LOC = "//div[text()='Сегодня']/ancestor::div[1]/following-sibling::div[2]//span[text()='днём']/following-sibling::span[contains(@class,'temp__value')]";

    public void getTomorrowTemperature() {
        String tomorrowTemperature;
        tomorrowTemperature = webDriver.findElement(By.xpath(TOMORROW_TEMPERATURE_LOC)).getText();
        System.out.println("Tomorrow is " + tomorrowTemperature);

    }
}
