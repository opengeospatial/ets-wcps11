package org.opengis.cite.wcps11.testdata;

import java.text.MessageFormat;

public class BinaryInduced {
    private static final String INDUCED_BINARY_OP_WITH_SCALAR_QUERY_TEMPLATE =
            "let $a := coverage newCov\r\n" + //
                        "domain crs \"OGC:Index1D\" with\r\n" + //
                        "x index (1:5)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "{0}\r\n" + //
                        "return encode({1}, \"application/json\")";
    
    private static final String INDUCED_BINARY_OP_WITH_SCALAR_ORACLE_TEMPLATE =
            "[{0},{0},{0},{0},{0}]";
    
    private static QueryAndOracle injectArgumentsScalar(String constant, String expression, String expected) {
        return new QueryAndOracle(
            MessageFormat.format(INDUCED_BINARY_OP_WITH_SCALAR_QUERY_TEMPLATE, constant, expression),
            MessageFormat.format(INDUCED_BINARY_OP_WITH_SCALAR_ORACLE_TEMPLATE, expected)
        );
    }

    public static final QueryAndOracle MUL = injectArgumentsScalar("2", "3 * $a", "6");

    public static final QueryAndOracle DIV = injectArgumentsScalar("10", "$a / 2", "5");

    public static final QueryAndOracle EQ = injectArgumentsScalar("2", "2 = $a", "true");

    public static final QueryAndOracle NEQ = injectArgumentsScalar("2", "$a != 2", "false");

    public static final QueryAndOracle GREATER = injectArgumentsScalar("2", "3 > $a", "true");

    public static final QueryAndOracle LESS = injectArgumentsScalar("4", "$a < 3", "false");

    public static final QueryAndOracle GREATER_EQ = injectArgumentsScalar("2", "1 >= $a", "false");

    public static final QueryAndOracle LESS_EQ = injectArgumentsScalar("2", "$a <= 2", "true");
}
