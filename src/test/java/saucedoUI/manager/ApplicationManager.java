package saucedoUI.manager;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    WebDriver wd;

    protected void init() {
        wd = new ChromeDriver();
        wd.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
        wd.get("https://www.saucedemo.com/");
    }

    public void login(String username, String password) {
        wd.findElement(By.name("user-name")).click();
        wd.findElement(By.name("user-name")).clear();
        wd.findElement(By.name("user-name")).sendKeys(username);
        wd.findElement(By.name("password")).click();
        wd.findElement(By.name("password")).clear();
        wd.findElement(By.name("password")).sendKeys(password);
        wd.findElement(By.name("login-button")).click();
    }

    protected void stop() {
        wd.quit();
    }

    public boolean isElementPresent(By locator) {
        try {
            wd.findElement(locator);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }


    public void addToCart(int index) {
        wd.findElements(By.xpath("//button[@class='btn btn_primary btn_small btn_inventory']")).get(index).click();
    }

    public void goToCart() {
        wd.findElement(By.xpath("//a[@class='shopping_cart_link']")).click();
    }

    public void removeFromCart() {
        wd.findElement(By.xpath("//button[@name='remove-sauce-labs-backpack']")).click();
    }

    public void productFilterPriceLowToHigh() {
        new Select(wd.findElement(By.xpath("//select[@class='product_sort_container']"))).selectByVisibleText("Price (low to high)");

    }

    public List<Double> createPriceList() {
        List<Double> prices = new ArrayList<>();
        List<WebElement> elements = wd.findElements(By.xpath("//div[@class='inventory_item_price']"));
        for (WebElement element : elements) {
            double price = Double.parseDouble((element.getText().replaceAll("\\$", "")));
            prices.add(price);
        }
        return prices;
    }
}
