package ru.stqa.training.selenium.litecart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.TestBase;

import java.util.List;

public class StickersTests extends TestBase {

    @Test
    public void checkProductSticker() {
        driver.get("http://localhost/litecart/en/");
        List<WebElement> productItems = driver.findElements(By.xpath("//ul[contains(@class, 'products')]//li[contains(@class, 'product')]"));
        for (int i = 0; i < productItems.size(); i++) {
            Assert.assertTrue(String.format("У элемента %s отсутствует стикер", productItems.get(i).findElement(By.className("name")).getText()),
                    productItems.get(i).findElements(By.xpath(".//div[contains(@class, 'sticker')]")).size() == 1);
        }
    }
}
