package t1.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import t1.framework.Browser;

public class Conditions {

    private static final String baseURL = "https://yandex.ru/";
    public static final int DURATION = 15; //время ожидания, сек
    public static final int SLEEP = 5;     //время сна, сек
    public static WebDriver driver = Browser.getInstance().getWebDriver();

    @Before
    public void setUp(){
        Browser.open(baseURL);
        driver.manage().window().maximize();
    }
    @After
    public void tearDown(){
        Browser.quit();
    }
}
