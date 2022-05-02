package t1.core;

import org.openqa.selenium.WebElement;
import t1.pages.*;

import java.util.Arrays;
import java.util.List;

public class PageCatalog extends BasePage {

    public final List<BasePage> pageCatalog = Arrays.asList(
           /*
           //YANDEX PAGES
            new YandexMainPage(),
            new YandexWeatherPage(),
            new YandexSearchResultPage()
            */

            //AMAZON PAGES
            new AmazonMainPage(),
            new AmazonMenuPage(),
            new AmazonSearchInPage()

    );

    public BasePage getPageByName(String pageName) {
            for (BasePage page : pageCatalog) {
                if (page.getPageName().equals(pageName)) {
                    return page;
                }
            }
        logger.error("Не заполнен каталог страниц.");
        throw new Error("Не заполнен каталог страниц.");
    }

    @Override
    public String getPageName() {return null;}

    @Override
    public WebElement getElementByName(String elementName) {
        return null;
    }

    @Override
    public String getElementXpath(String elementName) {return null;}
}
