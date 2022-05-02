package t1.steps;

import io.cucumber.java.ru.Дано;
import io.cucumber.java.ru.Тогда;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Assertions;
import org.openqa.selenium.WebElement;
import org.slf4j.LoggerFactory;

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


    /*@Дано("Выполняем запрос по теме {string}")
    public void SecondScenarioTest(String theme) {
        YandexMainPage yandexMainPage = new YandexMainPage();
        yandexMainPage.searchThemeWithYandex(theme);
        logger.info("Выполнен запрос по теме {}", theme);
    }*/

    @Дано("пользователь на странице {string} сохраняет текст элемента {string} в переменную контекста {string}")
    public void findCurrentTemperatureTest(String pageName, String elementName, String param) {
        String text = pageCatalog.waitForElementPresent(pageCatalog.getPageByName(pageName).getElementByName(elementName), "Не найден элемент " + elementName).getText();
        CUCUMBER_CONTEXT.put(param, text);
    }

    @Тогда("Сравнить две перменные из контекста 1: {string}, 2: {string}")
    public void compareTwoParamsFromContext(String firstParam, String secondParam) {
        Assertions.assertEquals(CUCUMBER_CONTEXT.get(firstParam), CUCUMBER_CONTEXT.get(secondParam));
    }

    @Тогда("сравнить переменную из контекста: {string} с ожидаемым результатом: {string}")
    public void compareParamFromContextWithExpected(String actualResult, String expectedResult) {
        Assertions.assertEquals(expectedResult, CUCUMBER_CONTEXT.get(actualResult));
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
        pageCatalog.waitForElementAndClick(pageCatalog.getPageByName(pageName).getElementByName(elementName),
                "Не удалось кликнуть на эелемент: " + elementName, 15);
    }

    @Дано("пользователь на странице {string} вводит текст {string} в элемент {string}")
    public void sendKeysValue(String pageName, String text, String elementName) {
        pageCatalog.waitForElementAndSendKeys(pageCatalog.getPageByName(pageName).getElementByName(elementName), text,
                "Не удалось ввести текст " + text + " в эелемент: " + elementName);
    }

    @Дано("пользователь проверяет, что ссылка текущей страницы содержит базовую часть {string} и текст {string}")
    public void checkURL(String basePart, String text) {
        String url = driver.getCurrentUrl();
        Assertions.assertTrue(url.contains(basePart), "url " + url + " does not match " + basePart + " in URL");
        Assertions.assertTrue(url.contains(text),"url" + url + " does not match " + text + " in URL");
    }

    @Дано("пользователь на странице {string} проверяет, что элемент {string} активен")
    public void checkElementAttribute(String pageName, String elementName) {
        WebElement element = pageCatalog.waitForElementExist(pageCatalog.getPageByName(pageName).getElementByName(elementName),
                "Не найден элемент " + elementName);
        String innerHTMLText = element.getAttribute("innerHTML");
        if (innerHTMLText.contains("::after")){
        }
    }

    @SneakyThrows
    @Дано("дебаг")
    public void debug() {
        pageCatalog.sleep(5000);
       // Thread.sleep(5000);
    }

    /*@Тогда("пользователь открывает страницу Яндекс")
    public void goToYaPage() {
        YandexMainPage yandexMainPage = new YandexMainPage(driver);
        System.out.println("успех?");
    }*/
}
