package resultreport;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDateTime;

public class LoginTestResultWriter {
	private static final String FILE_PATH = "src/test/resources/LoginTestResults.xlsx";
    private static Workbook workbook;
    private static Sheet sheet;
    private static int rowCount = 0;

    static {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
                rowCount = sheet.getLastRowNum();
            } catch (IOException e) {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Results");
            }
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Results");

            Row header = sheet.createRow(rowCount++);
            header.createCell(0).setCellValue("Email");
            header.createCell(1).setCellValue("Password");
            header.createCell(2).setCellValue("Expected Result");
            header.createCell(3).setCellValue("Actual Result");
            header.createCell(4).setCellValue("Status");
            header.createCell(5).setCellValue("Time");
        }
    }

    public static void writeResult(String email, String password, String expectedResult, String actualResult, String status) {
        Row row = sheet.createRow(++rowCount);
        row.createCell(0).setCellValue(email);
        row.createCell(1).setCellValue(password);
        row.createCell(2).setCellValue(expectedResult);
        row.createCell(3).setCellValue(actualResult);
        row.createCell(4).setCellValue(status);
        row.createCell(5).setCellValue(LocalDateTime.now().toString());

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}