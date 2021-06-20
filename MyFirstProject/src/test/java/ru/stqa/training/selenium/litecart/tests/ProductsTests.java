package ru.stqa.training.selenium.litecart.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.TestBase;
import ru.stqa.training.selenium.litecart.pages.BasketPage;
import ru.stqa.training.selenium.litecart.pages.MainPage;

import java.io.File;
import java.util.Random;

public class ProductsTests extends TestBase {

    AutorizationTests autorizationTests = new AutorizationTests();
    final Random random = new Random();

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

    @Test
    public void addNewProduct() {
        autorizationTests.logInAsAmin();
        driver.findElement(By.xpath("//a[contains(@href, 'catalog')]")).click();
        driver.findElement(By.xpath("//a[contains(@href,'edit_product')]")).click();
//        заполнение данных на вкладке General
        driver.findElement(By.xpath("//a[.='General']")).click();
        driver.findElement(By.xpath("//input[@name='status']")).click();
        String unicNum = String.valueOf(random.nextInt());
        String newProductName = "duck".concat(unicNum);
        driver.findElement(By.xpath("//input[@name='name[en]']")).sendKeys(newProductName);
        driver.findElement(By.xpath("//input[@name='code']")).sendKeys(unicNum);
        driver.findElement(By.xpath("//input[@name='categories[]' and @value='1']")).click();
        driver.findElement(By.xpath("//td[.='Female']/..//input")).click();
        driver.findElement(By.xpath("//input[@name='quantity']")).clear();
        driver.findElement(By.xpath("//input[@name='quantity']")).sendKeys("5,00");
        driver.findElement(By.xpath("//input[@name='new_images[]']")).sendKeys(new File("./src/test/resources/images.png").getAbsolutePath());
        driver.findElement(By.xpath("//input[@name='date_valid_from']")).sendKeys("01062021");
        driver.findElement(By.xpath("//input[@name='date_valid_to']")).sendKeys("01062022");
//        заполнение данных на вкладке Information
        driver.findElement(By.xpath("//a[.='Information']")).click();
        new Select(driver.findElement(By.cssSelector("select[name=manufacturer_id]"))).selectByValue("1");
        driver.findElement(By.xpath("//input[@name='keywords']")).sendKeys("keywords");
        driver.findElement(By.xpath("//input[@name='short_description[en]']")).sendKeys("short description");
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).click();
        driver.findElement(By.xpath("//div[@class='trumbowyg-editor']")).sendKeys("description");
        driver.findElement(By.xpath("//input[@name='head_title[en]']")).sendKeys("head title");
        driver.findElement(By.xpath("//input[@name='meta_description[en]']")).sendKeys("meta description");
//        заполнение данных на вкладке Prices
        driver.findElement(By.xpath("//a[.='Prices']")).click();
        driver.findElement(By.xpath("//input[@name='purchase_price']")).clear();
        driver.findElement(By.xpath("//input[@name='purchase_price']")).sendKeys("55");
        new Select(driver.findElement(By.cssSelector("select[name=purchase_price_currency_code]"))).selectByValue("USD");
        driver.findElement(By.xpath("//input[@name='prices[USD]']")).sendKeys("555");
        driver.findElement(By.xpath("//h2[.='Campaigns']/..//i")).click();
        driver.findElement(By.xpath("//input[contains(@name, 'start_date')]")).sendKeys("27092013" + Keys.TAB + "0245PM");
        driver.findElement(By.xpath("//input[contains(@name, 'end_date')]")).sendKeys("28092013" + Keys.TAB + "0245PM");
        driver.findElement(By.xpath("//input[contains(@name, 'percentage')]")).sendKeys("5");
        driver.findElement(By.xpath("//button[@name='save']")).click();

        Assert.assertTrue(String.format("В каталоге отсутствует новый продукт: %s", newProductName),
                driver.findElement(By.xpath(String.format("//table[@class='dataTable']//tr//a[.='%s']", newProductName))).isDisplayed());
    }

    @Test
    public void addProductsIntoBasket() {
        for (int i = 1; i < 4; i++)
            new MainPage().open().pickFirstOfMostPopularProducts().addProductIntoTheBacket();
        new BasketPage().open().deleteAllProductsFromTheBasket();
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
