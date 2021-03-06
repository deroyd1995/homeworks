package t1.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import t1.core.BaseElement;

import java.util.Arrays;
import java.util.List;

public class AmazonSearchInPage extends BasePage {
    @Override
    public String getPageName() {
        return "Результаты поиска Amazon";
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

    //ЛОКАТОРЫ

    private static final String AMAZONE_SEARCHIN_LOC = "//span[@id='nav-search-label-id']";
    private static final String BRANDS_COLLECTION_LOC = "//ul[@aria-labelledby='p_89-title']";
    private static final String BRANDS_COLLECTION_ITEM_LOC = "//ul[@aria-labelledby='p_89-title']/li";
    private static final String ITEMS_COLLECTION_LOC = "//div[@class='s-main-slot s-result-list s-search-results sg-row']";
    private static final String COLLECTION_ITEM_LOC = "//div[@data-component-type='s-search-result']";


    @FindBy(xpath = AMAZONE_SEARCHIN_LOC)
    WebElement searchIn;

    @FindBy(xpath = BRANDS_COLLECTION_LOC)
    WebElement brandsCollection;

    @FindBy(xpath = BRANDS_COLLECTION_ITEM_LOC)
    WebElement brandsItem;

    @FindBy(xpath = ITEMS_COLLECTION_LOC)
    WebElement itemsCollection;

    @FindBy(xpath = COLLECTION_ITEM_LOC)
    WebElement collectionItem;

    private List<BaseElement> elements = Arrays.asList(
            buildElementWithName(searchIn, "Поиск по разделу:",AMAZONE_SEARCHIN_LOC),
            buildElementWithName(brandsCollection, "Коллекция производителей",BRANDS_COLLECTION_LOC),
            buildElementWithName(brandsItem, "Один из брендов",BRANDS_COLLECTION_ITEM_LOC),
            buildElementWithName(itemsCollection, "Коллекция предметов",ITEMS_COLLECTION_LOC),
            buildElementWithName(collectionItem, "Предмет из коллекции",COLLECTION_ITEM_LOC)
    );

}
