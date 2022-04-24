package t1.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import t1.framework.Browser;

public class Conditions {

    public static final Logger logger = LoggerFactory.getLogger(Conditions.class);
    private static final String baseURL = "https://yandex.ru/";
    public static final int DURATION = 15; //время ожидания, сек
    public static final int SLEEP = 5;     //время сна, сек
    public WebDriver driver = Browser.getInstance().getWebDriver();

    @Before
    public void setUp(){
        logger.debug("Открываем заданный baseURL");
        Browser.open(baseURL);
        logger.debug("Масштабируем окно");
        driver.manage().window().maximize();
    }
    @After
    public void tearDown(){
        logger.debug("Вызываем метод закрытия браузера");
        Browser.quit();
    }
}
