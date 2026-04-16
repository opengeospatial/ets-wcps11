package org.opengis.cite.wcps11.testdata;

import java.text.MessageFormat;

public class CondenseOperations {
    
    private static final String REDUCE_OP_QUERY_TEMPLATE =
          "let $a := coverage newCov\r\n" + //
                        "domain crs \"OGC:Index4D\" with\r\n" + //
                        "x index (0:5),\r\n" + //
                        "y index (0:5),\r\n" + //
                        "z index (0:5),\r\n" + //
                        "t index (0:5)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "x + y + z + t\r\n" + //
                        "return {0}";
    
    public static final QueryAndOracle REDUCE_SUM = new QueryAndOracle(
        MessageFormat.format(REDUCE_OP_QUERY_TEMPLATE, "sum($a)"),
        "12960"
    );

    public static final QueryAndOracle REDUCE_AVG = new QueryAndOracle(
        MessageFormat.format(REDUCE_OP_QUERY_TEMPLATE, "avg($a)"),
        "10"
    );

    public static final QueryAndOracle REDUCE_MIN = new QueryAndOracle(
        MessageFormat.format(REDUCE_OP_QUERY_TEMPLATE, "min($a)"),
        "0"
    );

    public static final QueryAndOracle REDUCE_MAX = new QueryAndOracle(
        MessageFormat.format(REDUCE_OP_QUERY_TEMPLATE, "max($a)"),
        "20"
    );

    public static final QueryAndOracle REDUCE_COUNT = new QueryAndOracle(
        MessageFormat.format(REDUCE_OP_QUERY_TEMPLATE, "count($a > 10)"),
        "575"
    );
}
