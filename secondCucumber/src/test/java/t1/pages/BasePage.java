package t1.pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.LoggerFactory;
import t1.core.BaseElement;
import t1.framework.Browser;
import t1.steps.CurrentSteps;

import java.time.Duration;

import static junit.framework.TestCase.fail;

public abstract class BasePage {

    private static final ch.qos.logback.classic.Logger logger
            = (ch.qos.logback.classic.Logger) LoggerFactory.getLogger(CurrentSteps.class);

    protected WebDriver webDriver;

    public abstract String getPageName();

    public abstract WebElement getElementByName(String elementName);

    public BasePage() {
        this.webDriver = Browser.getInstance().getWebDriver();
        PageFactory.initElements(webDriver, this);
    }

    public boolean waitForElementNotPresent(WebElement element, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        try {
            wait.until(ExpectedConditions.visibilityOf((element)));
            return false;
        } catch (Exception tex) {
            tex.printStackTrace();
            return true;
        }
    }

    public WebElement waitForElementPresent(WebElement locator, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(webDriver, Duration.ofSeconds(timeoutInSeconds));
        wait.withMessage(errorMessage + "\n");
        try {
            wait.until(ExpectedConditions.visibilityOf((locator)));
        } catch (Exception tex) {
            tex.printStackTrace();
            fail(errorMessage);
        }
        return locator;
    }

    public WebElement waitForElementExist(WebElement locator, String errorMessage) {
        try {
            exist(locator);
        } catch (Exception tex) {
            tex.printStackTrace();
            fail(errorMessage);
        }
        return locator;
    }

    public WebElement waitForElementPresent(WebElement element, String errorMessage) {
        return waitForElementPresent(element, errorMessage, 5);
    }

    public void waitForElementAndClick(WebElement locator, String errorMessage, long timeoutInSeconds) {
        int numberOfRetry = 0;
        boolean successfulClick = false;
        do {
            try {
                WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
                element.click();
                successfulClick = true;
            } catch (StaleElementReferenceException se) {
                numberOfRetry++;
                logger.info(String.format("Failed to click on element %s due to the StaleElementReferenceException", locator));
                if (numberOfRetry == 3) {
                    se.printStackTrace();
                    fail(String.format("Cannot click on element %s due to the StaleElementReferenceException", locator));
                }
            }
        } while (!successfulClick);
    }

    public void waitForElementAndClick(WebElement locator, String errorMessage) {
        int numberOfRetry = 0;
        boolean successfulClick = false;
        do {
            try {
                WebElement element = waitForElementPresent(locator, errorMessage, 5);
                element.click();
                successfulClick = true;
            } catch (StaleElementReferenceException se) {
                numberOfRetry++;
                logger.info(String.format("Failed to click on element %s due to the StaleElementReferenceException", locator));
                if (numberOfRetry == 3) {
                    se.printStackTrace();
                    fail(String.format("Cannot click on element %s due to the StaleElementReferenceException", locator));
                }
            }
        } while (!successfulClick);
    }

    public void sleep(int milliSeconds) {
        try {
            Thread.sleep(milliSeconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public boolean exist(WebElement element) {
        int j = 0;
        boolean webElementPassed = false;
        do {
            sleep(1000);
            try {
                element.isDisplayed();
                webElementPassed = true;
            } catch (TimeoutException | NoSuchElementException e) {
                e.printStackTrace();
                logger.info(String.format("Элемент: %s не найден", element));
                return false;
            } catch (StaleElementReferenceException ex) {
                logger.info("Ошибка при поиске элемента " + element);
                j += 1;
                if (j == 5) {
                    ex.printStackTrace();
                    logger.info(String.format("Элемент: %s принял другое состояние", element));
                    Assert.fail("Элемент принял другое состояние");
                    return false;
                }
            }
        }
        while (!webElementPassed);
        return true;
    }

    public WebElement waitForElementAndSendKeys(WebElement locator, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresent(locator, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public WebElement waitForElementAndSendKeys(WebElement locator, String value, String errorMessage) {
        WebElement element = waitForElementPresent(locator, errorMessage, 5);
        element.sendKeys(value);
        return element;
    }

    protected BaseElement buildElementWithName (WebElement element, String name){
        return BaseElement.builder()
                .webElement(element)
                .elementName(name)
                .build();
    }
}
