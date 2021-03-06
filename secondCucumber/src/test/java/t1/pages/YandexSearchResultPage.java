package t1.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import t1.core.BaseElement;

import java.util.Arrays;
import java.util.List;

public class YandexSearchResultPage extends BasePage {
    @Override
    public String getPageName() {
        return "Результаты поиска Яндекс";
    }

    @Override
    public WebElement getElementByName(String elementName) {
        return elements.stream()
                .filter(element -> element.getElementName().equals(elementName))
                .findFirst()
                .orElseThrow(() -> new Error("На странице не найден элемент для наименования: " + elementName)).getWebElement();
    }

    @Override
    public String getElementXpath(String elementName) {
        return elements.stream()
                .filter(element -> element.getElementName().equals(elementName))
                .findFirst()
                .orElseThrow(() -> new Error("На странице не найден элемент для наименования: " + elementName)).getElementXpath();
    }

    @FindBy(xpath = "//div[@class='service service_name_search']/a/span")
    WebElement searchTab;

    private List<BaseElement> elements = Arrays.asList(
            buildElementWithName(searchTab, "Поиск","//div[@class='service service_name_search']/a/span")
    );
}
