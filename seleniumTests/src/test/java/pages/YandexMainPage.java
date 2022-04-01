package pages;


import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class YandexMainPage extends BasePage{

    public YandexMainPage(WebDriver webDriver) {
        super(webDriver);
    }

    //LOCATORS
    public static final String YANDEX_LOGO = "//div[@aria-label='Яндекс']";
    public static final String INPUT_FIELD = "//input[@aria-label='Запрос']";
    public static final String FIND_BUTTON = "//span[text()='Найти']/parent::button";
    public static final String RESULT_LIST = "//ul[@aria-label='Результаты поиска']";
    public static final String CURRENT_TEMP = "//div[@class='weather__temp']";
    public static final String WEATHER = "//div[@class='weather__header']//a[text()='Погода']";
    //public static final String MARKET = "//div[text()='Маркет']";
    public static final String MARKET = "//a[@data-id='market']";

    //METHODS
    public void searchInYandex (String expression){
        Assertions.assertTrue(webDriver.findElement(By.xpath(YANDEX_LOGO)).isDisplayed());
        webDriver.findElement(By.xpath(INPUT_FIELD)).sendKeys(expression);
        webDriver.findElement(By.xpath(FIND_BUTTON)).click();
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(RESULT_LIST)));
        System.out.println("Found '"+expression+"' in Yandex successfully");
    }

    public void getCurrentTemp (){
        String todayTemperature = webDriver.findElement(By.xpath(CURRENT_TEMP)).getText();
        System.out.println("Today is "+todayTemperature);
    }

    public void goToPageTomorrowForecast() throws InterruptedException{
        WebElement webElement = webDriver.findElement(By.xpath(WEATHER));
        webElement.click();
        TimeUnit.SECONDS.sleep(SLEEP);
        for (String windowHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(windowHandle);
        }
    }

    public void ifMarketExists() {
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(MARKET)));
        getTimeStamp("Checked if market exists");
    }

    //МЕТОД ДЛЯ ПЕРЕХОДА НА ЯНДЕКС МАРКЕТ
    public void followToMarket() throws InterruptedException{
        WebElement webElement = webDriver.findElement(By.xpath(MARKET));
        webElement.click();
        for (String windowHandle : webDriver.getWindowHandles()){
            webDriver.switchTo().window(windowHandle);
        }
        getTimeStamp("Followed market link successfully");
        TimeUnit.SECONDS.sleep(SLEEP);
    }
}
