package TestCases;

import Components.BaseTest;
import Datareader.datareader;
import Pages.HomePage;
import Pages.SearchResultPages;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class PriceTracker extends BaseTest {


    @Test(dataProvider = "jsonData", dataProviderClass = datareader.class)

    public void trackPrice(HashMap<String, String> input) throws IOException, InterruptedException {
        GoTo(geturl());
        List<WebElement> continuebtn = getDriver().findElements(By.xpath("//button[text()='Continue shopping']"));
        if (!continuebtn.isEmpty()) {
            continuebtn.get(0).click();
        } else {
            System.out.println("Continue shopping ,button not found.");
        }
        HomePage hp = new HomePage(getDriver());
        hp.searchProductbyList(input.get("item"), input.get("model"));
        JavascriptExecutor js = (JavascriptExecutor) getDriver();
        js.executeScript("window.scrollBy(0, 200);"); // Scroll down by 200 pixels
        SearchResultPages srp = new SearchResultPages(getDriver());
        String namemodel = input.get("searchModel");
        String priceText = srp.getPrice(namemodel); // e.g., "46,990"
        priceText = priceText.replace(",", "");
        int itemprice = Integer.parseInt(priceText);
        int targetprice = Integer.parseInt(input.get("target_price"));
        if (itemprice <= targetprice) {
            srp.addToCart(namemodel);
            String itemadded = srp.itemAddedornot();
            Assert.assertEquals(itemadded, "1");
            System.out.println("price of "+input.get("item")+" is "+ itemprice +" added to cart successfully.");
        } else {
             System.out.println("Price of " + input.get("item")+" is "+ itemprice+" greater,we will buy " + input.get("item")+" next time.");
        }
    }
}