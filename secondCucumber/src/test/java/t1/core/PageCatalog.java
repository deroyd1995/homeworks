package t1.core;

import org.openqa.selenium.WebElement;
import t1.pages.BasePage;
import t1.pages.SearchResultPage;
import t1.pages.YandexMainPage;
import t1.pages.YandexWeatherPage;

import java.util.Arrays;
import java.util.List;

public class PageCatalog extends BasePage {


    public final List<BasePage> pageCatalog = Arrays.asList(
            new YandexMainPage(),
            new YandexWeatherPage(),
            new SearchResultPage()
    );

    public BasePage getPageByName(String pageName) {
            for (BasePage page : pageCatalog) {
                if (page.getPageName().equals(pageName)) {
                    return page;
                }
            }
        throw new Error("Не заполнен каталог страниц.");

//        return pageCatalog.stream()
//                .filter(page -> page.getPageName().equals(pageName))
//                .findFirst()
//                .orElseThrow(() -> new Error("Не найдена страница с наименованием: " + pageName));
    }

    @Override
    public String getPageName() {
        return null;
    }

    @Override
    public WebElement getElementByName(String elementName) {
        return null;
    }
}
