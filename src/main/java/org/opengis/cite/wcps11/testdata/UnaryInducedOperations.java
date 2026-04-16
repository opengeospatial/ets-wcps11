package org.opengis.cite.wcps11.testdata;

import java.text.MessageFormat;

public class UnaryInducedOperations {
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
    
    private static QueryAndOracle injectArguments(String constant, String expression, String expected) {
        return new QueryAndOracle(
            MessageFormat.format(INDUCED_UNARY_OP_QUERY_TEMPLATE, constant, expression),
            MessageFormat.format(INDUCED_UNARY_OP_ORACLE_TEMPLATE, expected)
        );
    }

    public static final QueryAndOracle MINUS = injectArguments("1", "-$a", "-1");

    public static final QueryAndOracle PLUS = injectArguments("-2", "+$a", "2");

    public static final QueryAndOracle ABS = injectArguments("-3", "abs($a)", "3");

    public static final QueryAndOracle SQRT = injectArguments("9", "sqrt($a)", "3");

    public static final QueryAndOracle SIN = injectArguments("1", "sin($a)", "0.84147096");

    public static final QueryAndOracle COS = injectArguments("1", "cos($a)", "0.5403023");

    public static final QueryAndOracle TAN = injectArguments("1", "tan($a)", "1.5574077");

    public static final QueryAndOracle SINH = injectArguments("1", "sinh($a)", "1.1752012");

    public static final QueryAndOracle COSH = injectArguments("1", "cosh($a)", "1.5430806");

    public static final QueryAndOracle TANH = injectArguments("1", "tanh($a)", "0.7615942");
    
    public static final QueryAndOracle ASIN = injectArguments("1", "arcsin($a)", "1.5707964");

    public static final QueryAndOracle ACOS = injectArguments("1", "arccos($a)", "0.0");

    public static final QueryAndOracle ATAN = injectArguments("1", "arctan($a)", "0.7853982");

    public static final QueryAndOracle EXP = injectArguments("1", "exp($a)", "2.7182817");

    public static final QueryAndOracle LOG = injectArguments("2", "log($a)", "0.30103");

    public static final QueryAndOracle LN = injectArguments("2", "ln($a)", "0.6931472");

    public static final QueryAndOracle POW = injectArguments("2", "pow($a, 3.5)", "11.3137085");

    public static final QueryAndOracle CAST = new QueryAndOracle(
        "let $a := coverage newCov\r\n" + //
                        "domain crs \"OGC:Index2D\" with\r\n" + //
                        "x index (0:1)\r\n," + //
                        "y index (0:2)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity float\r\n" + //
                        "range set\r\n" + //
                        "2.44\r\n" + //
                        "return encode((int)$a, \"application/json\")",
        "[[2,2,2], [2,2,2]]"
    );

    public static final QueryAndOracle FIELD_EXPR = new QueryAndOracle(
        "let $a := coverage newCov\r\n" + //
                        "domain crs \"OGC:Index2D\" with\r\n" + //
                        "x index (1:5),\r\n" + //
                        "y index (4:8)\r\n" + //
                        "range type\r\n" + //
                        "field1 quantity int,\r\n" + //
                        "field2 quantity int\r\n" + //
                        "range set\r\n" + //
                        "{field1: 1, field2: 2}\r\n" + //
                        "return encode($a.field1, \"application/json\")\r\n",
        "[[1,1,1,1,1], [1,1,1,1,1], [1,1,1,1,1], [1,1,1,1,1], [1,1,1,1,1]]"
    );

}
