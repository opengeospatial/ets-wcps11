package org.opengis.cite.wcps11.testdata;

import java.text.MessageFormat;

public class CondenseOperations {
    
    private static final String REDUCE_OP_QUERY_TEMPLATE =
          "let $a := (coverage newCov\r\n" + //
                        "domain crs \"OGC:Index4D\" with\r\n" + //
                        "x index (0:5),\r\n" + //
                        "y index (0:5),\r\n" + //
                        "z index (0:5),\r\n" + //
                        "t index (0:5)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "x + y + z + t)\r\n" + //
                        "return {0}";
    
    public static final QueryAndOracle REDUCE_SUM = new QueryAndOracle(
        MessageFormat.format(REDUCE_OP_QUERY_TEMPLATE, "add($a)"),
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

    public static final QueryAndOracle CONDENSE_ADD = new QueryAndOracle(
        "return condense + over px x (1:5) using px",
        "15"
    );

    public static final QueryAndOracle CONDENSE_MUL = new QueryAndOracle(
        "return condense * over px x (1:5) where px > 2 using px",
        "60"
    );

    public static final QueryAndOracle CONDENSE_MAX = new QueryAndOracle(
        "return condense max over px x (1:5), py y (1:5) where px != py using px + py",
        "9"
    );

    public static final QueryAndOracle CONDENSE_MIN = new QueryAndOracle(
        "return condense min over px x (1:5), py y (1:5) where px != py using px + py",
        "3"
    );

    public static final QueryAndOracle CONDENSE_OR = new QueryAndOracle(
        "return condense or over px x (1:5), py y (1:5) where px != py using px > 3 and py > 3",
        "true"
    );

    public static final QueryAndOracle CONDENSE_AND = new QueryAndOracle(
        "return condense and over px x (1:5), py y (1:5) using px != py",
        "false"
    );

    public static final QueryAndOracle UNSUPPORTED_OP = new QueryAndOracle(
        MessageFormat.format(REDUCE_OP_QUERY_TEMPLATE, "count($a)")
    );
}
