package page;

import constant.Env;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.Assert.assertTrue;
@RunWith(Parameterized.class)
public class MakingAnOrderTest {

    private WebDriver driver;
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String station;
    private final String comment;
    private final By clickOrderButton;

    public MakingAnOrderTest( String name, String surname, String address, String phoneNumber, String station, String comment, By clickOrderButton) {
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.phoneNumber = phoneNumber;
        this.station = station;
        this.comment = comment;
        this.clickOrderButton = clickOrderButton;
    }


    @Parameterized.Parameters
    public static Object[][] getData() {
        MainPage mainPage = new MainPage();
        return new Object[][]{
                {"Марина", "Иванова", "г. Москва, ул. Профсоюзная, д. 24", "89165352035", "Калужская", "Хочу самокат", mainPage.getOrderButtonHeader()},
                {"Хасан", "Хасанович", "г. Москва, ул. Новочеремушкинская, д. 41", "+79178964586", "Профсоюзная", "Жду заказ как можно быстрее", mainPage.getOrderButtonBottom()},
                {"Марина", "Иванова", "г. Москва, ул. Профсоюзная, д. 24", "89165352035", "Калужская", "Хочу самокат",  mainPage.getOrderButtonHeader()},
                {"Хасан", "Хасанович", "г. Москва, ул. Новочеремушкинская, д. 41", "+79178964586", "Профсоюзная", "Жду заказ как можно быстрее", mainPage.getOrderButtonBottom()},
        };
    }

    @Before
    public void setUp() {
        switch (Env.BROWSER_NAME) {
            case "Firefox":
                driver = new FirefoxDriver();
                driver.get(Env.URL);
                break;
            case "Chrome":
                driver = new ChromeDriver();
                driver.get(Env.URL);
                break;
        }
    }

    @Test
    public void checkOrderProcess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButton(clickOrderButton);

        MakingAnOrder makingAnOrder = new MakingAnOrder(driver);
        makingAnOrder.waitForLoadOrderPage();
        makingAnOrder.sendTheFirstForm(name, surname, address, station, phoneNumber);
        makingAnOrder.clickNextButton();
        makingAnOrder.sendTheSecondForm(comment);
        makingAnOrder.getOrderButton().click();
        makingAnOrder.getConfirmButton().click();
        makingAnOrder.waitForLoadSuccessText();

        assertTrue("Сообщение об успешном заказе отсутствует", makingAnOrder.isDisplayedSuccessText());
    }

    @After
    public void teardown() {
        driver.quit();
    }
}