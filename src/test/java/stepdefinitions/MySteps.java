package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import pages.MyPages;
import utilities.Driver;
import utilities.ReusableMethods;

import java.time.Duration;

import static utilities.Driver.getDriver;

public class MySteps {
    MyPages page = new MyPages();

    @Given("The user is on the homepage")
    public void the_user_is_on_the_homepage() {
        Driver.getDriver().get("https://www.trendyol.com/");
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(30));
        wait.until(webDriver -> ((JavascriptExecutor) webDriver)
                .executeScript("return document.readyState").equals("complete"));

    }
    @When("The user searches for {string}")
    public void the_user_searches_for(String string) {
        ReusableMethods.waitForClickablility(page.cookies,30);
        page.cookies.click();
        ReusableMethods.waitForClickablility(page.searchBox,30);
        page.searchBox.sendKeys(string, Keys.ENTER);

    }
    @When("Adds the product to the cart")
    public void adds_the_product_to_the_cart() {

        page.addBasketButton.click();
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOf(page.newBasket));

    }
    @Then("The product should appear in the cart")
    public void the_product_should_appear_in_the_cart() {
        String expectedText = "Sepetim (1 Ürün)";
        ReusableMethods.clickByJavaScript(page.myBasketButton);
        try {
            ReusableMethods.waitForClickablility(page.alert,30);
            page.alert.click();
        }catch (Exception e){

        }

        ReusableMethods.visibleWait(page.basketHeadText,30);
        String actualText = page.basketHeadText.getText();
        Assert.assertEquals(expectedText,actualText);

    }
    @When("The user removes the product from the cart")
    public void the_user_removes_the_product_from_the_cart() {
        ReusableMethods.waitForClickablility(page.deleteButton,30);
        page.deleteButton.click();



    }
    @Then("The product should no longer be in the cart")
    public void the_product_should_no_longer_be_in_the_cart() {
        ReusableMethods.visibleWait(page.basketHeadText,30);
        String expectedText = "Sepetim";
        String actualText = page.basketHeadText.getText();
        Assert.assertEquals(expectedText,actualText);
    }

}
