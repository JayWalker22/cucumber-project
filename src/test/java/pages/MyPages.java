package pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class MyPages {
    public MyPages (){
        PageFactory.initElements(Driver.getDriver(),this);
    }
    @FindBy (css = "input[placeholder='Aradığınız ürün, kategori veya markayı yazınız']")
    public WebElement searchBox;
    @FindBy (xpath = "(//button[@class='add-to-basket-button'])[1]")
    public WebElement addBasketButton;
    @FindBy (xpath = "(//p[@class='link-text'])[2]")
    public WebElement myBasketButton;
    @FindBy (css = "div[class='pb-header']")
    public WebElement basketHeadText;
    @FindBy (xpath = "//span[text()='Sil']")
    public WebElement deleteButton;
    @FindBy (css = "button[id='onetrust-reject-all-handler']")
    public WebElement cookies;
    @FindBy (xpath = "//div[@class='tooltip-content']//child::button")
    public WebElement alert;
    @FindBy (css = "div[class='go-to-basket-text']")
    public WebElement newBasket;
}
