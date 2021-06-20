package ru.stqa.training.selenium.litecart.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.TestBase;

public class BasketPage extends TestBase {

    public BasketPage open() {
        driver.findElement(By.linkText("Checkout »")).click();
        return this;
    }

    public BasketPage deleteAllProductsFromTheBasket() {
        while (isElementPresent(By.cssSelector("button[name=remove_cart_item]"))) {
            wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("button[name=remove_cart_item]")));
            WebElement table = driver.findElement(By.cssSelector("div#checkout-summary-wrapper tbody"));
            driver.findElement(By.cssSelector("button[name=remove_cart_item]")).click();
            wait.until(ExpectedConditions.stalenessOf(table));
        }
        Assert.assertTrue("Не все товары удалены из корзины", isElementPresent(By.linkText("<< Back")));
        return this;
    }
}
