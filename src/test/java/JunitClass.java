import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.time.Duration;
import java.util.List;

public class JunitClass {

    String link = "https://opensource-demo.orangehrmlive.com/web/index.php/auth/login";
    WebDriver driver;
    @Before
    public void setup(){
        System.setProperty("webdriver.gecko.driver", "./src/test/resources/geckodriver.exe");
        driver=new FirefoxDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
    }

    @Test
    public void A_userLogin(){
        driver.get(link);
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("[type=submit]")).click();
        WebElement imageEliment = driver.findElement(By.className("oxd-userdropdown-img"));
        boolean status = imageEliment.isDisplayed();
        Assert.assertEquals(status,true);

    }
    @Test
    public void B_userLoginInvalidUsername(){
        driver.get(link);
        driver.findElement(By.name("username")).sendKeys("salman");
        driver.findElement(By.name("password")).sendKeys("admin123");
        driver.findElement(By.cssSelector("[type=submit]")).click();
        WebElement messages = driver.findElement(By.tagName("p"));
        String messageExpected = messages.getText();
        String messageActual = "Invalid credentials";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    @Test
    public void C_userLoginInvalidPassword(){
        driver.get(link);
        driver.findElement(By.name("username")).sendKeys("Admin");
        driver.findElement(By.name("password")).sendKeys("111");
        driver.findElement(By.cssSelector("[type=submit]")).click();
        WebElement messages = driver.findElement(By.tagName("p"));
        String messageExpected = messages.getText();
        String messageActual = "Invalid credentials";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }
    @Test
    public void D_userLoginEmptyUsernamePassword(){
        driver.get(link);
        driver.findElement(By.name("username")).sendKeys("");
        driver.findElement(By.name("password")).sendKeys("");
        driver.findElement(By.cssSelector("[type=submit]")).click();
        List <WebElement> messages = driver.findElements(By.xpath("//span[contains(text(),\"\")]"));
        String messageExpected1 = messages.get(0).getText();
        String messageExpected2 = messages.get(1).getText();
        String messageActual = "Required";
        Assert.assertTrue(messageActual.contains(messageExpected1));
        Assert.assertTrue(messageActual.contains(messageExpected2));
    }
    @Test
    public void E_userLoginInvalidUsernamePassword(){
        driver.get(link);
        driver.findElement(By.name("username")).sendKeys("admi");
        driver.findElement(By.name("password")).sendKeys("1234");
        driver.findElement(By.cssSelector("[type=submit]")).click();
        WebElement messages = driver.findElement(By.tagName("p"));
        String messageExpected = messages.getText();
        String messageActual = "Invalid credentials";
        Assert.assertTrue(messageActual.contains(messageExpected));
    }

    @After
    public void quit(){
        driver.quit();
    }
}
