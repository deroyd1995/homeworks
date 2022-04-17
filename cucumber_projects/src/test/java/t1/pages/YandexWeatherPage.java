package t1.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;


import static com.codeborne.selenide.Selenide.$x;

public class YandexWeatherPage {

    //LOCATORS

    public static final SelenideElement TOMORROW_TEMPERATURE_CROPP = $x("//div[text()='Сегодня']/ancestor::div[1]/following-sibling::div[1]");

    public void getNextDayTemp(String day){
        Integer dayIndex = 1;
        if (day == "завтра"){
            dayIndex = 1;
        }
        else if (day == "послезавтра"){
            dayIndex = 2;
        }

        final SelenideElement TOMORROW_TEMP = $x("//div[text()='Сегодня']/ancestor::div[1]/following-sibling::div["+dayIndex+"]//span[text()='днём']/following-sibling::span[contains(@class,'temp__value')]");
        String tomorrow_temp = TOMORROW_TEMP.shouldBe(Condition.visible).getText();
        System.out.println(day+" температура "+tomorrow_temp+"°");
    }
}
