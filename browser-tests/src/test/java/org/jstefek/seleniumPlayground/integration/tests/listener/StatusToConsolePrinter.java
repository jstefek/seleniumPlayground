package org.jstefek.seleniumPlayground.integration.tests.listener;

import static java.text.MessageFormat.format;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class StatusToConsolePrinter implements ITestListener {

    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("HH:mm:ss");
    private static final String MSG_FORMAT = "{0} {1}: {2}";

    private static String getFormattedDate() {
        return LocalTime.now().format(DATE_FORMAT);
    }

    @Override
    public void onFinish(ITestContext context) {
    }

    @Override
    public void onStart(ITestContext context) {
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

    @Override
    public void onTestFailure(ITestResult result) {
        printStatus(result, "FAILED");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        printStatus(result, "SKIPPED");
    }

    @Override
    public void onTestStart(ITestResult result) {
        System.out.println("");
        printStatus(result, "STARTED");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        printStatus(result, "SUCCESS");
    }

    private String getTestName(ITestResult result) {
        Class<?> testClass = result.getInstance().getClass();
        String pkgName = testClass.getPackage().getName();
        pkgName = pkgName.substring(pkgName.lastIndexOf('.') + 1);
        String testName = pkgName + '.' + testClass.getSimpleName() + '#' + result.getName();
        return testName;
    }

    private void printStatus(ITestResult result, String status) {
        System.out.println(format(MSG_FORMAT, getFormattedDate(), status, getTestName(result)));
    }

}
