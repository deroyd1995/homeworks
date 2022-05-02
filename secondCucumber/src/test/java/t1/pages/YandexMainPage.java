package t1.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import t1.core.BaseElement;

import java.util.Arrays;
import java.util.List;

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

    // Cucumber именование элементов

//    private BaseElement aYandexPictures(){
//        return BaseElement.builder()
//                .webElement(yandexLogo)
//                .elementName("Яндекс картинки")
//                .build();
//    }
//
//    private BaseElement aSearchBtn(){
//        return BaseElement.builder()
//                .webElement(searchButton)
//                .elementName("Кнопка Поиск")
//                .build();
//    }
//
//    private BaseElement aSearchInput(){
//        return BaseElement.builder()
//                .webElement(searchInput)
//                .elementName("Поле поиска")
//                .build();
//    }
//
//    private BaseElement aCurrentTemperature(){
//        return BaseElement.builder()
//                .webElement(currentTemperature)
//                .elementName("Текущая температура")
//                .build();
//    }
//
//    private BaseElement aResultList(){
//        return BaseElement.builder()
//                .webElement(resultList)
//                .elementName("Список с результатами поиска")
//                .build();
//    }
//
//    private BaseElement aWeather(){
//        return BaseElement.builder()
//                .webElement(weather)
//                .elementName("Погода")
//                .build();
//    }

    //
    /*public void searchThemeWithYandex(String expression) {
        Assertions.assertTrue(webDriver.findElement(By.xpath(YANDEX_LOGO_LOC)).isDisplayed());

        webDriver.findElement(By.xpath(GLOBAL_INPUT_FIELD_LOC)).sendKeys(expression);
        webDriver.findElement(By.xpath(FIND_BUTTON_LOC)).click();

        WebElement firstResult = new WebDriverWait(webDriver, Duration.ofSeconds(10))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(RESULT_LIST_LOC)));

    }*/

//    public void getCurrentTemperature() {
//        String todayTemperature;
//        todayTemperature = webDriver.findElement(By.xpath(CURRENT_TEMPERATURE_LOC)).getText();
//        System.out.println("Today is " + todayTemperature);
//    }
//
//    public void goToTemperatureForecast() {
//        webDriver.findElement(By.xpath(WEATHER_LOC)).click();
//        for (String windowHandle : webDriver.getWindowHandles()) {
//            webDriver.switchTo().window(windowHandle);
//        }
//    }

    private List<BaseElement> elements = Arrays.asList(
            buildElementWithName(yandexLogo, "Яндекс картинки"),
            buildElementWithName(searchButton, "Кнопка Поиск"),
            buildElementWithName(searchInput, "Поле поиска"),
            buildElementWithName(currentTemperature,"Текущая температура"),
            buildElementWithName(resultList, "Список с результатами поиска"),
            buildElementWithName(weather,"Погода")
    );
}
