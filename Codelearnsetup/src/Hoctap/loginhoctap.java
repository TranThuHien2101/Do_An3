package Hoctap;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class loginhoctap  {
	public static void main(String[] args) throws Exception {
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
	
    // Trang Học tập
    WebElement hocTapLink = driver.findElement(
    	    By.xpath("//a[text()='Học tập']")
    	);
    hocTapLink.click();
    Thread.sleep(2000);
    // tìm kiếm trên thanh công cụ 
    WebElement timkiem = driver.findElement(By.id("search-course"));
	timkiem.sendKeys("Python");
	Thread.sleep(2000);
	WebElement searchIcon = driver.findElement(
			    By.xpath("//div[contains(@class, 'TextInput-rightSection')]/svg[contains(@class, 'icon-tabler-search')]")
			);
			searchIcon.click();

    Thread.sleep(3000);
	driver.quit();
	}
}
