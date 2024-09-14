package cn.selenium;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 *
 * 2021年6月24日
 */
public class DelStudent {
    
    public static void main(String[] args) {
        
        System.setProperty("webdriver.chrome.driver", "D:\\self\\TestingTech\\selenium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        System.setProperty("webdriver.chrome.whitelistedIps", "192.168.1.3");

         WebDriver driver = new ChromeDriver();

         driver.manage().window().maximize();  
             
         driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);     
        
         driver.get("http://127.0.0.1");

        driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/table/tbody/tr[1]/td[6]/div/div/a[2]")).click();

        WebElement alert = driver.switchTo().activeElement();
//        alert.accept();
        alert.findElement(By.xpath("//*[@id=\"layui-layer2\"]/div[4]/a[1]")).click();

        //关闭并退出浏览器
         driver.quit();  
    }
 
}