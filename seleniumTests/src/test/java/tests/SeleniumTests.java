package tests;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class SeleniumTests extends BaseTest {

    @Test
    @DisplayName("Find the best motivators")
    @Tag("Motivators")
    public void findBestMotivatorTest() {
        Assertions.assertTrue(webDriver.findElement(By.xpath(YANDEX_LOGO)).isDisplayed());
        webDriver.findElement(By.xpath(INPUT_FIELD)).sendKeys(TEXT_TO_FIELD);
        webDriver.findElement(By.xpath(FIND_BUTTON)).click();
        new WebDriverWait(webDriver, Duration.ofSeconds(DURATION))
                .until(ExpectedConditions.elementToBeClickable(By.xpath(RESULT_LIST)));
        System.out.println("Motivators found successfully");
    }


}
