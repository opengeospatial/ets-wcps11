package org.opengis.cite.wcps11;

import java.net.URI;
import java.util.Map;
import java.util.logging.Level;

import org.opengis.cite.wcps11.util.ClientUtils;
import org.opengis.cite.wcps11.util.TestSuiteLogger;
import org.opengis.cite.wcps11.util.WCPSWrapper;
import org.testng.ISuite;
import org.testng.ISuiteListener;

/**
 * A listener that performs various tasks before and after a test suite is run,
 * usually concerned with maintaining a shared test suite fixture. Since this
 * listener is loaded using the ServiceLoader mechanism, its methods will be
 * called before those of other suite listeners listed in the test suite
 * definition and before any annotated configuration methods.
 *
 * Attributes set on an ISuite instance are not inherited by constituent test
 * group contexts (ITestContext). However, suite attributes are still accessible
 * from lower contexts.
 *
 * @see org.testng.ISuite ISuite interface
 */
public class SuiteFixtureListener implements ISuiteListener {

    @Override
    public void onStart(ISuite suite) {
        processSuiteParameters(suite);
    }

    @Override
    public void onFinish(ISuite suite) {
        // No actions needed after suite execution
    }
    /**
     * Processes test suite arguments and sets suite attributes accordingly. The
     * url given by the {@link TestRunArg#IUT iut} argument is retrieved
     * and used to create a {@link WCPSWrapper} instance, which is stored as a suite attribute under
     * {@link SuiteAttribute#WCPS_WRAPPER wcpsWrapper}.
     * 
     * @param suite
     *            An ISuite object representing a TestNG test suite.
     */
    void processSuiteParameters(ISuite suite) {
        Map<String, String> params = suite.getXmlSuite().getParameters();
        TestSuiteLogger.log(Level.CONFIG, "Suite parameters\n" + params.toString());
        String iutParam = params.get(TestRunArg.IUT.toString());
        if ((null == iutParam) || iutParam.isEmpty()) {
            throw new IllegalArgumentException("Required test run parameter not found: " + TestRunArg.IUT.toString());
        }
        suite.setAttribute(SuiteAttribute.WCPS_WRAPPER.getName(), new WCPSWrapper(URI.create(iutParam), ClientUtils.buildClient()));
    }
}
