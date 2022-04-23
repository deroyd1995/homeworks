package t1.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.codeborne.selenide.Selenide.$x;
import static com.codeborne.selenide.Selenide.switchTo;

public class YandexMainPage {

    private static final Logger logger = LoggerFactory.getLogger(YandexMainPage.class);
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
        logger.info("Сегодня температура "+todayTemperature);
    }

    public void goToPage(String pageName){

        if(pageName == "Маркет"){
            logger.info("Начат переход на Яндекс.Маркет");
            SelenideElement xpath = MARKET;
            String pageTitle = "Интернет-магазин Яндекс.Маркет — покупки с быстрой доставкой";
            xpath.shouldBe(Condition.enabled).click();
            switchTo().window(pageTitle);
        }
        else if (pageName == "Погода"){
            logger.info("Начат переход на Яндекс.Погода");
            SelenideElement xpath = WEATHER;
            String pageTitle = "Прогноз погоды в Якутске на 10 дней — Яндекс.Погода";
            xpath.shouldBe(Condition.enabled).click();
            switchTo().window(pageTitle);
        }


    }

    public void searchInYandex (String expression){
        logger.info("Начат поиск по теме");
        INPUT_FIELD.shouldBe(Condition.enabled).sendKeys(expression);
        FIND_BUTTON.shouldBe(Condition.enabled).click();
        RESULT_LIST.shouldBe(Condition.enabled);
    }

}
