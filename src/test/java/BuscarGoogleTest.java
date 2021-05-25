import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.concurrent.TimeUnit;


public class BuscarGoogleTest {

    private WebDriver driver;

    @BeforeTest
    public void setDriver() throws Exception{
        String path = "/Gestión de calidad/chromedriver.exe";
        System.setProperty("webdriver.chrome.driver",path);
        driver = new ChromeDriver();
    }
    @Test
    public void paginaPrincipalGoogle(){
        String googleUrl= "https://www.google.com";
        driver.get(googleUrl);
        //1 Capturar el campo de búsqueda
        WebElement campoBusqueda = driver.findElement(By.name("q"));
        //2. Criterio de búsqueda
        campoBusqueda.sendKeys("PlayStation 5");
        //3. Mandar Petición
        campoBusqueda.submit();
        try{
            TimeUnit.SECONDS.sleep(5);
        }
        catch(InterruptedException e){
            e.printStackTrace();
        }
        WebElement resultado = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div/div[1]/a/h3"));
        String label = resultado.getText();
        System.out.println(label);
        Assert.assertEquals(label,"PlayStation®5 | Play Has No Limits | PlayStation");
    }

    @AfterTest
    public void closeDriver() throws Exception{
        driver.quit();
    }
}
