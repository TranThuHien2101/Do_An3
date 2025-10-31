package utils;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class ExcelUtils {

    public static Object[][] getTestData(String filePath, String sheetName, int k) {
        try (FileInputStream fis = new FileInputStream(filePath);
				Workbook workbook = new XSSFWorkbook(fis)) {
            Sheet sheet = workbook.getSheet(sheetName);

            if (sheet == null) {
                System.err.println(" Không tìm thấy sheet tên: " + sheetName + " trong file: " + filePath);
                return new Object[0][0];
            }

            int rowCount = sheet.getPhysicalNumberOfRows();
            if (rowCount < 2) {
                System.err.println(" Sheet chỉ có tiêu đề hoặc không có dữ liệu.");
                return new Object[0][0];
            }

            //  Tự động nhận diện số cột từ hàng tiêu đề (hàng 0)
            Row headerRow = sheet.getRow(0);
            if (headerRow == null) {
                System.err.println(" Không có dòng tiêu đề trong sheet.");
                return new Object[0][0];
            }
            int colCount = headerRow.getLastCellNum(); // Số cột có thể có cell trống, nhưng vẫn đếm

            Object[][] data = new Object[rowCount - 1][colCount];
            DataFormatter formatter = new DataFormatter();

            for (int i = 1; i < rowCount; i++) {
                Row row = sheet.getRow(i);
                for (int j = 0; j < colCount; j++) {
                    Cell cell = (row != null) ? row.getCell(j) : null;
                    data[i - 1][j] = (cell != null) ? formatter.formatCellValue(cell).trim() : "";
                }
            }

            return data;

        } catch (IOException e) {
            System.err.println(" Lỗi khi đọc file Excel: " + e.getMessage());
            e.printStackTrace();
            return new Object[0][0];
        }
    }

	public static List<Map<String, String>> readExcel(String string, String string2) {
		// TODO Auto-generated method stub
		return null;
	}
}
