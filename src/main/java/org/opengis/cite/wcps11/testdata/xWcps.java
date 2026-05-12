package org.opengis.cite.wcps11.testdata;

public class xWcps {

    // --- Elementary operations test cases ---
    public static final QueryAndOracle RETURN_NUMERIC_LITERAL = new QueryAndOracle(
        "return 2",
        "2"
    );

    public static final QueryAndOracle SCALAR_ADDITION_1 = new QueryAndOracle(
        "return 1 + 3",
        "4"
    );

    public static final QueryAndOracle SCALAR_ADDITION_2 = new QueryAndOracle(
        "return 10 + 1 + 17",
        "28"
    );

    public static final QueryAndOracle BOOLEAN_OP_1 = new QueryAndOracle(
        "return 11 < 32", 
        "true"
    );

    public static final QueryAndOracle BOOLEAN_OP_2 = new QueryAndOracle(
        "return 11 > 32",
        "false"
    );


    // --- xWcpsCoverageConstructor test cases ---

    public static final QueryAndOracle CONSTRUCTOR_1D_BASIC = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"OGC:Index1D\" with",
            "x index (1:5)",
            "range type",
            "elem quantity int",
            "range set",
            "1, \"application/json\")"
        ),
        "[1,1,1,1,1]"
    );

    public static final QueryAndOracle CONSTRUCTOR_1D_ITERATOR = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"OGC:Index1D\" with",
            "x index (1:5)",
            "range type",
            "elem quantity int",
            "range set",
            "x, \"application/json\")"
        ),
        "[1,2,3,4,5]"
    );

    public static final QueryAndOracle ADDITION_IN_RANGE_SET_1 = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"OGC:Index1D\" with",
            "x index (1:5)",
            "range type",
            "elem quantity int",
            "range set",
            "1 + 1, \"application/json\")"
        ),
        "[2,2,2,2,2]"
    );

    public static final QueryAndOracle ADDITION_IN_RANGE_SET_2 = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"OGC:Index1D\" with",
            "x index (1:5)",
            "range type",
            "elem quantity int",
            "range set",
            "x + 1, \"application/json\")"
        ),
        "[2,3,4,5,6]"
    );

    public static final QueryAndOracle CONSTRUCTOR_2D = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"OGC:Index2D\" with",
            "x index (1:5),",
            "y index (4:8)",
            "range type",
            "elem quantity int",
            "range set",
            "x + y, \"application/json\")"
        ),
        "[[5,6,7,8,9],[6,7,8,9,10],[7,8,9,10,11],[8,9,10,11,12],[9,10,11,12,13]]"
    );

    public static final QueryAndOracle CONSTRUCTOR_3D = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"OGC:Index3D\" with",
            "x index (0:2),",
            "y index (0:2),",
            "z index (0:2)",
            "range type",
            "elem quantity int",
            "range set",
            "x + y + z, \"application/json\")"
        ),
        "[[[0,1,2],[1,2,3],[2,3,4]],[[1,2,3],[2,3,4],[3,4,5]],[[2,3,4],[3,4,5],[4,5,6]]]"
    );

    public static final QueryAndOracle CONSTRUCTOR_4D = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"OGC:Index4D\" with",
            "x index (0:2),",
            "y index (0:2),",
            "z index (0:2),",
            "t index (0:2)",
            "range type",
            "elem quantity int",
            "range set",
            "x + y + z + t, \"application/json\")"
        ),
        "[[[[0,1,2],[1,2,3],[2,3,4]],[[1,2,3],[2,3,4],[3,4,5]],[[2,3,4],[3,4,5],[4,5,6]]],[[[1,2,3],[2,3,4],[3,4,5]],[[2,3,4],[3,4,5],[4,5,6]],[[3,4,5],[4,5,6],[5,6,7]]],[[[2,3,4],[3,4,5],[4,5,6]],[[3,4,5],[4,5,6],[5,6,7]],[[4,5,6],[5,6,7],[6,7,8]]]]"
    );

    public static final QueryAndOracle CONSTRUCTOR_5D = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"OGC:Index5D\" with",
            "x index (0:1),",
            "y index (0:1),",
            "z index (0:1),",
            "t index (0:1),",
            "s index (0:1)",
            "range type",
            "elem quantity int",
            "range set",
            "x + y + z + t + s, \"application/json\")"
        ),
        "[[[[[0,1],[1,2]],[[1,2],[2,3]]],[[[1,2],[2,3]],[[2,3],[3,4]]]],[[[[1,2],[2,3]],[[2,3],[3,4]]],[[[2,3],[3,4]],[[3,4],[4,5]]]]]"
    );

    public static final QueryAndOracle OTHER_CRS_AND_AXIS_TYPES_1 = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"EPSG:4326\" with",
            "lat regular (-2:2) resolution -0.5,",
            "long regular (-2:2) resolution 1",
            "range type",
            "elem quantity int",
            "range set",
            "1, \"application/json\")"
        ), 
        "[[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1]]"
    );

    public static final QueryAndOracle OTHER_CRS_AND_AXIS_TYPES_2 = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"OGC:AnsiDate\" with",
            "day irregular (\"2024-01-01\", \"2025-01-01\", \"2026-01-01\")",
            "range type",
            "elem quantity int",
            "range set",
            "1, \"application/json\")"
        ),
        "[1,1,1]"
    );

    public static final QueryAndOracle CRS_COMBINATION = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"EPSG:4326+OGC:AnsiDate\" with",
            "lat regular (-2:2) resolution -0.5,",
            "long regular (-2:2) resolution 1,",
            "day irregular (\"2024-01-01\", \"2025-01-01\", \"2026-01-01\")",
            "range type",
            "elem quantity int",
            "range set",
            "1, \"application/json\")"
        ),
        "[[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]]]"
    );

    public static final QueryAndOracle MULTI_FIELD_RANGE_1 = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"OGC:Index2D\" with",
            "x index (1:5),",
            "y index (4:8)",
            "range type",
            "field1 quantity int,",
            "field2 quantity int",
            "range set",
            "{field1: 1, field2: 2}, \"application/json\")"
        ), 
        "[[\"1 2\",\"1 2\",\"1 2\",\"1 2\",\"1 2\"],[\"1 2\",\"1 2\",\"1 2\",\"1 2\",\"1 2\"],[\"1 2\",\"1 2\",\"1 2\",\"1 2\",\"1 2\"],[\"1 2\",\"1 2\",\"1 2\",\"1 2\",\"1 2\"],[\"1 2\",\"1 2\",\"1 2\",\"1 2\",\"1 2\"]]"
    );

    public static final QueryAndOracle MULTI_FIELD_RANGE_2 = new QueryAndOracle(
        String.join("\r\n",
            "return encode(coverage newCov",
            "domain crs \"OGC:Index3D\" with",
            "x index (0:4),",
            "y index (0:4),",
            "z index (0:4)",
            "range type",
            "field1 quantity int,",
            "field2 quantity int",
            "range set",
            "{field1: x + y, field2: y + z}, \"application/json\")"
        ),
        "[[[\"0 0\",\"0 1\",\"0 2\",\"0 3\",\"0 4\"],[\"1 1\",\"1 2\",\"1 3\",\"1 4\",\"1 5\"],[\"2 2\",\"2 3\",\"2 4\",\"2 5\",\"2 6\"],[\"3 3\",\"3 4\",\"3 5\",\"3 6\",\"3 7\"],[\"4 4\",\"4 5\",\"4 6\",\"4 7\",\"4 8\"]],[[\"1 0\",\"1 1\",\"1 2\",\"1 3\",\"1 4\"],[\"2 1\",\"2 2\",\"2 3\",\"2 4\",\"2 5\"],[\"3 2\",\"3 3\",\"3 4\",\"3 5\",\"3 6\"],[\"4 3\",\"4 4\",\"4 5\",\"4 6\",\"4 7\"],[\"5 4\",\"5 5\",\"5 6\",\"5 7\",\"5 8\"]],[[\"2 0\",\"2 1\",\"2 2\",\"2 3\",\"2 4\"],[\"3 1\",\"3 2\",\"3 3\",\"3 4\",\"3 5\"],[\"4 2\",\"4 3\",\"4 4\",\"4 5\",\"4 6\"],[\"5 3\",\"5 4\",\"5 5\",\"5 6\",\"5 7\"],[\"6 4\",\"6 5\",\"6 6\",\"6 7\",\"6 8\"]],[[\"3 0\",\"3 1\",\"3 2\",\"3 3\",\"3 4\"],[\"4 1\",\"4 2\",\"4 3\",\"4 4\",\"4 5\"],[\"5 2\",\"5 3\",\"5 4\",\"5 5\",\"5 6\"],[\"6 3\",\"6 4\",\"6 5\",\"6 6\",\"6 7\"],[\"7 4\",\"7 5\",\"7 6\",\"7 7\",\"7 8\"]],[[\"4 0\",\"4 1\",\"4 2\",\"4 3\",\"4 4\"],[\"5 1\",\"5 2\",\"5 3\",\"5 4\",\"5 5\"],[\"6 2\",\"6 3\",\"6 4\",\"6 5\",\"6 6\"],[\"7 3\",\"7 4\",\"7 5\",\"7 6\",\"7 7\"],[\"8 4\",\"8 5\",\"8 6\",\"8 7\",\"8 8\"]]]"
    );

    // --- letExpr test cases ---

    public static final QueryAndOracle LET_SCALAR = new QueryAndOracle(
        "let $a := 2 return $a", 
        "2"
    );

    public static final QueryAndOracle LET_MULTI = new QueryAndOracle(
        "let $a := 2, $b := 10 return $a + $b",
        "12"
    );

    public static final QueryAndOracle LET_COV = new QueryAndOracle(
        String.join("\r\n",
            "let $a := (coverage newCov",
            "domain crs \"OGC:Index1D\" with",
            "x index (1:5)",
            "range type",
            "elem quantity int",
            "range set",
            "1)",
            "return encode($a, \"application/json\")"
        ),
        "[1,1,1,1,1]"
    );

    public static final QueryAndOracle LET_LATER_USAGE = new QueryAndOracle(
        String.join("\r\n",
            "let $val := (1)",
            "return encode(coverage newCov",
            "domain crs \"OGC:Index1D\" with",
            "x index (1:5)",
            "range type",
            "elem quantity int",
            "range set",
            "$val, \"application/json\")"
        ),
        "[1,1,1,1,1]"
    );

    // --- switchExpr test cases ---

    public static final QueryAndOracle SWITCH_SCALAR = new QueryAndOracle(
        "return (switch case 1 < 0 return 4 default return 2)",
        "2"
    );

    public static final QueryAndOracle SWITCH_MULTI_CASE = new QueryAndOracle(
        String.join("\r\n",
            "return (switch",
            "case 1 < 0",
            "return 4",
            "case 1 > 0",
            "return 5",
            "default return 2)"
        ),
        "5"
    );

    public static final QueryAndOracle SWITCH_COV = new QueryAndOracle(
        String.join("\r\n",
            "let $a := (coverage newCov",
            "domain crs \"OGC:Index3D\" with",
            "x index (0:3),",
            "y index (0:3),",
            "z index (0:3)",
            "range type",
            "elem quantity int",
            "range set",
            "x + y + z)",
            "return encode(switch",
            "case $a > 5",
            "return 6",
            "case $a > 3",
            "return 4",
            "case $a > 1",
            "return 2",
            "default",
            "return 0, \"application/json\")"
        ),
        "[[[0,0,2,2],[0,2,2,4],[2,2,4,4],[2,4,4,6]],[[0,2,2,4],[2,2,4,4],[2,4,4,6],[4,4,6,6]],[[2,2,4,4],[2,4,4,6],[4,4,6,6],[4,6,6,6]],[[2,4,4,6],[4,4,6,6],[4,6,6,6],[6,6,6,6]]]"
    );

    public static final QueryAndOracle SWITCH_COV_OUTPUT = new QueryAndOracle(
        String.join("\r\n",
            "let $a := (coverage newCov",
            "domain crs \"OGC:Index2D\" with",
            "x index (1:5),",
            "y index (4:8)",
            "range type",
            "elem quantity int",
            "range set",
            "x + y),",
            "$b := ($a + 100)",
            "return encode(switch case $a > 8 return $a default return $b,",
            "\"application/json\")"
        ),
        "[[105,106,107,108,9],[106,107,108,9,10],[107,108,9,10,11],[108,9,10,11,12],[9,10,11,12,13]]"
    );

    public static final QueryAndOracle DUPLICATE_CONST_NAME = new QueryAndOracle(
        "let $a := 2, $a := 4 return $a"
    );

    public static final QueryAndOracle CRS_AXES_MISMATCH = new QueryAndOracle(
        "return encode(coverage newCov domain crs \"OGC:Index1D\" with x index (1:5), y index (4:8) range type elem quantity int range set x + y, \"application/json\")"
    );
}
