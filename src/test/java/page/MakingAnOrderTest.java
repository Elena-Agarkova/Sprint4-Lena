package page;
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
    private final String browserName;
    private final String name;
    private final String surname;
    private final String address;
    private final String phoneNumber;
    private final String station;
    private final String comment;
    private final By clickOrderButton;

    public MakingAnOrderTest(String browserName, String name, String surname, String address, String phoneNumber, String station, String comment, By clickOrderButton) {
        this.browserName = browserName;
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
                {"Chrome", "Марина", "Иванова", "г. Москва, ул. Профсоюзная, д. 24", "89165352035", "Калужская", "Хочу самокат", mainPage.getOrderButtonHeader()},
                {"Chrome", "Хасан", "Хасанович", "г. Москва, ул. Новочеремушкинская, д. 41", "+79178964586", "Профсоюзная", "Жду заказ как можно быстрее", mainPage.getOrderButtonBottom()},
                {"Firefox","Марина", "Иванова", "г. Москва, ул. Профсоюзная, д. 24", "89165352035", "Калужская", "Хочу самокат",  mainPage.getOrderButtonHeader()},
                {"Firefox","Хасан", "Хасанович",  "г. Москва, ул. Новочеремушкинская, д. 41", "+79178964586", "Профсоюзная", "Жду заказ как можно быстрее", mainPage.getOrderButtonBottom()},
        };
    }

    @Before
    public void setUp() {
        switch (browserName) {
            case "Chrome":
                driver = new ChromeDriver();
                driver.get("https://qa-scooter.praktikum-services.ru/");
                break;
            case "Firefox":
                driver = new FirefoxDriver();
                driver.get("https://qa-scooter.praktikum-services.ru/");
                break;
        }
    }

    @Test
    public void checkOrderProcess() {
        MainPage mainPage = new MainPage(driver);
        mainPage.clickOrderButton(clickOrderButton);

        MakingAnOrder makingAnOrder = new MakingAnOrder(driver);
        makingAnOrder.waitForLoadOrderPage();
        makingAnOrder.sendTheFirstForm(driver, name, surname, address, station, phoneNumber);
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