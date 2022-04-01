package tests;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import pages.YandexMainPage;
import pages.YandexMarketPage;
import pages.YandexWeatherPage;
import ru.yandex.qatools.ashot.AShot;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.coordinates.WebDriverCoordsProvider;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class SeleniumTests extends BaseTest {

    //метод для создания скриншота по xpath элемента, сохраняет с именем name и по пути
    public void takeScreenShotPNG(String xpath, String name) throws IOException{
        WebElement logo = webDriver.findElement(By.xpath(xpath));

        Screenshot screenshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000))
                .coordsProvider(new WebDriverCoordsProvider())
                .takeScreenshot(webDriver, logo);

        ImageIO.write(screenshot.getImage(), "png", new File("src\\test\\resources\\"+name));
    }



    @Test
    @DisplayName("Find the best motivators")
    @Tag("Motivators")
    public void findBestMotivatorTest() {
        String TEXT_TO_FIELD="лучшие мотиваторы";
        YandexMainPage yandexMainPage = new YandexMainPage(webDriver);
        yandexMainPage.searchInYandex(TEXT_TO_FIELD);
    }

    @Test
    @DisplayName("Get today temperature")
    @Tag("Weather")
    public void findCurrentTemperatureTest(){
        YandexMainPage yandexMainPage = new YandexMainPage(webDriver);
        yandexMainPage.getCurrentTemp();
    }

    @Test
    @DisplayName("Get tomorrow temperature")
    @Tag("Weather")
    public void findTomorrowTemperatureTest() throws InterruptedException, IOException {
        YandexMainPage yandexMainPage = new YandexMainPage(webDriver);
        yandexMainPage.goToPageTomorrowForecast();
        YandexWeatherPage yandexWeatherPage = new YandexWeatherPage(webDriver);
        yandexWeatherPage.getTomorrowTemp();
        //takeScreenShotPNG(yandexWeatherPage.TOMORROW_TEMPERATURE_CROPP,"tomorrow_temp.png");
    }

    @Test
    @Order(1)
    @DisplayName("Check if market tab exists")
    @Tag("SweetForCat")
    public void checkMarketExists() throws InterruptedException,IOException{
        YandexMainPage yandexMainPage = new YandexMainPage(webDriver);
        yandexMainPage.ifMarketExists();
        //takeScreenShotPNG(yandexMainPage.MARKET,"market.png");
    }

    @Test
    @Order(2)
    @DisplayName("Switch to market tab")
    @Tag("SweetForCat")
    public void followMarketLink() throws InterruptedException{
        YandexMainPage yandexMainPage = new YandexMainPage(webDriver);
        yandexMainPage.followToMarket();
    }

    @Test
    @Order(3)
    @DisplayName("Open catalogue")
    @Tag("SweetForCat")
    public void openCatalogue() throws InterruptedException{
        YandexMarketPage yandexMarketPage = new YandexMarketPage(webDriver);
        yandexMarketPage.followToMarket();
        yandexMarketPage.openCatalogue();
    }

    @Test
    @Order(4)
    @DisplayName("Find and move cursor to zootovari")
    @Tag("SweetForCat")
    public void moveCursorToZoo() throws InterruptedException{
        YandexMarketPage yandexMarketPage = new YandexMarketPage(webDriver);
        yandexMarketPage.followToMarket();
        yandexMarketPage.openCatalogue();
        yandexMarketPage.moveCursorToZooSup();
    }

    @Test
    @Order(5)
    @DisplayName("Click on sweets for cat")
    @Tag("SweetForCat")
    public void followSweetsForCat() throws InterruptedException{
        YandexMarketPage yandexMarketPage = new YandexMarketPage(webDriver);
        yandexMarketPage.followToMarket();
        yandexMarketPage.openCatalogue();
        yandexMarketPage.moveCursorToZooSup();
        yandexMarketPage.clickOnSweetsForCat();
    }

    @ParameterizedTest
    @CsvSource({"Whiskas,50,150,Доставка курьером", "Sheba,40,110,Любой", "Titbit,33,142,Самовывоз"})
    @Order(6)
    @DisplayName("Setting up filters")
    @Tag("SweetForCat")
    public void setUpFilters(String manufacturer,int priceFromValue,int priceToValue,String deliveryMethod)
            throws InterruptedException, IOException {

        YandexMarketPage yandexMarketPage = new YandexMarketPage(webDriver);
        yandexMarketPage.followToMarket();
        yandexMarketPage.openCatalogue();
        yandexMarketPage.moveCursorToZooSup();
        yandexMarketPage.clickOnSweetsForCat();

        yandexMarketPage.filterPrice(priceFromValue,priceToValue);
        //takeScreenShotPNG(yandexMarketPage.PRICE_SET_TAB,"priceset.png");

        yandexMarketPage.filterDeliveryMethod(deliveryMethod);
        //takeScreenShotPNG(yandexMarketPage.DELIVERY_METHOD_TAB,"deliverymethod.png");

        yandexMarketPage.filterByManufacturer(manufacturer);
        //takeScreenShotPNG(yandexMarketPage.MANUFACTURERS_TAB,"soloview_"+manufacturer+".png");

        yandexMarketPage.clickOnItem(1);
        yandexMarketPage.addItemToComparison();
        yandexMarketPage.goToCompareList();
        //takeScreenShotPNG(yandexMarketPage.PRODUCTS_COMPARISON,manufacturer+"in_result.png");
    }



}
