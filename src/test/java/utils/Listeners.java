package utils;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class Listeners implements ITestListener {

	public void onFinish(ITestContext arg0) {
		System.out.println("****************SUITE FINISHED*************");
	}

	public void onStart(ITestContext arg0) {
		System.out.println("****************SUITE STARTED*************");
	}

	public void onTestFailedButWithinSuccessPercentage(ITestResult arg0) {
		System.out.println("****************TEST FAILED WITH SUCCESS PERCENTAGE*************");
	}

	public void onTestFailure(ITestResult arg0) {
		System.out.println("Test Failed");
	}

	public void onTestSkipped(ITestResult arg0) {
		System.out.println("Test skipped");
	}

	public void onTestStart(ITestResult arg0) {
//		System.out.println(arg0.getName() + "------" + arg0.getStatus());  
	}

	public void onTestSuccess(ITestResult arg0) {
		System.out.println("Test Passed");  
	}

}
