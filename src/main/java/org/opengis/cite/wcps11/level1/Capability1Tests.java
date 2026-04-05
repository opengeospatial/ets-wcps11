package org.opengis.cite.wcps11.level1;

import static org.opengis.cite.wcps11.testdata.TestData.*;

import org.json.JSONException;
import org.opengis.cite.wcps11.CommonFixture;
import org.opengis.cite.wcps11.SuiteAttribute;
import org.opengis.cite.wcps11.testdata.QueryAndOracle;
import org.opengis.cite.wcps11.util.WCPSWrapper;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
/**
 * Includes various tests of capability 1.
 */
public class Capability1Tests extends CommonFixture {

    private WCPSWrapper service;

    // private Document testSubject;

    /**
     * Obtains the test subject from the ISuite context. The suite attribute
     * {@link org.opengis.cite.wcps11.SuiteAttribute#WCPS_ENDPOINT} should
     * be a WCPSWrapper instance.
     * 
     * @param testContext
     *            The test (group) context.
     */
    @BeforeClass
    public void obtainTestSubject(ITestContext testContext) {
        Object obj = testContext.getSuite().getAttribute(
                SuiteAttribute.WCPS_ENDPOINT.getName());
        if ((null != obj) && WCPSWrapper.class.isAssignableFrom(obj.getClass())) {
            this.service = WCPSWrapper.class.cast(obj);
        }
    }

    private void assertScalarOutput(QueryAndOracle data) {
        String output = service.sendQueryKVP(data.query);
        Assert.assertEquals(output.trim(), data.oracle.trim(),
                "Unexpected output for query: " + data.query);
    }

    private void assertCoverageOutput(QueryAndOracle data) throws JSONException{
        String output = service.sendQueryKVP(data.query);
        JSONAssert.assertEquals(output, data.oracle, true);
    }

    @DataProvider(name = "letExprQueries")
    public Object[][] provideLetExprQueries() {
        return new Object[][]{{LET_SCALAR}, {LET_MUTI}, {LET_COV}, {LET_LATER_USAGE}};
    }

    @Test(description = "Numeric literals and return statement")
    public void testNumericLiterals() {
        assertScalarOutput(RETURN_NUMERIC_LITERAL);
    }


    @Test(description = "Requirement 2 of xWcps ATS", dataProvider = "letExprQueries")
    public void testLetExpr(QueryAndOracle data) throws JSONException {
        assertCoverageOutput(data);
    }
}
