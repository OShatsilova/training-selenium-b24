package ru.stqa.training.selenium.litecart.tests;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import ru.stqa.training.selenium.TestBase;
import ru.stqa.training.selenium.litecart.tests.AutorizationTests;

import java.util.ArrayList;
import java.util.List;

public class CountriesTests extends TestBase {

    @Test
    public void checkCountriesSorted() {
        new AutorizationTests().logInAsAmin();
        driver.get("http://localhost/litecart/admin/?app=countries&doc=countries");
//        a)
        List<WebElement> countriesItems = driver.findElements(By.xpath("//form[@name='countries_form']//tr[@class='row']/td[5]/a"));
        checkListSorted(countriesItems);
//        b)
        List<WebElement> countriesRows = driver.findElements(By.xpath("//form[@name='countries_form']//tr[@class='row']"));
        List<String> countriesWithZones = new ArrayList<>();
        for (WebElement row : countriesRows) {
            if (!row.findElement(By.xpath("./td[6]")).getText().equals("0"))
                countriesWithZones.add(row.findElement(By.xpath("./td[5]/a")).getText());
        }
        for (String country : countriesWithZones) {
            driver.findElement(By.xpath(String.format("//a[text()='%s']", country))).click();
            List<WebElement> zonesItems = driver.findElements(By.cssSelector("input[name$='][name]']"));
            checkListSorted(zonesItems);
            driver.navigate().back();
        }
    }

    @Test
    public void checkZonesSorted() {
        new AutorizationTests().logInAsAmin();
        driver.get("http://localhost/litecart/admin/?app=geo_zones&doc=geo_zones");
        List<WebElement> countriesRows = driver.findElements(By.xpath("//form[@name='geo_zones_form']//tr[@class='row']"));
        List<String> countriesWithZones = new ArrayList<>();
        for (WebElement row : countriesRows) {
                countriesWithZones.add(row.findElement(By.xpath("./td[3]/a")).getText());
        }
        for (String country : countriesWithZones) {
            driver.findElement(By.xpath(String.format("//a[text()='%s']", country))).click();
            List<WebElement> zonesItems = driver.findElements(By.xpath("//select[contains(@name, '][zone_code]')]/option[@selected='selected']"));
            checkListSorted(zonesItems);
            driver.navigate().back();
        }
    }

    private void checkListSorted(List<WebElement> elementsList) {
//        фактический список значений
        List<String> curList = new ArrayList<>();
        elementsList.forEach(e -> curList.add(e.getText()));

//        сортированный список значений
        List<String> sortedList = new ArrayList<>();
        curList.stream().sorted().forEach(title -> sortedList.add(title));

        Assert.assertTrue(String.format("Список не сортирован в алфавитном порядке.\n" +
                "Ожидаемое значение:\n%s\n" +
                "Фактическое значение:\n%s\n", sortedList, curList), sortedList.equals(curList));
    }

}

