package resultreport;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.time.LocalDateTime;

public class RegisterDataResult {
    private static final String FILE_PATH = "src/test/resources/RegisterTestResults.xlsx";
    private static Workbook workbook;
    private static Sheet sheet;
    private static int rowCount = 0;

    static {
        File file = new File(FILE_PATH);
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
                sheet = workbook.getSheetAt(0);
                rowCount = sheet.getLastRowNum() + 1;
            } catch (IOException e) {
                workbook = new XSSFWorkbook();
                sheet = workbook.createSheet("Results");
                createHeader();
            }
        } else {
            workbook = new XSSFWorkbook();
            sheet = workbook.createSheet("Results");
            createHeader();
        }
    }

    private static void createHeader() {
        Row header = sheet.createRow(rowCount++);
        header.createCell(0).setCellValue("Username");
        header.createCell(1).setCellValue("Email");
        header.createCell(2).setCellValue("Password");
        header.createCell(3).setCellValue("Expected Result");
        header.createCell(4).setCellValue("Actual Result");
        header.createCell(5).setCellValue("Erro");
        header.createCell(6).setCellValue("Status");
        header.createCell(7).setCellValue("Time");
    }

    public static void writeResult(String username, String email, String password, String agree,
                                   String expectedResult, String actualResult, String status) {
        Row row = sheet.createRow(++rowCount);
        row.createCell(0).setCellValue(username != null ? username : "");
        row.createCell(1).setCellValue(email != null ? email : "");
        row.createCell(2).setCellValue(password != null ? password : "");
        row.createCell(3).setCellValue(expectedResult != null ? expectedResult : "");
        row.createCell(4).setCellValue(agree != null ? agree: "");
        row.createCell(5).setCellValue(actualResult != null ? actualResult : "");
        row.createCell(6).setCellValue(status != null ? status : "");
        row.createCell(7).setCellValue(LocalDateTime.now().toString());

        try (FileOutputStream fos = new FileOutputStream(FILE_PATH)) {
            workbook.write(fos);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
