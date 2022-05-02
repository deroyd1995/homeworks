package t1.pages;

import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;
import static t1.steps.Conditions.DURATION;

public class YandexMainPage extends BasePage{

    //LOCATORS FOR GOOGLE
    public static final String YANDEX_LOGO = "//div[@aria-label='Яндекс']";
    public static final String INPUT_FIELD = "//input[@aria-label='Запрос']";
    public static final String FIND_BUTTON = "//span[text()='Найти']/parent::button";
    public static final String RESULT_LIST = "//ul[@aria-label='Результаты поиска']";
    public static final String CURRENT_TEMP = "//div[@class='weather__temp']";
    public static final String WEATHER = "//div[@class='weather__header']//a[text()='Погода']";
    public static final String MARKET = "//a[@data-id='market']";

    //METHODS
    public void searchInYandex (String expression){
        logger.info("Начат поиск по теме");
        Assertions.assertTrue(webDriver.findElement(By.xpath(YANDEX_LOGO)).isDisplayed());
        webDriver.findElement(By.xpath(INPUT_FIELD)).sendKeys(expression);
        webDriver.findElement(By.xpath(FIND_BUTTON)).click();
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(RESULT_LIST)));
        logger.info("Поиск по теме '"+expression+"' совершён успешно");
    }

    public void getCurrentTemp (){
        String todayTemperature = webDriver.findElement(By.xpath(CURRENT_TEMP)).getText();
        System.out.println("Сегодня "+todayTemperature);
    }

    public void goToPage(String pageName) throws InterruptedException {
        if(pageName == "Маркет"){
            logger.info("Начат переход на Яндекс.Маркет");
            WebElement webElement = webDriver.findElement(By.xpath(MARKET));
            webElement.click();
            for (String windowHandle : webDriver.getWindowHandles()){
                webDriver.switchTo().window(windowHandle);
            }
        }
        else if (pageName == "Погода"){
            logger.info("Начат переход на Яндекс.Погода");
            WebElement webElement = webDriver.findElement(By.xpath(WEATHER));
            webElement.click();
            for (String windowHandle : webDriver.getWindowHandles()){
                webDriver.switchTo().window(windowHandle);
            }
        }
    }
}
