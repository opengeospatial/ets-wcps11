package org.opengis.cite.wcps11.testdata;

import java.text.MessageFormat;

public class UnaryInducedOperations {
    private static final String INDUCED_UNARY_OP_QUERY_TEMPLATE = String.join("\r\n",
            "let $a := (coverage newCov",
            "domain crs \"OGC:Index1D\" with",
            "x index (1:5)",
            "range type",
            "elem quantity int",
            "range set",
            "{0})",
            "return encode({1}, \"application/json\")"
    );
    
    private static final String INDUCED_UNARY_OP_ORACLE_TEMPLATE =
            "[{0},{0},{0},{0},{0}]";
    
    private static QueryAndOracle injectArguments(String constant, String expression, String expected) {
        return new QueryAndOracle(
            MessageFormat.format(INDUCED_UNARY_OP_QUERY_TEMPLATE, constant, expression),
            MessageFormat.format(INDUCED_UNARY_OP_ORACLE_TEMPLATE, expected)
        );
    }

    public static final QueryAndOracle MINUS = injectArguments("1", "-$a", "-1");

    public static final QueryAndOracle PLUS = injectArguments("-2", "+$a", "-2");

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

    public static final QueryAndOracle BIT_1 = injectArguments("1", "bit($a, (unsigned int)0)", "true");

    public static final QueryAndOracle BIT_2 = injectArguments("1", "bit($a, (unsigned int)1)", "false");

    public static final QueryAndOracle NOT = new QueryAndOracle(
        String.join("\r\n",
            "let $a := (coverage newCov",
            "domain crs \"OGC:Index1D\" with",
            "x index (1:5)",
            "range type",
            "elem quantity boolean",
            "range set",
            "false)",
            "return encode(not($a), \"application/json\")"
        )
    );

    public static final QueryAndOracle CAST = new QueryAndOracle(
        String.join("\r\n",
            "let $a := (coverage newCov",
            "domain crs \"OGC:Index2D\" with",
            "x index (0:1),",
            "y index (0:2)",
            "range type",
            "elem quantity float",
            "range set",
            "2.44)",
            "return encode((int)$a, \"application/json\")"
        ),
        "[[2,2,2], [2,2,2]]"
    );

    public static final QueryAndOracle FIELD_EXPR = new QueryAndOracle(
        String.join("\r\n",
            "let $a := (coverage newCov",
            "domain crs \"OGC:Index2D\" with",
            "x index (1:5),",
            "y index (4:8)",
            "range type",
            "field1 quantity int,",
            "field2 quantity int",
            "range set",
            "{field1: 1, field2: 2})",
            "return encode($a.field1, \"application/json\")"
        ),
        "[[1,1,1,1,1], [1,1,1,1,1], [1,1,1,1,1], [1,1,1,1,1], [1,1,1,1,1]]"
    );

    public static final QueryAndOracle UNSUPPORTED_TYPE = new QueryAndOracle(
        MessageFormat.format(INDUCED_UNARY_OP_QUERY_TEMPLATE, "13", "not($a)")
    );
}
