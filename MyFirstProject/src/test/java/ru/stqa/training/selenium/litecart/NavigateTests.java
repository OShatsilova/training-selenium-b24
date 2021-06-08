package ru.stqa.training.selenium.litecart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import ru.stqa.training.selenium.TestBase;

import java.util.List;
import java.util.Set;

public class NavigateTests extends TestBase {

    String menuItemLocator = "//li[@id='app-'][%s]";
    String subMenuItemLocator = menuItemLocator.concat("//li[%s]");

    @Test
    public void checkMenuItems() {
        new AutorizationTests().logInAsAmin();
        List<WebElement> menuItems = driver.findElements(By.cssSelector("li#app-"));
        for (int i = 1; i < menuItems.size() + 1; i++) {
            driver.findElement(By.xpath(String.format(menuItemLocator, i))).click();
            List<WebElement> subMenuItems = driver.findElement(By.xpath(String.format(menuItemLocator, i))).findElements(By.cssSelector("li"));
            if (subMenuItems.size() > 0) {
                for (int j = 1; j < subMenuItems.size() + 1; j++) {
                    driver.findElement(By.xpath(String.format(subMenuItemLocator, i, j))).click();
                    Assert.assertTrue(String.format("На странице %s заголовок отсутствует",
                            driver.findElement(By.xpath(String.format(subMenuItemLocator, i, j))).getText()),
                            driver.findElements(By.cssSelector("h1")).size() > 0);
                }
            } else
                Assert.assertTrue(String.format("На странице %s заголовок отсутствует",
                        driver.findElement(By.xpath(String.format(menuItemLocator, i))).getText()),
                        driver.findElements(By.cssSelector("h1")).size() > 0);
        }
    }

    @Test
    public void checkLinksInNewWindow() {
        new AutorizationTests().logInAsAmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
        driver.findElement(By.xpath("//a[@title='Edit']")).click();
        List<WebElement> links = driver.findElements(By.cssSelector("form[enctype='multipart/form-data'] a[target='_blank']"));
        String currWinHandle = driver.getWindowHandle();
        for (WebElement link: links) {
            link.click();
            wait.until(ExpectedConditions.numberOfWindowsToBe(2));
            Set<String> windows = driver.getWindowHandles();
            windows.remove(currWinHandle);
            String newHandle = windows.toString();
            newHandle =newHandle.substring(1,newHandle.length()-1);
            driver.switchTo().window(newHandle);
            driver.close();
            driver.switchTo().window(currWinHandle);
        }
    }
}
