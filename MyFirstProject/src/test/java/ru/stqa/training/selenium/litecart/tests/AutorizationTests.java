package ru.stqa.training.selenium.litecart.tests;

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

    public void logInAsUser(String username, String password) {
        driver.findElement(By.name("email")).sendKeys(username);
        driver.findElement(By.name("password")).sendKeys(password);
        driver.findElement(By.name("login")).click();
        Assert.assertTrue("Авторизоваться не удалось", driver.findElement(By.xpath("//h3[@class='title' and text()='Account']")).isDisplayed());
    }

    public void logOut(){
        driver.findElement(By.xpath("//a[contains(@href, 'logout')]")).click();
        Assert.assertTrue("Выйти из приложения не удалось", driver.findElement(By.xpath("//h3[@class='title' and text()='Login']")).isDisplayed());
    }

}
