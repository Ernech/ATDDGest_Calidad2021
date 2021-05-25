import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.sql.Driver;
import java.util.concurrent.TimeUnit;

public class DzoneRegistroTest {

    private WebDriver driver;

    @BeforeTest
    public void setDriver() throws Exception{
        String path = "/Gestión de calidad/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
    }

    @Test
    public void verificarMensajeDeErrorAlRegistrar(){
        String dzoneUrl= "https://dzone.com";
        driver.get(dzoneUrl);
        WebElement joinLink = driver.findElement(By.xpath("//*[@id=\"ng-app\"]/body/div[2]/div/div/div[1]/div/div[2]/div[2]/a[2]"));
        String linkText = joinLink.getText();
        System.out.println(linkText);
        joinLink.click();

        try{
            TimeUnit.SECONDS.sleep(5);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        WebElement joinButton = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div[2]/div[3]/button"));
        joinButton.click();
        try{
            TimeUnit.SECONDS.sleep(5);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }

        WebElement iconoAlerta = driver.findElement(By.xpath("//*[@id=\"login\"]/div/div[2]/div[1]/div[2]/form/div[1]/span[2]/i"));
        Assert.assertEquals(iconoAlerta.isDisplayed(),true);

        WebElement emailErrorTextMessage = driver.findElement(By.xpath("//div[@data-validate=\"Please enter a valid email address\"]"));
        Assert.assertEquals(emailErrorTextMessage.getAttribute("data-validate"),"Please enter a valid email address");
    }

    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
}
