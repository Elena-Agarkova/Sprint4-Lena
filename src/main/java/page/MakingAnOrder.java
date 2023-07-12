package page;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class MakingAnOrder {
    private final WebDriver driver;
    private final By orderPageHeader = By.xpath(".//div[contains(@class, 'Order_Header')]");
    private final By inputName = By.xpath(".//input[@placeholder ='* Имя']");
    private final By inputSurname  = By.xpath(".//input[@placeholder ='* Фамилия']");
    private final By inputAddress  = By.xpath(".//input[@placeholder ='* Адрес: куда привезти заказ']");
    private final By inputMetroStation  = By.xpath(".//input[@placeholder ='* Станция метро']");
    private final By inputPhoneNumber  = By.xpath(".//input[@placeholder ='* Телефон: на него позвонит курьер']");
    private final By nextButton  = By.xpath(".//div[contains(@class, 'Order_NextButton')]/button[contains(@class, 'Button_Button')]");
    private final By inputDate = By.xpath(".//input[@placeholder ='* Когда привезти самокат']");
    private final By inputDatePicker = By.xpath(".//div[contains(@class, 'react-datepicker__day--today')]");
    private final By inputRentalPeriod = By.className("Dropdown-placeholder");
    private final By inputRentalDay = By.xpath(".//div[@class='Dropdown-menu']/div[text()='сутки']");
    private final By checkboxes = By.xpath(".//div[contains(@class, 'Order_Checkboxes')]//input");
    private final By comment = By.xpath(".//input[@placeholder ='Комментарий для курьера']");
    private final By orderButton = By.xpath("//div[contains(@class, 'Order_Buttons')]//button[contains(text(), 'Заказать')]");
    private final By confirmButton = By.xpath("//button[contains(text(), 'Да')]");
    public final By successText = By.xpath("//div[(text()= 'Заказ оформлен')]");

    public MakingAnOrder(WebDriver driver) {
        this.driver = driver;
    }

    public void waitForLoadOrderPage(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(orderPageHeader));
    }

    public void waitForLoadSuccessText(){
        new WebDriverWait(driver, Duration.ofSeconds(5))
                .until(ExpectedConditions.visibilityOfElementLocated(successText));
    }

    public WebElement getName() {
        return driver.findElement(inputName);
    }

    public WebElement getSurname() {
        return driver.findElement(inputSurname);
    }

    public WebElement getAddress() {
        return driver.findElement(inputAddress);
    }

    public WebElement getPhoneNumber() {
        return driver.findElement(inputPhoneNumber);
    }

    public WebElement getStation() {
        return driver.findElement(inputMetroStation);
    }

    public WebElement getTitleOrder() {
        return driver.findElement(orderPageHeader);
    }

    public void clickNextButton() {
        driver.findElement(nextButton).click();
    }

    public WebElement getInputDate() {
        return driver.findElement(inputDate);
    }

    public WebElement getInputDatePicker() {
        return driver.findElement(inputDatePicker);
    }

    public WebElement getRentalPeriod() {
        return driver.findElement(inputRentalPeriod);
    }

    public WebElement getRentalDay() {
        return driver.findElement(inputRentalDay);
    }

    public void clickCheckboxes() {
        driver.findElements(checkboxes).forEach(checkbox -> checkbox.click());
    }

    public WebElement getComment() {
        return driver.findElement(comment);
    }

    public WebElement getOrderButton() {
        return driver.findElement(orderButton);
    }

    public WebElement getConfirmButton() {
        return driver.findElement(confirmButton);
    }
    public void sendTheFirstForm(String name, String surname, String address, String station, String phoneNumber) {
        getName().sendKeys(name);
        getSurname().sendKeys(surname);
        getAddress().sendKeys(address);
        new Actions(driver).moveToElement(getStation()).click().sendKeys(station)
                .sendKeys(Keys.ARROW_DOWN).sendKeys(Keys.ENTER).build().perform();
        getPhoneNumber().sendKeys(phoneNumber);
    }

    public void sendTheSecondForm(String comment) {
        getInputDate().click();
        getInputDatePicker().click();
        getRentalPeriod().click();
        getRentalDay().click();
        clickCheckboxes();
        getComment().sendKeys(comment);
    }

    public boolean isDisplayedSuccessText() {
        System.out.println(driver.findElement(successText).getText());
        return driver.findElement(successText).isDisplayed();
    }
}