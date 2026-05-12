package org.opengis.cite.wcps11;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.testng.ITestContext;
import org.testng.annotations.BeforeSuite;

/**
 * Checks that various preconditions are satisfied before the test suite is run.
 * If any of these (BeforeSuite) methods fail, all tests will be skipped.
 */
public class SuitePreconditions {

    private static final Logger LOGR = Logger.getLogger(SuitePreconditions.class.getName());

    /**
     * Verifies that the referenced test subject exists and has the expected
     * type.
     *
     * @param testContext
     *            Information about the (pending) test run.
     */
    @BeforeSuite
    @SuppressWarnings("rawtypes")
    public void verifyTestSubject(ITestContext testContext) {
        SuiteAttribute iutAttr = SuiteAttribute.WCPS_WRAPPER;
        Object sutObj = testContext.getSuite().getAttribute(iutAttr.getName());
        Class expectedType = iutAttr.getType();
        if (null == sutObj || !expectedType.isInstance(sutObj)) {
            String msg = String.format("Value of test suite attribute '%s' is missing or is not an instance of %s",
                    iutAttr.getName(), expectedType.getName());
            LOGR.log(Level.SEVERE, msg);
            throw new AssertionError(msg);
        }
    }
}
