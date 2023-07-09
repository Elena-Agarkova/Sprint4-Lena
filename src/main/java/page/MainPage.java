package page;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class MainPage {
    private WebDriver driver;

    public MainPage() {
    }

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    private final By ACCORDION = By.className("accordion");
    private final By ACCORDION_BUTTON = By.className("accordion__button");
    private final By ACCORDION_TEXT = By.cssSelector(".accordion__heading p");
    private final By ORDER_BUTTON_HEADER = By.xpath(".//div[contains(@class, 'Header_Nav')]/button[contains(@class, 'Button_Button')]");
    private final By ORDER_BUTTON_BOTTOM = By.xpath(".//div[contains(@class, 'Home_FinishButton')]/button[contains(@class, 'Button_Button')]");

    public void waitForLoadAccordion() {
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(ACCORDION));
    }

    public void clickAccordionElements() {
        List<WebElement> accordionButtonsElement = driver.findElements(ACCORDION_BUTTON);
        accordionButtonsElement.forEach(button -> {
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", button);
            button.click();
        });
    }

    public List<String> getTextsFromAccordion() {
        List<String> accordionTexts = new ArrayList<>();
        driver.findElements(ACCORDION_TEXT).forEach(text -> accordionTexts.add(text.getText()));
        System.out.println(accordionTexts);
        return accordionTexts;
    }

    public By getOrderButtonHeader() {
        return ORDER_BUTTON_HEADER;
    }

    public By getOrderButtonBottom() {
        return ORDER_BUTTON_BOTTOM;
    }

    public void clickOrderButton(By locator) {
        WebElement orderButtonElement = driver.findElement(locator);
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", orderButtonElement);
        orderButtonElement.click();
    }
}
