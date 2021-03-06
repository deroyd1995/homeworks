package t1.core;

import lombok.Builder;
import org.openqa.selenium.WebElement;

@Builder
public class BaseElement {

    /**
     * Наименование элемента используемого в сценарии
     */
    private String elementName;

    /**
     * element, найденный по локатору
     */
    private WebElement webElement;


    /**
     * xpath элемента
     */
    private String xpath;

    public String getElementName() {
        return elementName;
    }

    public WebElement getWebElement() {
        return webElement;
    }

    public String getElementXpath(){return xpath;}
}
