package cn.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

/**
 *
 * 2021年6月24日
 */
public class UpdateStudent {
    
    public static void main(String[] args) {
        
        System.setProperty("webdriver.chrome.driver", "D:\\self\\TestingTech\\selenium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        System.setProperty("webdriver.chrome.whitelistedIps", "192.168.1.3");

         WebDriver driver = new ChromeDriver();

         driver.manage().window().maximize();  
             
         driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);     
        
         driver.get("http://127.0.0.1");

//        WebElement webElement = driver.findElement(By.xpath("/html/body/div[1]/div[1]"));
//        Select select = new Select(webElement);
//        select.selectByIndex(2);

        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/table/tbody/tr[1]/td[6]/div/div/a[1]")).click();

        WebDriver element = driver.switchTo().frame(0);

        element.findElement(By.xpath("/html/body/form/div[2]/div/input")).clear();
        element.findElement(By.xpath("/html/body/form/div[2]/div/input")).sendKeys("weizhuo02");

        element.findElement(By.xpath("/html/body/form/div[3]/div/input")).clear();
        element.findElement(By.xpath("/html/body/form/div[3]/div/input")).sendKeys("24");

        element.findElement(By.xpath("/html/body/form/div[4]/div/input")).clear();
        element.findElement(By.xpath("/html/body/form/div[4]/div/input")).sendKeys("13");

        element.findElement(By.xpath("/html/body/form/div[5]/div/textarea")).clear();
        element.findElement(By.xpath("/html/body/form/div[5]/div/textarea")).sendKeys("aaaaaaaaaaaaaa");
        element.findElement(By.xpath("/html/body/form/div[6]/div/button[2]")).click();

        //关闭并退出浏览器
//         driver.quit();
    }
 
}