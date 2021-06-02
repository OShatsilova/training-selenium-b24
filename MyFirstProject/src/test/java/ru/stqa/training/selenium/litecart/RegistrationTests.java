package ru.stqa.training.selenium.litecart;

import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import ru.stqa.training.selenium.TestBase;
import java.util.Random;

public class RegistrationTests extends TestBase {

    final Random random = new Random();

    @Test
    public void newUserRegistration() {

        AutorizationTests autorizationTests = new AutorizationTests();

        String userName = "User_".concat(String.valueOf(random.nextInt()));
        String lastname = "Shatilova";
        String userPassword = "123";
        String address1 = "Test address";
        String postcode = "12345";
        String city = "Washington";
        String countryCode = "United States";
        String phone = "+375291111111";
        String emailPart = "@gmail.com";

        driver.get("https://litecart.stqa.ru/ru/");
        driver.findElement(By.linkText("New customers click here")).click();
        WebElement customerForm = driver.findElement(By.cssSelector("form[name=customer_form]"));
        while (customerForm.findElement(By.cssSelector("input[name=firstname]")).getAttribute("value").equals(""))
        customerForm.findElement(By.cssSelector("input[name=firstname]")).sendKeys(userName);
        customerForm.findElement(By.cssSelector("input[name=lastname]")).sendKeys(lastname);
        customerForm.findElement(By.cssSelector("input[name=address1]")).sendKeys(address1);
        customerForm.findElement(By.cssSelector("input[name=postcode]")).sendKeys(postcode);
        customerForm.findElement(By.cssSelector("input[name=city]")).sendKeys(city);
        Select selectCountry = new Select(customerForm.findElement(By.cssSelector("select[name=country_code]")));
        selectCountry.selectByVisibleText(countryCode);
        Select selectZone = new Select(customerForm.findElement(By.cssSelector("select[name=zone_code]")));
        JavascriptExecutor js =(JavascriptExecutor)driver;;
        js.executeScript("arguments[0].selectedIndex=3; arguments[0].dispatchEvent(new Event('change'))", selectZone);
        customerForm.findElement(By.cssSelector("input[name=email]")).sendKeys(userName.concat(emailPart));
        customerForm.findElement(By.cssSelector("input[name=phone]")).sendKeys((phone));
        customerForm.findElement(By.cssSelector("input[name=password]")).sendKeys(userPassword);
        customerForm.findElement(By.cssSelector("input[name=confirmed_password]")).sendKeys(userPassword);
        customerForm.findElement(By.cssSelector("button[name=create_account]")).click();

        Assert.assertTrue("Зарегистрировать нового пользователя не удалось", driver.findElement(By.xpath("//div[.=' Your customer account has been created.']")).isDisplayed());

        autorizationTests.logOut();
        autorizationTests.logInAsUser(userName.concat(emailPart), userPassword);
        autorizationTests.logOut();
    }
}
