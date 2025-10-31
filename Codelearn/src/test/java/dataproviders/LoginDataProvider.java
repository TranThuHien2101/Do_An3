package dataproviders;

import org.testng.annotations.DataProvider;
import utils.ExcelUtils;

public class LoginDataProvider {
	@DataProvider(name = "loginData")
    public Object[][] loginDataProvider() {
        String filePath = "src/test/resources/LoginData.xlsx";
        return ExcelUtils.getTestData(filePath, "Sheet1", 3);
    }
}