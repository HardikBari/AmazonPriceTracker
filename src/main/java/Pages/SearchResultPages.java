package Pages;

import AbstractComponent.waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class SearchResultPages extends waits {
    WebDriver driver;

    public SearchResultPages(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); //very important step without this webelement will not be initialized

    }

    @FindBy(id = "nav-cart-count")
    WebElement cartcount;
    @FindBy(css = ".a-price-whole")
    List<WebElement> price;
    @FindBy(css = "div[data-component-type='s-search-result']")
    List<WebElement> productCards;

    By cartbtn = By.xpath(".//button[text()='Add to cart']");
    By priceofproduct = By.cssSelector(".a-price-whole");

    public WebElement getpriceofproduct(String name) throws InterruptedException {
        WebElement prod = productCards.stream().filter(product -> product.findElement(By.cssSelector("h2 span")).getText().contains(name)).findFirst().orElse(null);
        if (prod == null) {
            Assert.fail(" Product with name not found.");
        } else {
            System.out.println("Product found: " + prod.findElement(By.cssSelector("h2 span")).getText());
        }
        return prod;
    }

    public void addToCart(String name) throws InterruptedException {
        WebElement PROD = getpriceofproduct(name);
        PROD.findElement(cartbtn).click();
    }

    public String getPrice(String name) throws InterruptedException {
        WebElement PROD = getpriceofproduct(name);
        String price = PROD.findElement(priceofproduct).getText();
        return price;
    }


    public String itemAddedornot() {
        waitForElementToAppear(cartcount);
        String count = cartcount.getText();
        return count;

    }
}