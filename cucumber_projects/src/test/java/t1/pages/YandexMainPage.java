package t1.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class YandexMainPage {
    //LOCATORS
    public static final SelenideElement YANDEX_LOGO = $x("//div[@aria-label='Яндекс']");
    public static final SelenideElement INPUT_FIELD = $x("//input[@aria-label='Запрос']");
    public static final SelenideElement FIND_BUTTON = $x("//span[text()='Найти']/parent::button");
    public static final SelenideElement RESULT_LIST = $x("//ul[@aria-label='Результаты поиска']");
    public static final SelenideElement CURRENT_TEMP = $x("//div[@class='weather__temp']");
    public static final SelenideElement WEATHER = $x("//div[@class='weather__header']//a[text()='Погода']");
    public static final SelenideElement MARKET = $x("//a[@data-id='market']");

    public void getCurrentTemp (){
        String todayTemperature = CURRENT_TEMP.shouldBe(Condition.visible).getText();
        System.out.println("Today is "+todayTemperature);
    }

    public void goToPage(String pageName){
        SelenideElement xpath = $x("");
        String pageTitle = "";

        if(pageName == "Маркет"){
            xpath = MARKET;
            pageTitle = "Интернет-магазин Яндекс.Маркет — покупки с быстрой доставкой";
        }
        else if (pageName == "Погода"){
            xpath = WEATHER;
            pageTitle = "Прогноз погоды в Якутске на 10 дней — Яндекс.Погода";
        }

        xpath.shouldBe(Condition.enabled).click();
        switchTo().window(pageTitle);
    }

}
