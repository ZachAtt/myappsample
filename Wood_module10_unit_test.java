package JavaDoc;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

/**
 * Run unit tests on word occurrences classes
 * @author Zachary Wood
 */

public class Wood_module10_unit_test {
 	
	/**
	 * Runs Junit tests and prints test results
	 * @param args     command line arguments
	 */
	public static void main(String[] args) {
      Result result = JUnitCore.runClasses(TestJunit.class);
		
      for (Failure failure : result.getFailures()) {
         System.out.println(failure.toString());
      }
		
      System.out.println((result.wasSuccessful() ? "\nAll tests passed" : "\nTest failed"));
	}
}  	