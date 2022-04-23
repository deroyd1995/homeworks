package pages;

import org.openqa.selenium.WebDriver;
import framework.Browser;

public abstract class BasePage2 {
    protected WebDriver webDriver;
    public BasePage2() {
        this.webDriver = Browser.getInstance().getWebDriver();
    }
}
