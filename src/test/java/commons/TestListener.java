package commons;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;


public class TestListener extends BaseTest implements ITestListener {


    @Override
    public void onStart(ITestContext result) {
        Log.info("Bắt đầu thực hiện chạy test case " + result.getName());

    }

    @Override
    public void onFinish(ITestContext result) {
        Log.info("Kết thúc thực hiện chạy test case " + result.getName());

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // TODO Auto-generated method stub
    }

    @Override
    public void onTestFailure(ITestResult result) {
        Log.info("[" + result.getName() + "] " + "[FAILED]");
        takeScreenshots(result);

    }

    @Override
    public void onTestSuccess(ITestResult result) {
        Log.info("[" + result.getName() + "] " + "[PASSED]");

    }

    @Override
    public void onTestSkipped(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestStart(ITestResult result) {
        // TODO Auto-generated method stub

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        Log.info("[" + result.getName() + "] " + "[FAILED_TIMEOUT]");
    }


}
