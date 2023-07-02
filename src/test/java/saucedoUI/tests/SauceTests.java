package saucedoUI.tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import saucedoUI.manager.BaseTests;

import java.util.Collections;
import java.util.List;

public class SauceTests extends BaseTests {
    @Test
    public void standardUserAuthorization() {
        app.login("standard_user", "secret_sauce");
        Assert.assertTrue(app.isElementPresent(By.xpath("//span[@class='title']")));
    }

    @Test
    public void blockUserAuthorization() {
        app.login("locked_out_user", "secret_sauce");
        Assert.assertTrue(app.isElementPresent(By.xpath("//h3[@data-test='error']")));
    }

    @Test
    public void addToCart() {
        app.login("standard_user", "secret_sauce");
        app.addToCart(0);
        app.goToCart();
        Assert.assertTrue(app.isElementPresent(By.xpath("//div[@class='cart_quantity']")));
    }

    @Test
    public void removeFromCart() {
        app.login("standard_user", "secret_sauce");
        app.addToCart(0);
        app.goToCart();
        app.removeFromCart();
        Assert.assertFalse(app.isElementPresent(By.xpath("//div[@class='cart_quantity']")));
    }

    @Test
    public void removeFromCart2() {
        app.login("standard_user", "secret_sauce");
        app.addToCart(0);
        app.removeFromCart();
        Assert.assertFalse(app.isElementPresent(By.xpath("//span[@class='shopping_cart_badge']")));
    }

    @Test
    public void removeFromCartProblemUser() {
        app.login("problem_user", "secret_sauce");
        app.addToCart(0);
        app.removeFromCart();
        Assert.assertFalse(app.isElementPresent(By.xpath("//span[@class='shopping_cart_badge']")));
    }

    @Test
    public void productFilterByPriceProblemUser() {
        app.login("problem_user", "secret_sauce");
        List<Double> before = app.createPriceList();
        app.productFilterPriceLowToHigh();
        List<Double> after = app.createPriceList();

        Collections.sort(before);
        Assert.assertEquals(before, after);
    }
}
