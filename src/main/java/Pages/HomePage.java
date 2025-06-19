package Pages;

import AbstractComponent.waits;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class HomePage extends waits {

    WebDriver driver;
    public HomePage(WebDriver driver){
       super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this); //very important step without this webelement will not be initialized

    }

    @FindBy (id="twotabsearchtextbox")
    WebElement searchBox;
    @FindBy (className="s-suggestion-container")
    List<WebElement> suggestions;

    By searchList = By.cssSelector(".s-suggestion-container");


    public List<WebElement> getsearchlist() throws InterruptedException {
        waitForElementToAppear(searchList);
        return suggestions;
    }

    public void searchProductbyList(String productName,String prodmodel) throws InterruptedException {
        searchBox.sendKeys(productName);
        Thread.sleep(3000);
        WebElement realproduct=getsearchlist().stream().
              filter(prod->prod.findElement(By.cssSelector("span.s-heavy"))
                      .getText().equalsIgnoreCase(prodmodel)).findFirst().orElse(null);

        if(realproduct!=null){
            realproduct.click();
        }

    }



}