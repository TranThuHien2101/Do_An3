package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RegisterPage {
    private WebDriver driver;
    private WebDriverWait wait;

    private final By openRegisterFormButton = By.xpath("//span[text()='Đăng ký' and contains(@class, 'text-blue-primary')]");
    private final By usernameInput = By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/div[1]/div/input");
    private final By emailInput = By.xpath("//input[@type='text' and @name='email']");
    private final By passwordInput = By.xpath("/html/body/div[10]/div/div/div/div[2]/section/div[2]/div[2]/div[5]/form/div/div[3]/div/div[1]/input");    private final By agreeCheckbox = By.name("agree");
    private final By registerButton = By.xpath("//button[contains(text(),'Đăng ký')]");
    private final By errorMessage = By.xpath("//div[contains(@class,'mantine-Notification-description')]");
    private final By successMessage = By.xpath("//div[contains(@class,'mantine-Notification-description') and contains(text(),'thành công')]");
    private final By overlay = By.xpath("//div[contains(@class, 'mantine-Overlay-root')]");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void openRegisterForm() {
        driver.get("https://codelearn.io/");
        wait.until(ExpectedConditions.elementToBeClickable(openRegisterFormButton)).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(usernameInput));
        waitForOverlayToDisappear();
    }

    private void waitForOverlayToDisappear() {
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(overlay));
        } catch (Exception ignored) {}
    }

    public void enterUsername(String username) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(usernameInput));
        field.clear();
        field.sendKeys(username);
    }

    public void enterEmail(String email) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(emailInput));
        field.clear();
        field.sendKeys(email);
    }

    public void enterPassword(String password) {
        WebElement field = wait.until(ExpectedConditions.elementToBeClickable(passwordInput));
        field.clear();
        field.sendKeys(password);
    }

    public void setAgreeCheckbox(String agree) {
        if (agree.equalsIgnoreCase("true")) {
            WebElement checkbox = wait.until(ExpectedConditions.elementToBeClickable(agreeCheckbox));
            if (!checkbox.isSelected()) {
                checkbox.click();
            }
        }
    }

    public void clickRegister() {
        try {
            WebElement btn = wait.until(ExpectedConditions.elementToBeClickable(registerButton));
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", btn);
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn);
        } catch (Exception e) {
            System.out.println("❌ Không thể click nút Đăng ký: " + e.getMessage());
        }
    }

    public String getErrorMessage() {
        try {
            WebElement error = wait.until(ExpectedConditions.visibilityOfElementLocated(errorMessage));
            return error.getText();
        } catch (Exception e) {
            return null;
        }
    }

    public String getSuccessMessage() {
        try {
            WebElement success = wait.until(ExpectedConditions.visibilityOfElementLocated(successMessage));
            return success.getText();
        } catch (Exception e) {
            return null;
        }
    }
}
