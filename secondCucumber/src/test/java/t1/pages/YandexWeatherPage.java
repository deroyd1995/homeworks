package t1.pages;


import org.openqa.selenium.By;

public class YandexWeatherPage extends YandexMainPage {

    //METHODS
    public void getNextDayTemp(String day){
        Integer dayIndex = getDayIndex(day);
        //LOCATORS FOR GOOGLE
        String TOMORROW_TEMP = "//li["+dayIndex+"]//div[@class='temp forecast-briefly__temp forecast-briefly__temp_day']//span[contains(@class,'temp__value')]";

        //LOCATORS FOR GECKO

        String tomorrow_temp = webDriver.findElement(By.xpath(TOMORROW_TEMP)).getText();
        logger.info(day+" температура "+tomorrow_temp+"°");
    }

    public Integer getDayIndex(String day){
        if (day.equalsIgnoreCase("завтра")){
            return 3;
        }
        else if(day.equalsIgnoreCase("послезавтра")){
            return 4;
        }
        else if(day.equalsIgnoreCase("вчера")){
            return 1;
        }
        else {
            logger.info("взята температура по умолчанию");
            return 2; //сегодня
        }
    }

}
