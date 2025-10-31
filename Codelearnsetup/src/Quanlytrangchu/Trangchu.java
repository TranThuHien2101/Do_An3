package Quanlytrangchu;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
public class Trangchu {

	public static void main(String[] args) throws Exception{
		
		System.setProperty("WebDriver.chrome.driver","D:\\selenium\\chromedriver.exe");
		WebDriver driver =new ChromeDriver();
		//1-maximize browser của mình
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
		WebElement dangnhap = driver.findElement( By.xpath("//div[@id='login-submit']//button"));
			dangnhap.click();
		Thread.sleep(3000);
		// Click nút hủy
		WebElement closeButton = driver.findElement(By.xpath("//button[contains(@class, 'mantine-Modal-close')]")	);
			closeButton.click();
        Thread.sleep(2000);
        // Click nút bỏ qua
        WebElement skipButton = driver.findElement(By.xpath("//span[text()='Bỏ qua']"));
        skipButton.findElement(By.xpath("./ancestor::button")).click();
        Thread.sleep(2000);
		//trang home
        WebElement homeButton = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div[1]/a/img"));
		homeButton.click();
		Thread.sleep(2000);
		//click vào trang
		WebElement courseImage = driver.findElement(By.xpath("//img[@alt='course-thumbnail' and contains(@src, 'thu-vien-chuan-cpp')]"));
        WebElement clickableElement = courseImage.findElement(By.xpath("./ancestor::a"));
        clickableElement.click();
        Thread.sleep(2000);
        // Click giới thiệu 
        WebElement gioiThieuBtn = driver.findElement(By.xpath("//div[text()='Giới thiệu']"));
        gioiThieuBtn.click();
        // Click giáo trình 
        WebElement giaoTrinhBtn = driver.findElement(By.xpath("//div[text()='Giáo trình']"));
        giaoTrinhBtn.click();
        //Click đánh giá
        WebElement danhGiaBtn = driver.findElement(By.xpath("//div[text()='Đánh giá']")	);
        danhGiaBtn.click();
        //click Chứng chỉ
        WebElement chungChiBtn = driver.findElement( By.xpath("//div[text()='Chứng chỉ']"));
        chungChiBtn.click();
        //Click trang home
        WebElement homeButton1 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div[1]/a/img"));
		homeButton1.click();
		 Thread.sleep(2000);
        // Click Cuộc thi
        driver.findElement(By.xpath("//div[@id='t7']//a[1]//img[1]")).click();
        WebElement homeButton2 = driver.findElement(By.xpath("/html/body/div[1]/div[1]/div[2]/div/div/div[1]/a/img"));
		homeButton2.click();
		 Thread.sleep(2000);
		 // Click bài viết
		 driver.findElement(By.xpath("//div[@class='mt-5 flex flex-col gap-3 bg-white px-5 pt-5 rounded-md flex-auto border']//a[1]")).click(); 
		
		 // Click Luyện tập hàng ngày
		 driver.findElement(By.xpath(" //body/div/div/main/div/div/div/div/div/div/div/a[2]/div[2]")).click(); 
		 
		 driver.quit();
	}

}
