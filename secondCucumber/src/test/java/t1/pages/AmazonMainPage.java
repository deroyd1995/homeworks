package t1.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import t1.core.BaseElement;

import java.util.Arrays;
import java.util.List;

public class AmazonMainPage extends BasePage{

    @Override
    public String getPageName() {
        return "Главная страница Amazon";
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
    private static final String AMAZONE_HOME_LOC = "//a[@aria-label='Amazon']";
    private static final String AMAZONE_INPUT_FIELD_LOC = "//input[@aria-label='Search']";
    private static final String AMAZONE_SEARCH_BUTTON_LOC = "//input[@id='nav-search-submit-button']";
    private static final String AMAZONE_CATEGORIES_MENU_LOC = "//a[@aria-label='Open Menu']";

    //Элементы
    @FindBy(xpath = AMAZONE_HOME_LOC)
    WebElement amazoneHome;

    @FindBy(xpath = AMAZONE_INPUT_FIELD_LOC)
    WebElement searchInput;

    @FindBy(xpath = AMAZONE_SEARCH_BUTTON_LOC)
    WebElement searchButton;

    @FindBy(xpath = AMAZONE_CATEGORIES_MENU_LOC)
    WebElement mainMenu;

    //METHODS

    private List<BaseElement> elements = Arrays.asList(
            buildElementWithName(amazoneHome, "Amazone главная",AMAZONE_HOME_LOC),
            buildElementWithName(searchInput, "Поле поиска",AMAZONE_INPUT_FIELD_LOC),
            buildElementWithName(searchButton, "Кнопка Поиск",AMAZONE_SEARCH_BUTTON_LOC),
            buildElementWithName(mainMenu, "Главное меню",AMAZONE_CATEGORIES_MENU_LOC)
    );
}
