package cn.selenium;
 
import java.util.concurrent.TimeUnit;
 
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
 
/**
 * @author 北京-宏哥
 *
 * 2021年6月24日
 */
public class FirstScript {
    
    public static void main(String[] args) {
        
        System.setProperty("webdriver.chrome.driver", "D:\\self\\TestingTech\\selenium\\chromedriver-win64\\chromedriver-win64\\chromedriver.exe");

        System.setProperty("webdriver.chrome.whitelistedIps", "192.168.1.3");

         WebDriver driver = new ChromeDriver();

         driver.manage().window().maximize();  
             
         driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);     
        
         driver.get("https://www.baidu.com");  //地址栏输入百度地址
         
         //判断title是不是 百度一下，你就知道
         
         try{
             String baidu_title = "百度一下，你就知道";
             System.out.println(driver.getTitle());
             assert baidu_title == driver.getTitle();
             System.out.println("Test Pass");
             
         }catch(Exception e){
             e.printStackTrace();
         }
         
         driver.findElement(By.id("kw")).sendKeys("Selenium");//搜索输入框输入Selenium
       
         driver.findElement(By.id("su")).click();//点击百度一下按钮
         
         //设置隐性等待时间  
         driver.manage().timeouts().implicitlyWait(8, TimeUnit.SECONDS);  
         
         //这里通过元素XPath表达式来确定该元素显示在结果列表，从而判断Selenium官网这个链接显示在结果列表。
         
//         WebElement ele_string=driver.findElement(By.xpath("/html/body/div[3]/div[4]/div[1]/div[3]/div[5]/div/h3/a/div/div/p/span/span"));
         WebElement ele_string=driver.findElement(By.cssSelector("#\\34  > div > h3 > a > div > div > p > span > span > em"));

         String ele_string1 = ele_string.getText();
         
         System.out.println(ele_string1);
         
         try{
         
             if(ele_string1.equals("Selenium automates browsers. That's it!")){
             
                 System.out.println("Testing is successful!");
                
             }
         }catch(Exception e){
             e.printStackTrace();
         }
              
         //关闭并退出浏览器  
         driver.quit();  
    }
 
}