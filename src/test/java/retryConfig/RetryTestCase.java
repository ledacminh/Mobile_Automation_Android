package retryConfig;

import commons.GlobalConstants;
import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryTestCase implements IRetryAnalyzer {

    private int retryCount = 0;

    @Override
    public boolean retry(ITestResult result) {
        if (retryCount < GlobalConstants.RETRY_COUNT) {
            retryCount++;
            return true;
        }
        return false;
    }

}
