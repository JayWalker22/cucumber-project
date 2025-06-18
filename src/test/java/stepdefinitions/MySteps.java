package stepdefinitions;

import io.cucumber.java.en.*;
import org.junit.Assert;
import org.openqa.selenium.Keys;
import org.openqa.selenium.interactions.Actions;
import pages.MyPages;
import utilities.Driver;
import utilities.ReusableMethods;

import static utilities.Driver.getDriver;

public class MySteps {
    MyPages page = new MyPages();

    @Given("The user is on the homepage")
    public void the_user_is_on_the_homepage() {
        Driver.getDriver().get("https://www.trendyol.com/");

    }
    @When("The user searches for {string}")
    public void the_user_searches_for(String string) {
        page.cookies.click();
        ReusableMethods.waitForClickablility(page.searchBox,10);
        page.searchBox.sendKeys(string, Keys.ENTER);

    }
    @When("Adds the product to the cart")
    public void adds_the_product_to_the_cart() {
        page.addBasketButton.click();
        ReusableMethods.bekle(3);

    }
    @Then("The product should appear in the cart")
    public void the_product_should_appear_in_the_cart() {
        String expectedText = "Sepetim (1 Ürün)";
        page.myBasketButton.click();
        try {
            ReusableMethods.waitForClickablility(page.alert,10);
            page.alert.click();
        }catch (Exception e){

        }

        ReusableMethods.bekle(4);
        String actualText = page.basketHeadText.getText();
        Assert.assertEquals(expectedText,actualText);

    }
    @When("The user removes the product from the cart")
    public void the_user_removes_the_product_from_the_cart() {
        ReusableMethods.bekle(2);
        page.deleteButton.click();
        ReusableMethods.bekle(2);


    }
    @Then("The product should no longer be in the cart")
    public void the_product_should_no_longer_be_in_the_cart() {
        String expectedText = "Sepetim";
        String actualText = page.basketHeadText.getText();
        Assert.assertEquals(expectedText,actualText);
    }

}
