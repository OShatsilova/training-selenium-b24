package ru.stqa.training.selenium;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.openqa.selenium.support.ui.ExpectedConditions.titleIs;

public class MyFirstTest {

    WebDriver driver;
    WebDriverWait wait;
    String email = "Alta.Kuhic@gmail.com";

    @Before
    public void start() {
        driver = new ChromeDriver();
        wait = new WebDriverWait(driver, 10);
    }

    @Test
    public void myFirstTest() {
        driver.get("http://users.bugred.ru/");
        wait.until(titleIs("Users"));
        driver.findElement(By.name("q")).sendKeys(email);
        driver.findElement(By.xpath("//button[text()='Найти']")).click();
        Assert.assertEquals(driver.findElement(By.xpath("//tbody[@class='ajax_load_row']/tr/td")).getText(), email);
    }

    @After
    public void stop() {
        driver.quit();
    }
}
