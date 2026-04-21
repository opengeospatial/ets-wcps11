package org.opengis.cite.wcps11.testsuite;

import static org.opengis.cite.wcps11.testdata.UnaryInducedOperations.*;
import static org.opengis.cite.wcps11.testdata.BinaryInducedOperations.*;

import org.json.JSONException;
import org.opengis.cite.wcps11.testdata.QueryAndOracle;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
/**
 * Includes various tests of capability 1.
 */
public class InducedOperations extends TestSuiteBase {
    
    @DataProvider(name = "unaryInducedOps")
    public Object[][] provideUnaryInducedOps() {
        return new Object[][]{{PLUS}, {MINUS}, {ABS},
                              {SQRT}, {SIN}, {COS}, {TAN}, {SINH}, {COSH}, {TANH},
                              {ASIN}, {ACOS}, {ATAN}, {EXP}, {LOG}, {LN}, {POW},
                              {BIT_1}, {BIT_2},
                              {FIELD_EXPR}, {CAST}};
    }

    @DataProvider(name = "binaryInducedOps")
    public Object[][] provideBinaryInducedOps() {
        return new Object[][]{{ADD_SCALAR}, {MUL_SCALAR}, {DIV_SCALAR}, {EQ_SCALAR}, {NEQ_SCALAR}, {GREATER_SCALAR}, 
                              {LESS_SCALAR}, {GREATER_EQ_SCALAR}, {LESS_EQ_SCALAR}, {AND_SCALAR}, {OR_SCALAR}, 
                              {XOR_SCALAR}, {OVERLAY_SCALAR_1}, {OVERLAY_SCALAR_2}, {ADD_COV}, {MUL_COV}, {DIV_COV}, {EQ_COV}, {NEQ_COV}, {GREATER_COV},
                              {LESS_COV}, {GREATER_EQ_COV}, {LESS_EQ_COV}, {AND_COV}, {OR_COV}, {XOR_COV}, {OVERLAY_COV}};
    }

    @Test(description = "Unary Induced Operations", dataProvider = "unaryInducedOps")
    public void testUnaryInducedOps(QueryAndOracle data) throws JSONException {
        assertCoverageOutput(data);
    }

    @Test(description = "Binary Induced Operations", dataProvider = "binaryInducedOps")
    public void testBinaryInducedOps(QueryAndOracle data) throws JSONException {
        assertCoverageOutput(data);
    }
}
