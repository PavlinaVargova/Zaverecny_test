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
    public void rodicSExistujicimUctemSePrihlasiDoAplikace() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/prihlaseni");
        this.prihlaseniRodice();
    }

    public void prihlaseniRodice() {
        WebElement tlacitkoProEmail = prohlizec.findElement(By.id("email"));
        tlacitkoProEmail.sendKeys("info@veritas-learning.cz");
        WebElement tlacitkoHeslo = prohlizec.findElement(By.id("password"));
        tlacitkoHeslo.sendKeys("ABcd123");
        WebElement tlacitkoPrihlasit = prohlizec.findElement(By.xpath("//button[contains(@class,'btn')]"));
        tlacitkoPrihlasit.click();
    }


    public void vyplneniPrihlaskyNaKurzWebu() {

        WebElement poleProVyberTerminu = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/form/table/tbody/tr[2]/td[2]/div/button/div/div/div"));
        poleProVyberTerminu.click();
        WebElement poleProPodvyberTerminu = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/form/table/tbody/tr[2]/td[2]/div/div/div[1]/input"));
        poleProPodvyberTerminu.sendKeys("21.06.");
        poleProPodvyberTerminu.sendKeys("\n");
        WebElement poleProJmenoDitete = prohlizec.findElement(By.id("forename"));
        poleProJmenoDitete.sendKeys("Katka");
        WebElement polePrijmeniDitete = prohlizec.findElement(By.id("surname"));
        polePrijmeniDitete.sendKeys("Vargova");
        WebElement poleDatumNarozeni = prohlizec.findElement(By.id("birthday"));
        poleDatumNarozeni.sendKeys("15.11.2010");
        WebElement platbaHotove = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/form/table/tbody/tr[8]/td[2]/span[4]/label"));
        platbaHotove. click();
        WebElement odsouhlaseniPodminek = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/form/table/tbody/tr[11]/td[2]/span/label"));
        odsouhlaseniPodminek.click();
        WebElement tlacitkoPrihlasitDite = prohlizec.findElement(By.xpath("//input[contains(@class,'btn')]"));
        tlacitkoPrihlasitDite.click();

    }

    @Test
    public void prihlaseniNaTrimesicniKurzyWebuBezPredchozihoPrihlaseniRodice() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");
        WebElement tlacitkoViceInformaciProKurzWeb = prohlizec.findElement(By.xpath("//a[contains(@href, 'https://cz-test-jedna.herokuapp.com/11-trimesicni-kurzy-webu')]"));
        tlacitkoViceInformaciProKurzWeb.click();
        WebElement tlacitkoVytvoritPrihlasku = prohlizec.findElement(By.xpath("//a[contains(@class, 'btn')]"));
        tlacitkoVytvoritPrihlasku.click();

        this.prihlaseniRodice();
        this.vyplneniPrihlaskyNaKurzWebu();

        WebElement tlacitkoStahnoutPrihlasku = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/table/tbody/tr[10]/td[2]/a"));
        Assertions.assertNotNull(tlacitkoStahnoutPrihlasku);

    }

    @Test
    public void prihlaseniNaKurzPoPrihlaseniRodice() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");
        WebElement tlacitkoProPrihlseniRodice = prohlizec.findElement(By.xpath("/html/body/div/header/nav/div/div[2]/a"));
        tlacitkoProPrihlseniRodice.click();
        this.prihlaseniRodice();
        WebElement logoProVstupKNabidceKurzu = prohlizec.findElement(By.className("logo"));
        logoProVstupKNabidceKurzu.click();
        WebElement tlacitkoViceInformaciProKurzWeb = prohlizec.findElement(By.xpath("//a[contains(@href, 'https://cz-test-jedna.herokuapp.com/11-trimesicni-kurzy-webu')]"));
        tlacitkoViceInformaciProKurzWeb.click();
        WebElement tlacitkoVytvoritPrihlasku = prohlizec.findElement(By.xpath("//a[contains(@class, 'btn')]"));
        tlacitkoVytvoritPrihlasku.click();

        this.vyplneniPrihlaskyNaKurzWebu();
        WebElement tlacitkoStahnoutPrihlasku = prohlizec.findElement(By.xpath("/html/body/div/div/div/div/div/table/tbody/tr[10]/td[2]/a"));
        Assertions.assertNotNull(tlacitkoStahnoutPrihlasku);

    }

    @Test
    public void overeniSpravnostiPredvyplnenohoEmailuRodiceVPrihlasce() {
        prohlizec.navigate().to("https://cz-test-jedna.herokuapp.com/");
        WebElement tlacitkoViceInformaciProKurzWeb = prohlizec.findElement(By.xpath("//a[contains(@href, 'https://cz-test-jedna.herokuapp.com/11-trimesicni-kurzy-webu')]"));
        tlacitkoViceInformaciProKurzWeb.click();
        WebElement tlacitkoVytvoritPrihlasku = prohlizec.findElement(By.xpath("//a[contains(@class, 'btn')]"));
        tlacitkoVytvoritPrihlasku.click();
        this.prihlaseniRodice();

        WebElement vyplnenePoleProEmail = prohlizec.findElement(By.id("email"));
        Assertions.assertEquals("info@veritas-learning.cz", vyplnenePoleProEmail.getAttribute("value"));

    }


        @AfterEach
    public void tearDown() {
        prohlizec.close();
    }
}
