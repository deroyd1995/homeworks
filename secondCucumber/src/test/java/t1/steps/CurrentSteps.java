package t1.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;
import t1.pages.YandexMainPage;
import t1.pages.YandexWeatherPage;

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.util.Map;

import static org.junit.Assert.assertTrue;
import static t1.core.CucumberContext.CUCUMBER_CONTEXT;
import static t1.steps.Conditions.driver;
import static t1.steps.Conditions.pageCatalog;

public class CurrentSteps {
    private static final ch.qos.logback.classic.Logger logger
            = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(CurrentSteps.class);

    @Дано("пользователь на странице {string} сохраняет текст элемента {string} в переменную контекста {string}")
    public void saveElementTextToContext(String pageName, String elementName, String param) {
        logger.info("Сохранение переменной контекста");
        String text = pageCatalog.waitForElementPresent(pageCatalog.getPageByName(pageName).getElementByName(elementName), "Не найден элемент " + elementName).getText();
        CUCUMBER_CONTEXT.put(param, text);
        logger.debug(String.format("Переменная контекста %s со страницы %s сохранена",param,pageName));
    }

    @Дано("пользователь на странице {string} находит элемент {string} в коллекции {string}, общий локатор {string}")
    public void isElementPresentedInCollection(String pageName, String name, String collectionOfItems,String sameLocator){
        logger.info("Начинается поиск элемента на странице");
        if (pageCatalog.IsElementInCollection(pageCatalog.getPageByName(pageName).getElementByName(collectionOfItems),
                pageCatalog.getPageByName(pageName).getElementXpath(sameLocator),
                name, String.format("Нет ни одного элемента в %s", collectionOfItems)))
        {
            logger.info("Элемент найден");
            logger.debug(String.format("%s найден на странице %s",name,pageName));
        }

        else logger.error("Элемент не найден");
    }

    @Дано("пользователь на странице {string} ожидает элемент {string}")
    public void waitForElementOnPage(String pageName, String elementName) {
        logger.info("Ожидание элемента на странице");
        assertTrue(pageCatalog.waitForElementPresent(pageCatalog.getPageByName(pageName).getElementByName(elementName),"Не найден элемент " + elementName).getText(),true);
        logger.debug(String.format("Элемент %s на странице %s появился",elementName,pageName));
    }

    @Тогда("Сравнить две перменные из контекста 1: {string}, 2: {string}")
    public void compareTwoParamsFromContext(String firstParam, String secondParam) {
        Assertions.assertEquals(CUCUMBER_CONTEXT.get(firstParam), CUCUMBER_CONTEXT.get(secondParam));
        logger.debug(String.format("Переменная контекста %s равна переменной контекста %s",firstParam,secondParam));
    }

    @Тогда("сравнить переменную из контекста: {string} с ожидаемым результатом: {string}")
    public void compareParamFromContextWithExpected(String actualResult, String expectedResult) {
        logger.info("Сравнение переменной из контекста с ожидаемым результатом");
        Assertions.assertEquals(expectedResult, CUCUMBER_CONTEXT.get(actualResult));
        logger.debug(String.format("Переменная контекста %s равна ожидаемому результату %s",actualResult,expectedResult));
    }

    //Использовать для сравнения чисел >, ==, < и тд.
    @Тогда("Убедиться в истинности числового выражения {string}")
    public void checkEvalisTrue(String eval) throws ScriptException {
        String streval = eval;
        for (Map.Entry<String, Object> entry : CUCUMBER_CONTEXT.getContext().entrySet())  {
            if (streval.contains(entry.getKey().replaceAll("^.*:", ""))) {
                streval = streval.replace(entry.getKey().replaceAll("^.*:", ""), (String) CUCUMBER_CONTEXT.get(entry.getKey().replaceAll("^.*:", "")));
            }
        }
        ScriptEngineManager mgr = new ScriptEngineManager();
        ScriptEngine engine = mgr.getEngineByName("JavaScript");
        assertTrue("Expression is false", (Boolean) engine.eval(streval));
    }

    @Дано("пользователь на странице {string} кликает на элемент {string}")
    @SneakyThrows
    public void clickOnElement(String pageName, String elementName) {
        logger.info("Пользователь кликает на элемент");
        pageCatalog.waitForElementAndClick(pageCatalog.getPageByName(pageName).getElementByName(elementName),
                "Не удалось кликнуть на эелемент: " + elementName, 15);
        logger.debug(String.format("Пользователь на странице %s кликнул на элемент %s",pageName,elementName));
    }

    @Дано("пользователь на странице {string} вводит текст {string} в элемент {string}")
    public void sendKeysValue(String pageName, String text, String elementName) {
        logger.info("Пользователь вводит текст");
        pageCatalog.waitForElementAndSendKeys(pageCatalog.getPageByName(pageName).getElementByName(elementName), text,
                "Не удалось ввести текст " + text + " в эелемент: " + elementName);
        logger.debug(String.format("Пользователь на странице %s вводит текст '%s'",pageName,text));
    }

    @Дано("пользователь проверяет, что ссылка текущей страницы содержит базовую часть {string} и текст {string}")
    public void checkURL(String basePart, String text) {
        String url = driver.getCurrentUrl();
        Assertions.assertTrue(url.contains(basePart), "url " + url + " does not match " + basePart + " in URL");
        Assertions.assertTrue(url.contains(text),"url" + url + " does not match " + text + " in URL");
    }

    @Дано("пользователь на странице {string} проверяет, что элемент {string} активен")
    public void checkElementAttribute(String pageName, String elementName) {
        logger.info("Пользователь проверяет, что элемент активен");
        WebElement element = pageCatalog.waitForElementExist(pageCatalog.getPageByName(pageName).getElementByName(elementName),
                "Не найден элемент " + elementName);
        String innerHTMLText = element.getAttribute("innerHTML");
        if (innerHTMLText.contains("::after")){
        }
        logger.debug(String.format("Пользователь на странице %s удостоверился, что элемент %s активен",pageName,elementName));
    }

    @Дано("Перейти на страницу {string}")
    public void goToPage(String page) throws InterruptedException {
        YandexMainPage yandexMainPage = new YandexMainPage();
        yandexMainPage.goToPage(page);
        logger.info("Осуществлён переход на страницу '"+page+"'");
    }

    @Дано("Получить прогноз погоды на {string}")
    public void getWeather(String day) throws InterruptedException {
        if (day.equalsIgnoreCase("сегодня")){
            logger.info("Запрошена погода на "+day);
            YandexMainPage yandexMainPage = new YandexMainPage();
            yandexMainPage.getCurrentTemp();
            logger.info("Температура получена");
        }
        else {
            logger.info("Запрошена погода на "+day);
            YandexMainPage yandexMainPage = new YandexMainPage();
            yandexMainPage.goToPage("Погода");
            YandexWeatherPage yandexWeatherPage = new YandexWeatherPage();
            yandexWeatherPage.getNextDayTemp(day);
            logger.info("Температура получена");
        }
    }

    @SneakyThrows
    @Дано("дебаг")
    public void debug() {
        pageCatalog.sleep(1000);
       // Thread.sleep(5000);
    }

    /*@Тогда("пользователь открывает страницу Яндекс")
    public void goToYaPage() {
        YandexMainPage yandexMainPage = new YandexMainPage(driver);
        System.out.println("успех?");
    }*/
}
