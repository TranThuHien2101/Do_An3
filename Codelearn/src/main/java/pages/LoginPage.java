package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class LoginPage {
    private WebDriver driver;

    // Locators dành cho CodeLearn
    private final By openLoginFormButton = By.cssSelector("span.mantine-Button-label");
    private final By emailInput = By.id("login-user-name");
    private final By passwordInput = By.id("login-password");
    private final By loginButton = By.xpath("//div[@id='login-submit']//button");
    private final By loginForm = By.id("login-submit"); // đại diện cho form login
    private final By loginAlert = By.className("mantine-Notification-description"); // nếu có thông báo lỗi

    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openLoginForm() {
        driver.get("https://codelearn.io/");
        try {
            Thread.sleep(2000); // Chờ trang load
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.findElement(openLoginFormButton).click();
    }

    public void clearFields() {
        driver.findElement(emailInput).clear();
        driver.findElement(passwordInput).clear();
    }

    public void login(String email, String password) {
        WebElement emailField = driver.findElement(emailInput);
        WebElement passwordField = driver.findElement(passwordInput);

        emailField.clear();
        emailField.sendKeys(email);

        passwordField.clear();
        passwordField.sendKeys(password);

        driver.findElement(loginButton).click();
    }

    public By getEmailInputLocator() {
        return emailInput;
    }

    public By getLoginFormLocator() {
        return loginForm;
    }

    public By getLoginAlertLocator() {
        return loginAlert;
    }

    public By getPasswordInputLocator() {
        return passwordInput;
    }
}
