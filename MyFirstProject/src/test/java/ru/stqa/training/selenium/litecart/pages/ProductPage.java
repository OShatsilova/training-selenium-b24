package ru.stqa.training.selenium.litecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.TestBase;

public class ProductPage extends TestBase {

    public ProductPage addProductIntoTheBacket() {
        WebElement productBacketcountBefor = driver.findElement(By.cssSelector("div#cart-wrapper span.quantity"));
        int count = Integer.parseInt(productBacketcountBefor.getText());
        if (isElementPresent(By.cssSelector("td.options select"))) {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            Select optionsSize = new Select(driver.findElement(By.xpath("//select[@name='options[Size]']")));
            js.executeScript("arguments[0].selectedIndex=1; arguments[0].dispatchEvent(new Event('change'))", optionsSize);
        }
        driver.findElement(By.cssSelector("button[name=add_cart_product]")).click();
        WebElement productBacketcountAfter = driver.findElement(By.cssSelector("div#cart-wrapper span.quantity"));
        wait.until(ExpectedConditions.textToBePresentInElement(productBacketcountAfter, String.valueOf(count + 1)));
        return this;
    }
}
