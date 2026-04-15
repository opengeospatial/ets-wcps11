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

    private static final String INDUCED_BINARY_OP_WITH_COV_QUERY_TEMPLATE = 
            "let $a := coverage newCov1\r\n" + //
                        "domain crs \"OGC:Index3D\" with\r\n" + //
                        "x index (0:1),\r\n" + //
                        "y index (0:1),\r\n" + //
                        "z index (0:1)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "{0},\r\n" + //
                  "$b := coverage newCov2\r\n" + //
                        "domain crs \"OGC:Index3D\" with\r\n" + //
                        "x index (0:1),\r\n" + //
                        "y index (0:1),\r\n" + //
                        "z index (0:1)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "{1}\r\n" + //
                        "return encode({2}, \"application/json\")";
    
    private static final String INDUCED_BINARY_OP_WITH_COV_ORACLE_TEMPLATE = 
            "[[[{0},{0}],[{0},{0}]],[[{0},{0}],[{0},{0}]]]";
    
    private static QueryAndOracle injectArgumentsScalar(String constant, String expression, String expected) {
        return new QueryAndOracle(
            MessageFormat.format(INDUCED_BINARY_OP_WITH_SCALAR_QUERY_TEMPLATE, constant, expression),
            MessageFormat.format(INDUCED_BINARY_OP_WITH_SCALAR_ORACLE_TEMPLATE, expected)
        );
    }

    private static QueryAndOracle injectArgumentsCov(String cov1Constant, String cov2Constant, String expression, String expected) {
        return new QueryAndOracle(
            MessageFormat.format(INDUCED_BINARY_OP_WITH_COV_QUERY_TEMPLATE, cov1Constant, cov2Constant, expression),
            MessageFormat.format(INDUCED_BINARY_OP_WITH_COV_ORACLE_TEMPLATE, expected)
        );
    }

    public static final QueryAndOracle MUL_SCALAR = injectArgumentsScalar("2", "3 * $a", "6");

    public static final QueryAndOracle DIV_SCALAR = injectArgumentsScalar("10", "$a / 2", "5");

    public static final QueryAndOracle EQ_SCALAR = injectArgumentsScalar("2", "2 = $a", "true");

    public static final QueryAndOracle NEQ_SCALAR = injectArgumentsScalar("2", "$a != 2", "false");

    public static final QueryAndOracle GREATER_SCALAR = injectArgumentsScalar("2", "3 > $a", "true");

    public static final QueryAndOracle LESS_SCALAR = injectArgumentsScalar("4", "$a < 3", "false");

    public static final QueryAndOracle GREATER_EQ_SCALAR = injectArgumentsScalar("2", "1 >= $a", "false");

    public static final QueryAndOracle LESS_EQ_SCALAR = injectArgumentsScalar("2", "$a <= 2", "true");

    public static final QueryAndOracle MUL_COV = injectArgumentsCov("2", "2", "$a * $b", "4");

    public static final QueryAndOracle DIV_COV = injectArgumentsCov("9", "3", "$a / $b", "3");

    public static final QueryAndOracle EQ_COV = injectArgumentsCov("2", "2", "$a = $b", "true");

    public static final QueryAndOracle NEQ_COV = injectArgumentsCov("2", "3", "$a != $b", "true");

    public static final QueryAndOracle GREATER_COV = injectArgumentsCov("3", "2", "$a > $b", "true");

    public static final QueryAndOracle LESS_COV = injectArgumentsCov("5", "5", "$a < $b", "false");

    public static final QueryAndOracle GREATER_EQ_COV = injectArgumentsCov("2", "2", "$a >= $b", "true");

    public static final QueryAndOracle LESS_EQ_COV = injectArgumentsCov("2", "1", "$a <= $b", "false");

}
