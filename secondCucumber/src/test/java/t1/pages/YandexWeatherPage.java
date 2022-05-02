package t1.pages;


import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import t1.core.BaseElement;

import java.util.Arrays;
import java.util.List;

public class YandexWeatherPage extends BasePage {

    @Override
    public String getPageName() {
        return "Страница погоды Яндекс";
    }

    @Override
    public WebElement getElementByName(String elementName) {
        return elements.stream()
                .filter(element -> element.getElementName().equals(elementName))
                .findFirst()
                .orElseThrow(() -> new Error("На странице не найден элемент для наименования: " + elementName)).getWebElement();
    }

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

    private List<BaseElement> elements = Arrays.asList(
    );

}
