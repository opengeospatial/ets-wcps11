package org.opengis.cite.wcps11.testsuite;

import org.json.JSONException;
import org.opengis.cite.wcps11.CommonFixture;
import org.opengis.cite.wcps11.SuiteAttribute;
import org.opengis.cite.wcps11.testdata.QueryAndOracle;
import org.opengis.cite.wcps11.util.WCPSWrapper;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.skyscreamer.jsonassert.JSONCompareResult;
import org.skyscreamer.jsonassert.comparator.DefaultComparator;
import org.testng.Assert;
import org.testng.ITestContext;
import org.testng.annotations.BeforeClass;

public class TestSuiteBase extends CommonFixture {

    private static class Comparator extends DefaultComparator {
        public Comparator(JSONCompareMode mode) {
            super(mode);
        }

        @Override
        public void compareValues(String prefix, Object expected, Object actual, JSONCompareResult result) throws JSONException {
            if (expected instanceof Number && actual instanceof Number) {
                double expectedNum = ((Number) expected).doubleValue();
                double actualNum = ((Number) actual).doubleValue();
                if (Math.abs(expectedNum - actualNum) > 1e-3) {
                    result.fail(prefix, expected, actual);
                }
            } else {
                super.compareValues(prefix, expected, actual, result);
            }
        }
    }
    
    private WCPSWrapper service;

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

    protected void assertScalarOutput(QueryAndOracle data) {
        String output = service.sendQueryKVP(data.query).getEntity(String.class).trim();
        String oracle = data.oracle.trim();
        try {
            double expected = Double.parseDouble(oracle);
            double actual = Double.parseDouble(output);
            Assert.assertEquals(actual, expected, 1e-3, "Unexpected output for query: " + data.query);
        } catch (Exception e) {
            Assert.assertEquals(oracle, output,
                        "Unexpected output for query: " + data.query);
        }
    }

    protected void assertCoverageOutput(QueryAndOracle data) throws JSONException {
        String output = service.sendQueryKVP(data.query).getEntity(String.class);
        JSONAssert.assertEquals("Unexpected coverage output for query: " + data.query, output, data.oracle, new Comparator(JSONCompareMode.STRICT));
    }

    protected void assertErrorResponse(QueryAndOracle data) {
        int statusCode = service.sendQueryKVP(data.query).getStatus();
        Assert.assertTrue(statusCode > 399, "Expected Error response for query: " + data.query);
    }
}
