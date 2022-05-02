package t1.steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import org.openqa.selenium.WebDriver;
import t1.core.PageCatalog;
import t1.framework.Browser;

public class Conditions {
    public static final int DURATION = 15; //время ожидания, сек


    public static WebDriver driver = Browser.getInstance().getWebDriver();
    private static final String baseURL="https://www.amazon.com/";
    public static PageCatalog pageCatalog;

    @Before
    public void setUp() {
        Browser.open(baseURL);
        driver.manage().window().maximize();
        pageCatalog = new PageCatalog();

    }

    @After
    public void tearDown() {
        Browser.quit();
    }
}
