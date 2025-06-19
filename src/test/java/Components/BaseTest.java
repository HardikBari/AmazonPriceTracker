package Components;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;

public class BaseTest {
    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();
    Properties prop;

    public WebDriver getDriver() {
        return driver.get();
    }
       @BeforeMethod
    public void setup(){
           WebDriverManager.chromedriver().setup();
//           ChromeOptions options = new ChromeOptions();
//           options.addArguments("--headless=new"); // new headless mode is better
//           options.addArguments("--window-size=1920,1080");
           driver.set(new ChromeDriver());
           getDriver().manage().window().maximize();

       }
    @AfterMethod
    public void tearDown() {
        getDriver().quit();   // Close the browser for current thread
        driver.remove();      // Remove driver from ThreadLocal to avoid memory leaks
    }

    public List<HashMap<String, String>> getjsondata() throws IOException {

        String jsoncontent= FileUtils.readFileToString(new File(System.getProperty("user.dir") + "//src//test//resources//itemData.json"), "UTF-8");
        //System.out.println(jsoncontent);
        ObjectMapper mapper= new ObjectMapper();
        List<HashMap<String,String>> data = mapper.readValue(jsoncontent, new TypeReference<List<HashMap<String, String>>>() {});
        return data;

    }

       public void configdata() throws IOException {

           InputStream input = getClass().getClassLoader().getResourceAsStream("config.properties");
           if (input == null) {
               throw new IOException("config.properties file not found in classpath");
           }
           prop = new Properties();
            prop.load(input);
         // FileInputStream fis = new FileInputStream(user.dir+"\Amazon\src\test\java\resoruces\config.properties");
       }

       public String geturl() throws IOException {
           configdata();
           String url = prop.getProperty("baseurl");
          // System.out.println(url);
           return url;
       }
       }

