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

    public MainPageAccordionTest(String browserName) {
        this.browserName = browserName;
    }

    @Parameterized.Parameters
    public static Object[][] getData() {
        return new Object[][]{
                {"Chrome"},
                {"Firefox"},
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
        mainPage.clickAccordionElements();

        assertThat("Элемент выпадающего списка пустой", mainPage.getTextsFromAccordion(), everyItem(not(containsString(""))));
    }

    @After
    public void teardown() {
        driver.quit();
    }
}