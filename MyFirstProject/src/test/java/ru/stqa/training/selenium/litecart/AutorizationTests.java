package ru.stqa.training.selenium.litecart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import ru.stqa.training.selenium.TestBase;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class AutorizationTests extends TestBase {

    String username = "admin";
    String password = "admin";

    @Test
    public void logInAsAmin() {
        driver.get("http://localhost/litecart/admin/");
        wait.until(titleIs("My Store"));
        driver.findElement(By.name("username")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        Assert.assertTrue(driver.findElement(By.xpath("//img[@title='My Store']")).isDisplayed());
    }

}
