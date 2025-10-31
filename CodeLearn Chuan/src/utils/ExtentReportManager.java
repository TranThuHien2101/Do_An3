package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;

import java.io.File;

public class ExtentReportManager {
    private static ExtentReports extent;
    private static final String reportPath = "test-output/ExtentReport.html";

    public static ExtentReports getInstance() {
        if (extent == null) {
            extent = new ExtentReports();

            // Tạo thư mục chứa báo cáo nếu chưa tồn tại
            File reportFile = new File(reportPath);
            File parentDir = reportFile.getParentFile();
            if (!parentDir.exists()) {
                parentDir.mkdirs();
            }

            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);

            // Cấu hình báo cáo
            reporter.config().setDocumentTitle("Automation Test Report");
            reporter.config().setReportName ("Functional Test Report");
            reporter.config().setTheme(Theme.STANDARD);  // Hoặc Theme.DARK

            extent.attachReporter(reporter);

            // Thêm thông tin hệ thống vào báo cáo
            extent.setSystemInfo("OS", System.getProperty("os.name"));
            extent.setSystemInfo("Java Version", System.getProperty("java.version"));
            extent.setSystemInfo("Tester", "Tran Thu Hien");
        }
        return extent;
    }

    // Lấy đường dẫn báo cáo
    public static String getReportPath() {
        return reportPath;
    }
    public static void flushReport() {
        if (extent != null) {
            extent.flush();
            System.out.println(">>> Báo cáo đã được flush ra: " + reportPath);
        } else {
            System.out.println(">>> Báo cáo chưa được khởi tạo!");
        }
    }


}
