package org.opengis.cite.wcps11.level1;

import static org.opengis.cite.wcps11.testdata.xWcps.*;
import static org.opengis.cite.wcps11.testdata.UnaryInduced.*;
import static org.opengis.cite.wcps11.testdata.BinaryInduced.*;

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
        String output = service.sendQueryKVP(data.query).getEntity(String.class);
        Assert.assertEquals(data.oracle.trim(), output.trim(),
                "Unexpected output for query: " + data.query);
    }

    private void assertCoverageOutput(QueryAndOracle data) throws JSONException {
        String output = service.sendQueryKVP(data.query).getEntity(String.class);
        JSONAssert.assertEquals("Unexpected coverage output for query: " + data.query, output, data.oracle, true);
    }

    private void assertErrorResponse(QueryAndOracle data) {
        int statusCode = service.sendQueryKVP(data.query).getStatus();
        Assert.assertEquals(statusCode, 400, "Expected HTTP 400 Bad Request for query: " + data.query);
    }

    @DataProvider(name = "basicBinaryOps")
    public Object[][] provideBasicBinaryOps() {
        return new Object[][]{{SCALAR_ADDITION_1}, {SCALAR_ADDITION_2}};
    }

    @DataProvider(name = "coverageConstructor")
    public Object[][] provideCoverageConstructor() {
        return new Object[][]{{CONSTRUCTOR_1D_BASIC}, {CONSTRUCTOR_1D_ITERATOR},
                              {ADDITION_IN_RANGE_SET_1}, {ADDITION_IN_RANGE_SET_2},
                              {CONSTRUCTOR_2D}, {CONSTRUCTOR_3D}, {CONSTRUCTOR_4D}, {CONSTRUCTOR_5D},
                              {OTHER_CRS_AND_AXIS_TYPES_1}, {OTHER_CRS_AND_AXIS_TYPES_2},
                              {CRS_COMBINATION}, {MULTI_FIELD_RANGE_1}, {MULTI_FIELD_RANGE_2}};
    }

    @DataProvider(name = "letExpr")
    public Object[][] provideLetExpr() {
        return new Object[][]{{LET_SCALAR}, {LET_MUTI}, {LET_COV}, {LET_LATER_USAGE}};
    }
    
    @DataProvider(name = "unaryInducedOps")
    public Object[][] provideUnaryInducedOps() {
        return new Object[][]{{MINUS}, {ABS}};
    }

    @DataProvider(name = "binaryInducedOps")
    public Object[][] provideBinaryInducedOps() {
        return new Object[][]{{BOOLEAN_OP_ON_COV}, {SCALAR_ADDITION_ON_COV}, {MUL}, {DIV}};
    }

    @DataProvider(name = "switchExprScalar")
    public Object[][] provideSwitchExprScalar() {
        return new Object[][]{{SWITCH_SCALAR}, {SWITCH_MULTI_CASE}};
    }

    @DataProvider(name = "switchExprCoverage")
    public Object[][] provideSwitchExprCoverage() {
        return new Object[][]{{SWITCH_COV}, {SWITCH_COV_OUTPUT}};
    }

    @DataProvider(name = "errorResponses")
    public Object[][] provideErrorResponses() {
        return new Object[][]{{DUPLICATE_CONST_NAME}, {CRS_AXES_MISMATCH}};
    }

    @Test(description = "Numeric literals and return statement")
    public void testNumericLiterals() {
        assertScalarOutput(RETURN_NUMERIC_LITERAL);
    }
 
    @Test(description = "Basic binary operations", dataProvider = "basicBinaryOps")
    public void testBasicBinaryOps(QueryAndOracle data) {
        assertScalarOutput(data);
    }

    @Test(description = "Requirements 6,7,8,10 of xWcps ATS: xWcpsCoverageConstructorExpr", dataProvider = "coverageConstructor")
    public void testCoverageConstructor(QueryAndOracle data) throws JSONException {
        assertCoverageOutput(data);
    }

    @Test(description = "Requirement 2 of xWcps ATS: letExpr", dataProvider = "letExpr")
    public void testLetExpr(QueryAndOracle data) throws JSONException {
        assertCoverageOutput(data);
    }

    @Test(description = "Unary Induced Operations", dataProvider = "unaryInducedOps")
    public void testUnaryInducedOps(QueryAndOracle data) throws JSONException {
        assertCoverageOutput(data);
    }

    @Test(description = "Binary Induced Operations", dataProvider = "binaryInducedOps")
    public void testBinaryInducedOps(QueryAndOracle data) throws JSONException {
        assertCoverageOutput(data);
    }

    @Test(description = "Requirement 13 of xWcps ATS: switchExpr", dataProvider = "switchExprScalar")
    public void testSwitchExprScalar(QueryAndOracle data) throws JSONException {
        assertScalarOutput(data);
    }

    @Test(description = "Requirement 13 of xWcps ATS: switchExpr", dataProvider = "switchExprCoverage")
    public void testSwitchExprCoverage(QueryAndOracle data) throws JSONException {
        assertCoverageOutput(data);
    }

    @Test(description = "Requirement 19 of xWcps ATS: queries leading to exceptions", dataProvider = "errorResponses")
    public void testErrorResponses(QueryAndOracle data) {
        assertErrorResponse(data);
    }

}
