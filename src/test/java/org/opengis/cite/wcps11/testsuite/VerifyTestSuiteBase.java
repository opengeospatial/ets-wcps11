package org.opengis.cite.wcps11.testsuite;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.opengis.cite.wcps11.SuiteAttribute;
import org.opengis.cite.wcps11.testdata.QueryAndOracle;
import org.opengis.cite.wcps11.util.WCPSWrapper;
import org.testng.ISuite;
import org.testng.ITestContext;
import com.sun.jersey.api.client.ClientResponse;

/**
 * Verifies the behavior of the Capability1Tests test class. Test stubs replace
 * fixture constituents where appropriate.
 */
public class VerifyTestSuiteBase {
    private TestSuiteBase testSuite;

    private WCPSWrapper mockService;

    @Before
    public void setUp() {
        testSuite = new TestSuiteBase();
        ITestContext mockContext = mock(ITestContext.class);
        ISuite mockSuite = mock(ISuite.class);
        mockService = mock(WCPSWrapper.class);
        when(mockContext.getSuite()).thenReturn(mockSuite);
        when(mockSuite.getAttribute(SuiteAttribute.WCPS_WRAPPER.getName())).thenReturn(mockService);
        testSuite.obtainTestSubject(mockContext);
    }

    @Test(expected = AssertionError.class)
    public void testAssertScalarOutput() {
        QueryAndOracle queryAndOracle = new QueryAndOracle("some query", "42");
        ClientResponse response = mock(ClientResponse.class);
        
        when(mockService.sendQueryKVP(queryAndOracle.query)).thenReturn(response);
        when(response.getEntity(String.class)).thenReturn("41");

        testSuite.assertScalarOutput(queryAndOracle);
    }

    @Test
    public void testAssertScalarOutputSuccess() {
        QueryAndOracle queryAndOracle = new QueryAndOracle("some query", "42.0");
        ClientResponse response = mock(ClientResponse.class);

        when(mockService.sendQueryKVP(queryAndOracle.query)).thenReturn(response);
        when(response.getEntity(String.class)).thenReturn("42.00001");

        testSuite.assertScalarOutput(queryAndOracle);
    }

    @Test
    public void testAssertScalarOutputNonNumeric() {
        QueryAndOracle queryAndOracle = new QueryAndOracle("some query", "false");
        ClientResponse response = mock(ClientResponse.class);

        when(mockService.sendQueryKVP(queryAndOracle.query)).thenReturn(response);
        when(response.getEntity(String.class)).thenReturn("false");

        testSuite.assertScalarOutput(queryAndOracle);
    }

    @Test
    public void testAssertCoverageOutput() throws JSONException {
        String json1 = "[[1, 2, 3],\n[4, 5, 6]]";
        String json2 = "[[1,2,3],[4,5,6]]";
        QueryAndOracle queryAndOracle = new QueryAndOracle("some query", json1);
        ClientResponse response = mock(ClientResponse.class);

        when(mockService.sendQueryKVP(queryAndOracle.query)).thenReturn(response);
        when(response.getEntity(String.class)).thenReturn(json2);

        testSuite.assertCoverageOutput(queryAndOracle);
    }

    @Test(expected = AssertionError.class)
    public void testAssertCoverageOutputInvalidJSON() throws JSONException {
        String json1 = "[[1, 2, 3],\n[4, 5, 6]]";
        String json2 = "[[1,2,3],[4,6,6]]";
        QueryAndOracle queryAndOracle = new QueryAndOracle("some query", json1);
        ClientResponse response = mock(ClientResponse.class);

        when(mockService.sendQueryKVP(queryAndOracle.query)).thenReturn(response);
        when(response.getEntity(String.class)).thenReturn(json2);

        testSuite.assertCoverageOutput(queryAndOracle);
    }
}
