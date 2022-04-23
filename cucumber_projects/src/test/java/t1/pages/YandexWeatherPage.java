package t1.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import static com.codeborne.selenide.Selenide.$x;

public class YandexWeatherPage {

    private static final Logger logger = LoggerFactory.getLogger(YandexMainPage.class);

    //LOCATORS

    public static final SelenideElement TOMORROW_TEMPERATURE_CROPP = $x("//div[text()='Сегодня']/ancestor::div[1]/following-sibling::div[1]");

    public void getNextDayTemp(String day){
        Integer dayIndex = getDayIndex(day);
        SelenideElement TOMORROW_TEMP = $x("//li["+dayIndex+"]//div[@class='temp forecast-briefly__temp forecast-briefly__temp_day']//span[contains(@class,'temp__value')]");
        String tomorrow_temp = TOMORROW_TEMP.shouldBe(Condition.visible).getText();
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
