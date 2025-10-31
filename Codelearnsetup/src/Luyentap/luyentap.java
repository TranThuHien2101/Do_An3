package Luyentap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.Keys;
import java.util.List;
import java.time.Duration;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.ExpectedConditions;
public class luyentap {
	public static void main(String[] args) throws Exception{
		System.setProperty("WebDriver.chrome.driver","D:\\selenium\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		driver.manage().window().maximize();
		driver.navigate().to("https://codelearn.io/");
		System.out.print(driver.getTitle());
		Thread.sleep(2000);
		
		WebElement loginButton = driver.findElement(By.cssSelector("span.mantine-Button-label"));
		if (loginButton.getText().equals("Đăng nhập")) {
		    loginButton.click();
		}
		Thread.sleep(2000);

		WebElement tentaikhoan = driver.findElement(By.id("login-user-name"));
		tentaikhoan.sendKeys("tranthuhien21012004@gmail.com");
		WebElement password = driver.findElement(By.id("login-password"));
		password.sendKeys("Hien21012004");
		Thread.sleep(2000);
		WebElement dangnhap = driver.findElement(
			    By.xpath("//div[@id='login-submit']//button")
			);
			dangnhap.click();

		Thread.sleep(3000);
		// Click nút hủy
		WebElement closeButton = driver.findElement(
			    By.xpath("//button[contains(@class, 'mantine-Modal-close')]")
			);
			closeButton.click();

	    Thread.sleep(2000);
	    // Click nút bỏ qua
	    WebElement skipButton = driver.findElement(By.xpath("//span[text()='Bỏ qua']"));
	    skipButton.findElement(By.xpath("./ancestor::button")).click();
	    Thread.sleep(2000);
	    
	    // Click trang luyện tập
	    WebElement luyenTapLink = driver.findElement(By.xpath("//a[text()='Luyện tập']"));
	    luyenTapLink.click();
	    Thread.sleep(2000);
	   // Tìm kiếm trên thanh công cụ
	    WebElement searchInput = driver.findElement(By.id("filter-mentor"));
	    searchInput.sendKeys("Python");
	    Thread.sleep(2000);
	    //Click trạng thái
	    WebElement statusDropdown = driver.findElement(By.xpath ("//input[@id='mantine-7dhw74gji']"));
	    statusDropdown.click();
	    Thread.sleep(1000);
	    WebElement searchIcon = driver.findElement(By.xpath ("//*[name()='circle' and contains(@cx,'10')]"));
	    searchIcon.click();    	
	}
}


