package t1.steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.After;
import io.cucumber.java.Before;

import static com.codeborne.selenide.Selenide.closeWebDriver;
import static com.codeborne.selenide.Selenide.open;

public class Conditions {

    @Before
    public void setUp(){
        Configuration.baseUrl = "https://yandex.ru";
        Configuration.timeout = 5000;
        Configuration.browserSize = "1920x1080";
        open("");
    }
    @After
    public void tearDown(){
        closeWebDriver();
    }
}
