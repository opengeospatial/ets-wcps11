package org.opengis.cite.wcps11.level1;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import org.opengis.cite.wcps11.CommonFixture;
import org.opengis.cite.wcps11.SuiteAttribute;
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

    private final String COV_OUTPUT_TESTS_PATH = "/org/opengis/cite/wcps11/testQueriesAndOracles/coverage/";
    private final String SCALAR_OUTPUT_TESTS_PATH = "/org/opengis/cite/wcps11/testQueriesAndOracles/scalar/";
    // private Document testSubject;

    /**
     * Obtains the test subject from the ISuite context. The suite attribute
     * {@link org.opengis.cite.wcps11.SuiteAttribute#TEST_SUBJECT} should
     * evaluate to a DOM Document node.
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

    private Iterator<Object[]> queriesAndOracles(String basePath) {
        List<Object[]> params = new ArrayList<>();
        try {
            InputStream manifestStream = getClass().getResourceAsStream(basePath + "manifest.txt");
            if (manifestStream != null) {
                try (Scanner scanner = new Scanner(manifestStream, "UTF-8")) {
                    while (scanner.hasNextLine()) {
                        String filename = scanner.nextLine().trim();
                        if (!filename.isEmpty()) {
                            InputStream queryStream = getClass().getResourceAsStream(basePath + "queries/" + filename);
                            InputStream oracleStream = getClass().getResourceAsStream(basePath + "oracles/" + filename);
                            if (queryStream != null && oracleStream != null) {
                                try (Scanner qScanner = new java.util.Scanner(queryStream, "UTF-8").useDelimiter("\\A");
                                     Scanner oScanner = new java.util.Scanner(oracleStream, "UTF-8").useDelimiter("\\A")) {
                                    String query = qScanner.hasNext() ? qScanner.next() : "";
                                    String oracle = oScanner.hasNext() ? oScanner.next() : "";
                                    params.add(new Object[]{query, oracle});
                                }
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return params.iterator();
    }

    @DataProvider(name = "coverageOutputTestsProvider")
    private Iterator<Object[]> coverageOutputTestsProvider() {
        return queriesAndOracles(COV_OUTPUT_TESTS_PATH);
    }

    @DataProvider(name = "scalarOutputTestsProvider")
    private Iterator<Object[]> scalarOutputTestsProvider() {
        return queriesAndOracles(SCALAR_OUTPUT_TESTS_PATH);
    }

    @Test(dataProvider = "coverageOutputTestsProvider")
    /**
     * queries with json outputs
     */
    public void coverageOutputTests(String query, String oracle) throws Exception {
        String serverOutput = service.sendQueryKVP(query);
        JSONAssert.assertEquals("Server returned incorrect output for query: " + query, oracle, serverOutput, true);
    }

    @Test(dataProvider = "scalarOutputTestsProvider")
    /**
     * queries with scalar outputs
     */
    public void scalarOutputTests(String query, String oracle) {
        String serverOutput = service.sendQueryKVP(query);
        Assert.assertEquals(serverOutput.trim(), oracle.trim(), "Not equal for query: " + query);
    }
}
