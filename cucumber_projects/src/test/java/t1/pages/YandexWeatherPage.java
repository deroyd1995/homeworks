package t1.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selenide.$x;

public class YandexWeatherPage {

    //LOCATORS

    public static final SelenideElement TOMORROW_TEMPERATURE_CROPP = $x("//div[text()='Сегодня']/ancestor::div[1]/following-sibling::div[1]");

    public void getNextDayTemp(String day){
        Integer dayIndex = getDayIndex(day);
        SelenideElement TOMORROW_TEMP = $x("//li["+dayIndex+"]//div[@class='temp forecast-briefly__temp forecast-briefly__temp_day']//span[contains(@class,'temp__value')]");
        String tomorrow_temp = TOMORROW_TEMP.shouldBe(Condition.visible).getText();
        System.out.println(day+" температура "+tomorrow_temp+"°");
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
            return 2; //сегодня
        }
    }
}
