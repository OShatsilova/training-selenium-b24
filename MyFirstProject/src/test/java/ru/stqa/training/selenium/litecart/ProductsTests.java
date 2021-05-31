package ru.stqa.training.selenium.litecart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.TestBase;

public class ProductsTests extends TestBase {

    String productFromMainPageLocator = "div#box-campaigns ul li";
    String regularPriceValueLocator = "s.regular-price";
    String campaignPriceValueLocator = "strong.campaign-price";
    String productFromProductPageLocator = "div#box-product";


    @Test
    public void checkProduct_A() {
        driver.get("http://localhost/litecart/en/");
        WebElement productFromMainPage = driver.findElement(By.cssSelector(productFromMainPageLocator));
        String productTitleFromMainPage = productFromMainPage.findElement(By.cssSelector("div.name")).getText();
        productFromMainPage.click();
        WebElement productFromProductPage = driver.findElement(By.cssSelector(productFromProductPageLocator));
        String productTtitleFromProductPage = productFromProductPage.findElement(By.cssSelector("h1")).getText();
        Assert.assertTrue(String.format("На главной странице название: %s\nА на странице продкта: %s\n", productTitleFromMainPage, productTtitleFromProductPage),
                productTitleFromMainPage.equals(productTtitleFromProductPage));
    }

    @Test
    public void checkProduct_B() {
        driver.get("http://localhost/litecart/en/");
        WebElement productFromMainPage = driver.findElement(By.cssSelector(productFromMainPageLocator));
        String regularPriceValueFromMainPage = productFromMainPage.findElement(By.cssSelector(regularPriceValueLocator)).getText();
        String campaignPriceValueFromMainPage = productFromMainPage.findElement(By.cssSelector(campaignPriceValueLocator)).getText();
        productFromMainPage.click();
        WebElement productFromProductPage = driver.findElement(By.cssSelector(productFromProductPageLocator));
        String regularPriceValueFromProductPage = productFromProductPage.findElement(By.cssSelector(regularPriceValueLocator)).getText();
        String campaignPriceValueFromProductPage = productFromProductPage.findElement(By.cssSelector(campaignPriceValueLocator)).getText();
        Assert.assertTrue(String.format("На главной странице название: %s\nА на странице продкта: %s\n", regularPriceValueFromMainPage, regularPriceValueFromProductPage),
                regularPriceValueFromMainPage.equals(regularPriceValueFromProductPage));
        Assert.assertTrue(String.format("На главной странице название: %s\nА на странице продкта: %s\n", campaignPriceValueFromMainPage, campaignPriceValueFromProductPage),
                campaignPriceValueFromMainPage.equals(campaignPriceValueFromProductPage));
    }

    @Test
    public void checkProduct_V() {
        driver.get("http://localhost/litecart/en/");
        WebElement productFromMainPage = driver.findElement(By.cssSelector(productFromMainPageLocator));
        WebElement regularPriceFromMainPage = productFromMainPage.findElement(By.cssSelector(regularPriceValueLocator));
        checkElementlineTthrough(regularPriceFromMainPage);
        checkElementRGB(regularPriceFromMainPage);
        productFromMainPage.click();
        WebElement productFromProductPage = driver.findElement(By.cssSelector(productFromProductPageLocator));
        WebElement regularPriceFromProductPage = productFromProductPage.findElement(By.cssSelector(regularPriceValueLocator));
        checkElementlineTthrough(regularPriceFromProductPage);
        checkElementRGB(regularPriceFromProductPage);
    }

    @Test
    public void checkProduct_G() {
        driver.get("http://localhost/litecart/en/");
        WebElement productFromMainPage = driver.findElement(By.cssSelector(productFromMainPageLocator));
        WebElement campaignPriceFromMainPage = productFromMainPage.findElement(By.cssSelector(campaignPriceValueLocator));
        checkElemenеtTextRedColor(campaignPriceFromMainPage);
        checkElementFontBold(campaignPriceFromMainPage);
        productFromMainPage.click();
        WebElement productFromProductPage = driver.findElement(By.cssSelector(productFromProductPageLocator));
        WebElement regularPriceFromProductPage = productFromProductPage.findElement(By.cssSelector(campaignPriceValueLocator));
        checkElemenеtTextRedColor(regularPriceFromProductPage);
        checkElementFontBold(regularPriceFromProductPage);
    }

    @Test
    public void checkProduct_D() {
        driver.get("http://localhost/litecart/en/");
        WebElement productFromMainPage = driver.findElement(By.cssSelector(productFromMainPageLocator));
        WebElement regularPriceFromMainPage = productFromMainPage.findElement(By.cssSelector(regularPriceValueLocator));
        WebElement campaignPriceFromMainPage = productFromMainPage.findElement(By.cssSelector(campaignPriceValueLocator));
        checkElementsFontSize(regularPriceFromMainPage, campaignPriceFromMainPage);
        productFromMainPage.click();
        WebElement productFromProductPage = driver.findElement(By.cssSelector(productFromProductPageLocator));
        WebElement regularPriceFromProductPage = productFromProductPage.findElement(By.cssSelector(regularPriceValueLocator));
        WebElement campaignPriceFromProductPage = productFromProductPage.findElement(By.cssSelector(campaignPriceValueLocator));
        checkElementsFontSize(regularPriceFromProductPage, campaignPriceFromProductPage);
    }

    private void checkElementRGB(WebElement element) {
        String regularPriceColor = element.getCssValue("color");
        regularPriceColor = regularPriceColor.replace("rgba(", "").replace(")", "");
        String color_R = regularPriceColor.substring(0, regularPriceColor.indexOf(","));
        regularPriceColor = regularPriceColor.substring(regularPriceColor.indexOf(",") + 2);
        String color_G = regularPriceColor.substring(0, regularPriceColor.indexOf(","));
        regularPriceColor = regularPriceColor.substring(regularPriceColor.indexOf(",") + 2);
        String color_B = regularPriceColor.substring(0, regularPriceColor.indexOf(","));
        regularPriceColor = regularPriceColor.substring(regularPriceColor.indexOf(",") + 2);
        String alfa = regularPriceColor;
        Assert.assertTrue(String.format("На странице %s regular-price продукта не серая", driver.getCurrentUrl()),
                color_R.equals(color_G));
        Assert.assertTrue(String.format("На странице %s regular-price продукта не серая", driver.getCurrentUrl()),
                color_G.equals(color_B));
        Assert.assertFalse(String.format("На странице %s regular-price продукта не серая", driver.getCurrentUrl()),
                alfa.equals(100));
    }

    private void checkElementlineTthrough(WebElement element) {
        Assert.assertTrue(String.format("На странице %s regular-price продукта не перечёркнута", driver.getCurrentUrl()),
                element.getCssValue("text-decoration-line").equals("line-through"));
    }

    private void checkElemenеtTextRedColor(WebElement element) {
        String regularPriceColor = element.getCssValue("color");
        regularPriceColor = regularPriceColor.replace("rgba(", "").replace(")", "");
        String color_R = regularPriceColor.substring(0, regularPriceColor.indexOf(","));
        regularPriceColor = regularPriceColor.substring(regularPriceColor.indexOf(",") + 2);
        String color_G = regularPriceColor.substring(0, regularPriceColor.indexOf(","));
        regularPriceColor = regularPriceColor.substring(regularPriceColor.indexOf(",") + 2);
        String color_B = regularPriceColor.substring(0, regularPriceColor.indexOf(","));
        regularPriceColor = regularPriceColor.substring(regularPriceColor.indexOf(",") + 2);
        String alfa = regularPriceColor;
        Assert.assertTrue(String.format("На странице %s regular-price продукта не красная", driver.getCurrentUrl()),
                color_G.equals("0"));
        Assert.assertTrue(String.format("На странице %s regular-price продукта не красная", driver.getCurrentUrl()),
                color_B.equals("0"));
        Assert.assertFalse(String.format("На странице %s regular-price продукта не красная", driver.getCurrentUrl()),
                alfa.equals(100));
    }

    private void checkElementFontBold(WebElement element) {
        Assert.assertTrue(String.format("На странице %s шрифт текста элемента не выделен жирным", driver.getCurrentUrl()),
                element.getCssValue("font-weight").equals("700"));
    }

    private void checkElementsFontSize(WebElement regularPriceElement, WebElement campaignPriceElement) {
        float regularPriceFontSize = Float.valueOf(regularPriceElement.getCssValue("font-size").replace("px", ""));
        float campaignPriceFontSize = Float.valueOf(campaignPriceElement.getCssValue("font-size").replace("px", ""));
        Assert.assertTrue(String.format("На странице %s ошибка сравнения размеров шрифтов", driver.getCurrentUrl()),
                regularPriceFontSize < campaignPriceFontSize);
    }
}
