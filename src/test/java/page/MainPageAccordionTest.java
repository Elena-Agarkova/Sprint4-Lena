package page;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(Parameterized.class)
public class MainPageAccordionTest {

    private WebDriver driver;
    private final String browserName;
    private final int element;
    private final String text;

    public MainPageAccordionTest(String browserName,int element, String text) {
        this.browserName = browserName;
        this.element = element;
        this.text = text;
    }

    @Parameterized.Parameters()
    public static Object[][] getData() {
        return new Object[][]{
                {"Chrome"},
                {"Firefox"},
                {"Сутки — 400 рублей. Оплата курьеру — наличными или картой."},
                {"Пока что у нас так: один заказ — один самокат. Если хотите покататься с друзьями, можете просто сделать несколько заказов — один за другим."},
                {"Допустим, вы оформляете заказ на 8 мая. Мы привозим самокат 8 мая в течение дня. Отсчёт времени аренды начинается с момента, когда вы оплатите заказ курьеру. Если мы привезли самокат 8 мая в 20:30, суточная аренда закончится 9 мая в 20:30."},
                {"Только начиная с завтрашнего дня. Но скоро станем расторопнее."},
                {"Пока что нет! Но если что-то срочное — всегда можно позвонить в поддержку по красивому номеру 1010."},
                {"Самокат приезжает к вам с полной зарядкой. Этого хватает на восемь суток — даже если будете кататься без передышек и во сне. Зарядка не понадобится."},
                {"Да, пока самокат не привезли. Штрафа не будет, объяснительной записки тоже не попросим. Все же свои."},
                {"Да, обязательно. Всем самокатов! И Москве, и Московской области."},
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
    public void checkAccordionTextAfterClickExists() {
        MainPage mainPage = new MainPage(driver);
        mainPage.waitForLoadAccordion();
        mainPage.clickAccordionElements(element - 1);

        assertThat("Элемент выпадающего списка не соответствует тексту", mainPage.getTextFromAccordion(element - 1), containsString(text));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}