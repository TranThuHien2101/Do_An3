package tests;

import utility.EmailSender;
import org.testng.annotations.AfterSuite;

public class TestRunner {

    @AfterSuite
    public void sendReport() {
        String filePath = "C:\\Users\\THU HIEN\\Documents\\Đồ án 3\\Codelearn\\target\\test-classes";

        EmailSender.sendEmailWithAttachment(
            "hienthu21012004@gmail.com", 
            "Automation Test Report",
            "Chào bạn,\n\nĐây là báo cáo kiểm thử tự động.\n\nTrân trọng,\nAutomation Bot",
            filePath
        );
    }
}
