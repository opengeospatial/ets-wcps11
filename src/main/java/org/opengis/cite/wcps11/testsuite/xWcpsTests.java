package org.opengis.cite.wcps11.testsuite;

import static org.opengis.cite.wcps11.testdata.xWcps.*;
import static org.opengis.cite.wcps11.testdata.UnaryInducedOperations.UNSUPPORTED_TYPE;
import static org.opengis.cite.wcps11.testdata.CondenseOperations.UNSUPPORTED_OP;

import org.json.JSONException;
import org.opengis.cite.wcps11.testdata.QueryAndOracle;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class xWcpsTests extends TestSuiteBase {
    
    @DataProvider(name = "basicBinaryOps")
    public Object[][] provideBasicBinaryOps() {
        return new Object[][]{{SCALAR_ADDITION_1}, {SCALAR_ADDITION_2}, {BOOLEAN_OP_1}, {BOOLEAN_OP_2}};
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
        return new Object[][]{{LET_SCALAR}, {LET_MULTI}, {LET_COV}, {LET_LATER_USAGE}};
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
        return new Object[][]{{DUPLICATE_CONST_NAME}, {CRS_AXES_MISMATCH}, {UNSUPPORTED_TYPE}, {UNSUPPORTED_OP}};
    }

    @Test(description = "Basic binary operations", dataProvider = "basicBinaryOps")
    public void testBasicBinaryOps(QueryAndOracle data) {
        assertScalarOutput(data);
    }
    
    @Test(description = "Numeric literals and return statement")
    public void testNumericLiterals() {
        assertScalarOutput(RETURN_NUMERIC_LITERAL);
    }

    @Test(description = "Requirements 6,7,8,10 of xWcps ATS: xWcpsCoverageConstructorExpr", dataProvider = "coverageConstructor")
    public void testCoverageConstructor(QueryAndOracle data) throws JSONException {
        assertCoverageOutput(data);
    }

    @Test(description = "Requirement 2 of xWcps ATS: letExpr", dataProvider = "letExpr")
    public void testLetExpr(QueryAndOracle data) throws JSONException {
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
