package tests;

import org.testng.annotations.AfterSuite;
import utility.EmailSender;

public class TestRunner {

    @AfterSuite
    public void sendReportByEmail() {
        String filePath = "C:\\Users\\THU HIEN\\eclipse-workspace\\CodeLearn Chuan\\test-output\\ExtentReport.html";

        System.out.println(">>> [AfterSuite] Bắt đầu gửi email báo cáo...");

        try {
            EmailSender.sendEmailWithAttachment(
                "hienthu21012004@gmail.com",
                "Báo cáo kiểm thử tự động",
                "Chào bạn,\n\nĐây là báo cáo kiểm thử tự động được gửi sau khi hoàn thành toàn bộ test.\n\nTrân trọng,\nTestter Tran Thu Hien",
                filePath
            );
            System.out.println(">>> [AfterSuite] Đã gửi email báo cáo thành công!");
        } catch (Exception e) {
            System.err.println(">>> [AfterSuite] Gửi email thất bại:");
            e.printStackTrace(); // <-- Quan trọng: in lỗi ra console
        }
    }
}
