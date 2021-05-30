package cz.czechitas.selenium;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

public class TestyPrihlasovaniNaKurzy {

    WebDriver prohlizec;

    @BeforeEach
    public void setUp() {
//      System.setProperty("webdriver.gecko.driver", System.getProperty("user.home") + "/Java-Training/Selenium/geckodriver");
        System.setProperty("webdriver.gecko.driver", "C:\\Java-Training\\Selenium\\geckodriver.exe");
        prohlizec = new FirefoxDriver();
        prohlizec.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
    }

    @Test
    public void prohlizecMusiOtevritStrankuCzechitas() {
        prohlizec.navigate().to("https://www.czechitas.cz/cs/");

        Assertions.assertNotNull("https://www.czechitas.cz/cs/");
    }

    @Test
    public void googleMusiVyhledatCzechitas() {
        prohlizec.navigate().to("https://www.google.com/");
        WebElement souhlasimSCookies = prohlizec.findElement(By.xpath("/html/body/div[2]/div[2]/div[3]/span/div/div/div[3]/button[2]/div"));
        souhlasimSCookies.click();

        WebElement vyhledavaciPole = prohlizec.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[1]/div/div[2]/input"));
        vyhledavaciPole.sendKeys("Czechitas");
        WebElement poleHledatGooglem = prohlizec.findElement(By.xpath("/html/body/div[1]/div[3]/form/div[1]/div[1]/div[3]/center/input[1]"));
        poleHledatGooglem.click();
        WebElement prvniOdkazCzechias = prohlizec.findElement(By.xpath("/html/body/div[7]/div/div[9]/div[1]/div/div[1]/div[3]/div/div/div/div/div[1]/a/div[1]"));
        prvniOdkazCzechias.click();

        Assertions.assertNotNull("https://www.czechitas.cz/cs/");
    }

    @AfterEach
    public void tearDown() {
       prohlizec.close();
    }
}
