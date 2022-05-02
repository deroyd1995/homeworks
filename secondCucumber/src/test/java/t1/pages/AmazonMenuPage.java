package t1.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import t1.core.BaseElement;

import java.util.Arrays;
import java.util.List;

public class AmazonMenuPage extends BasePage {
    @Override
    public String getPageName() {
        return "Главное меню Amazon";
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

    //Локаторы
    private static final String AMAZONE_SHOP_ELECTRONICS_LOC = "//div[text()='Electronics']";
    private static final String AMAZONE_ELECTRONICS_RESULT_LOC = "//div[@class='hmenu-item hmenu-title ' and text()='electronics']";
    private static final String AMAZONE_ELECTRONICS_HEADPHONES_LOC = "//a[@class='hmenu-item' and text()='Headphones']";

    //Элементы
    @FindBy(xpath = AMAZONE_SHOP_ELECTRONICS_LOC)
    WebElement departmentElectronics;

    @FindBy(xpath = AMAZONE_ELECTRONICS_RESULT_LOC)
    WebElement electronicsResult;

    @FindBy(xpath = AMAZONE_ELECTRONICS_HEADPHONES_LOC)
    WebElement headphones;

    //METHODS

    private List<BaseElement> elements = Arrays.asList(
            buildElementWithName(departmentElectronics, "Раздел электроники",AMAZONE_SHOP_ELECTRONICS_LOC),
            buildElementWithName(electronicsResult, "Результат поиска: электроника",AMAZONE_ELECTRONICS_RESULT_LOC),
            buildElementWithName(headphones, "Наушники",AMAZONE_ELECTRONICS_HEADPHONES_LOC)
    );
}
