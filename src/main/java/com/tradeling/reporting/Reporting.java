package com.tradeling.reporting;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.ExtentSparkReporterConfig;
import com.aventstack.extentreports.reporter.configuration.Theme;
import com.tradeling.utilities.Utilities;

import java.io.File;
import java.util.Locale;
import java.util.Random;

public class Reporting {

    static ThreadLocal<Logger> loggerThreadLocal = new ThreadLocal<Logger>();
    static ExtentSparkReporter spark;
    public static String reportFolderLoc = "report";
    public static String runId = "";
    public static String screenShotLoc = "";
    public static ExtentReports extent;

    public void initiateReport() {
        runId = Utilities.createUniqueId(999999);
        System.out.println("Run id: " + runId);
        createReportDirectory();
        extent = new ExtentReports();
        spark = new ExtentSparkReporter(reportFolderLoc+ "/" + runId);
        extent.attachReporter(spark);
        setReportConfig();
    }

    private void setReportConfig(){
      spark.config(ExtentSparkReporterConfig.builder().theme(Theme.DARK).reportName("Tradeling Mobile Buyer App").documentTitle("Tradeling").build());
    }

    public void closeReporting(){
        extent.flush();
    }

    public void setLogger(String testName){
        Logger logger = new Logger(extent.createTest(testName.replace("_"," ").toUpperCase(Locale.ROOT)));
        loggerThreadLocal.set(logger);
    }

    public static Logger getLogger(){
        return loggerThreadLocal.get();
    }


    public void createReportDirectory() {
        try {
            File executionFolder = new File(reportFolderLoc + "/" + runId);
            screenShotLoc = reportFolderLoc + "/" + runId + "/screenshots";
            File screenShotFolder = new File(screenShotLoc);
            if (!executionFolder.exists()) {
                if (executionFolder.mkdirs()) {
                    if (!screenShotFolder.exists()) {
                        screenShotFolder.mkdir();
                    }
                } else {
                    System.out.println("Failed to create report directory");
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }






}
