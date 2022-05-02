package t1.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import t1.core.BaseElement;

import java.time.Duration;
import java.util.Arrays;
import java.util.List;

import static t1.steps.Conditions.DURATION;

public class YandexMainPage extends BasePage{



    @Override
    public String getPageName() {
        return "Главная страница";
    }

    @Override
    public WebElement getElementByName(String elementName) {
        return elements.stream()
                .filter(element -> element.getElementName().equals(elementName))
                .findFirst()
                .orElseThrow(() -> new Error("На странице не найден элемент для наименования: " + elementName)).getWebElement();
    }

    //Локаторы
    private static final String GLOBAL_INPUT_FIELD_SEL = ".mini-suggest__input";
    private static final String YANDEX_LOGO_LOC = "//div[@aria-label='Яндекс']";
    private static final String SEARCH_BUTTON_SEL = ".search2__button";
    private static final String RESULT_LIST_LOC = "//ul[@aria-label='Результаты поиска']";
    private static final String WEATHER_LOC = "//div[@class='weather__header']//a[text()='Погода']";
    private static final String CURRENT_TEMPERATURE_LOC = "//div[@class='weather__temp']";
    public static final String MARKET_LOC = "//a[@data-id='market']";

    //Элементы
    @FindBy(xpath = YANDEX_LOGO_LOC)
    WebElement yandexLogo;

    @FindBy(css = SEARCH_BUTTON_SEL)
    WebElement searchButton;

    @FindBy(css = GLOBAL_INPUT_FIELD_SEL)
    WebElement searchInput;

    @FindBy(xpath = CURRENT_TEMPERATURE_LOC)
    WebElement currentTemperature;

    @FindBy(xpath = RESULT_LIST_LOC)
    WebElement resultList;

    @FindBy(xpath = WEATHER_LOC)
    WebElement weather;

    @FindBy(xpath = MARKET_LOC)
    WebElement market;



    //METHODS
     public void goToPage(String pageName) throws InterruptedException {
        if(pageName == "Маркет"){
            logger.info("Начат переход на Яндекс.Маркет");
            market.click();
            for (String windowHandle : webDriver.getWindowHandles()){
                webDriver.switchTo().window(windowHandle);
            }
        }
        else if (pageName == "Погода"){
            logger.info("Начат переход на Яндекс.Погода");
            weather.click();
            for (String windowHandle : webDriver.getWindowHandles()){
                webDriver.switchTo().window(windowHandle);
            }
        }
    }

    public void getCurrentTemp (){
        String todayTemperature = currentTemperature.getText();
        System.out.println("Сегодня "+todayTemperature);
    }
   

    private List<BaseElement> elements = Arrays.asList(
            buildElementWithName(yandexLogo, "Яндекс картинки"),
            buildElementWithName(searchButton, "Кнопка Поиск"),
            buildElementWithName(searchInput, "Поле поиска"),
            buildElementWithName(currentTemperature,"Текущая температура"),
            buildElementWithName(resultList, "Список с результатами поиска"),
            buildElementWithName(weather,"Погода")
    );
}
