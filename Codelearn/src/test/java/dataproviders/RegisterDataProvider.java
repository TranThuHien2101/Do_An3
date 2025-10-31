package dataproviders;

import org.testng.annotations.DataProvider;
import utils.ExcelUtils;

public class RegisterDataProvider {

	@DataProvider(name = "RegisterData")
	public static Object[][] getRegisterData() {
	    String filePath = "src/test/resources/RegisterData.xlsx";
	    Object[][] rawData = ExcelUtils.getTestData(filePath, "Sheet1", 7); // lấy 7 cột

	    Object[][] filteredData = new Object[rawData.length][5];
	    for (int i = 0; i < rawData.length; i++) {
	        System.arraycopy(rawData[i], 0, filteredData[i], 0, 5);
	    }
	    return filteredData;
	}
}