package cn.selenium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 *
 * 2021年6月24日
 */
public class AddStudent {
    
    public static void main(String[] args) {
        
        System.setProperty("webdriver.chrome.driver", "D:\\self\\TestingTech\\selenium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        System.setProperty("webdriver.chrome.whitelistedIps", "192.168.1.3");

         WebDriver driver = new ChromeDriver();

         driver.manage().window().maximize();  
             
         driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);     
        
         driver.get("http://127.0.0.1");


//        driver.findElement(By.linkText("新增")).click();
        //*[@id="addButton"]
        driver.findElement(By.xpath("//*[@id=\"addButton\"]")).click();

        // Handles加s表示复数,所有的意思,handle是句柄的意思
//        Object[] windows = driver.getWindowHandles().toArray();
//        driver.switchTo().window(windows[1].toString());
//		Thread.sleep(2000);

//        # 通过id获取iframe
//                iframe = driver.find_element_by_id("iframe_id")
//        driver.switch_to.frame(iframe)

//        WebElement element = driver.switchTo().activeElement();
//        element.findElement(By.xpath("/html/body/form/div[2]/div/input")).sendKeys("weizhuo01");
//        element.findElement(By.xpath("/html/body/form/div[3]/div/input")).sendKeys("23");
//        element.findElement(By.xpath("/html/body/form/div[4]/div/input")).sendKeys("12");
//        element.findElement(By.xpath("/html/body/form/div[5]/div/textarea")).sendKeys("DFSAFDSAFDSAFSAFDSA");
//        element.findElement(By.xpath("/html/body/form/div[6]/div/button[2]")).click();

        WebDriver element = driver.switchTo().frame(0);
        element.findElement(By.xpath("/html/body/form/div[2]/div/input")).sendKeys("weizhuo01");
        element.findElement(By.xpath("/html/body/form/div[3]/div/input")).sendKeys("23");
        element.findElement(By.xpath("/html/body/form/div[4]/div/input")).sendKeys("12");
        element.findElement(By.xpath("/html/body/form/div[5]/div/textarea")).sendKeys("DFSAFDSAFDSAFSAFDSA");
        element.findElement(By.xpath("/html/body/form/div[6]/div/button[2]")).click();

         //关闭并退出浏览器
         driver.quit();  
    }
 
}