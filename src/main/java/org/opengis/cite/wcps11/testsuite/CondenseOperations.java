package org.opengis.cite.wcps11.testsuite;

import static org.opengis.cite.wcps11.testdata.CondenseOperations.*;

import org.json.JSONException;
import org.opengis.cite.wcps11.testdata.QueryAndOracle;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class CondenseOperations extends TestSuiteBase {
    
    @DataProvider(name = "condenseOps")
    public Object[][] provideCondenseOps() {
        return new Object[][]{{CONDENSE_ADD}, {CONDENSE_MUL}, {CONDENSE_MAX}, {CONDENSE_MIN}, {CONDENSE_OR},
                              {CONDENSE_AND}, {REDUCE_SUM}, {REDUCE_AVG}, {REDUCE_MIN}, {REDUCE_MAX}, {REDUCE_COUNT}};
    }
    
    @Test(description = "Condense Operations", dataProvider = "condenseOps")
    public void testCondenseOps(QueryAndOracle data) throws JSONException {
        assertScalarOutput(data);
    }
}
