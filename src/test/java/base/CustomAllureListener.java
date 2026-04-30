package base;

import io.qameta.allure.testng.AllureTestNg;
import org.testng.ITestResult;
import org.testng.annotations.Test;

public class CustomAllureListener extends AllureTestNg {

    @Override
    public void onTestStart(ITestResult result) {
        try {
            if (result != null && result.getMethod() != null) {
                var method = result.getMethod().getConstructorOrMethod().getMethod();
                if (method != null) {
                    Test testAnnotation = method.getAnnotation(Test.class);
                    if (testAnnotation != null && !testAnnotation.enabled()) {
                        System.out.println("[CustomAllureListener] Ignoring disabled test: " + method.getName());
                        return;
                    }
                }
            }
        } catch (Exception e) {
            System.err.println("[CustomAllureListener] Error checking @Test annotation: " + e.getMessage());
        }

        System.out.println("[CustomAllureListener] Registering test: " + result.getMethod().getMethodName());
        super.onTestStart(result);
    }
}
