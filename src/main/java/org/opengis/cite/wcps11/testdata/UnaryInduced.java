package org.opengis.cite.wcps11.testdata;

import java.text.MessageFormat;

public class UnaryInduced {
    private static final String INDUCED_UNARY_OP_QUERY_TEMPLATE =
            "let $a := coverage newCov\r\n" + //
                        "domain crs \"OGC:Index1D\" with\r\n" + //
                        "x index (1:5)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "{0}\r\n" + //
                        "return encode({1}, \"application/json\")";
    
    private static final String INDUCED_UNARY_OP_ORACLE_TEMPLATE =
            "[{0},{0},{0},{0},{0}]";
    
    public static final QueryAndOracle MINUS = new QueryAndOracle(
        MessageFormat.format(INDUCED_UNARY_OP_QUERY_TEMPLATE, "1", "-$a"),
        MessageFormat.format(INDUCED_UNARY_OP_ORACLE_TEMPLATE, "-1")
    );
}
