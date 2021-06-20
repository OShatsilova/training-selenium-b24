package ru.stqa.training.selenium.litecart.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.stqa.training.selenium.TestBase;

import java.util.List;

public class MainPage extends TestBase {

    String URL = "http://localhost/litecart/en/";
    @FindBy(css = "div#box-most-popular li")
    List<WebElement> mostPopularProducts;

    public MainPage() {
        PageFactory.initElements(driver, this);
    }

    public MainPage open() {
        driver.get(URL);
        return this;
    }

    public ProductPage pickFirstOfMostPopularProducts() {
        mostPopularProducts.get(0).click();
        return new ProductPage();
    }
}
