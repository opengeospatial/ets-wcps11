package org.opengis.cite.wcps11.testdata;

public class TestData {

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
        "return encode(coverage newCov " + //
                        "domain crs \"OGC:Index1D\" with " + //
                        "x index (1:5) " + //
                        "range type " + //
                        "elem quantity int " + //
                        "range set " + //
                        "1, \"application/json\")",
        "[1,1,1,1,1]"
    );

    public static final QueryAndOracle CONSTRUCTOR_1D_ITERATOR = new QueryAndOracle(
        "return encode(coverage newCov\r\n" + //
                        "domain crs \"OGC:Index1D\" with\r\n" + //
                        "x index (1:5)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "x, \"application/json\")\r\n",
        "[1,2,3,4,5]"
    );

    public static final QueryAndOracle ADDITION_IN_RANGE_SET_1 = new QueryAndOracle(
        "return encode(coverage newCov\r\n" + //
                        "domain crs \"OGC:Index1D\" with\r\n" + //
                        "x index (1:5)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "1 + 1, \"application/json\")\r\n",
        "[2,2,2,2,2]"
    );

    public static final QueryAndOracle ADDITION_IN_RANGE_SET_2 = new QueryAndOracle(
        "return encode(coverage newCov\r\n" + //
                        "domain crs \"OGC:Index1D\" with\r\n" + //
                        "x index (1:5)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "x + 1, \"application/json\")",
        "[2,3,4,5,6]"
    );

    public static final QueryAndOracle CONSTRUCTOR_2D = new QueryAndOracle(
        "return encode(coverage newCov\r\n" + //
                        "domain crs \"OGC:Index2D\" with\r\n" + //
                        "x index (1:5),\r\n" + //
                        "y index (4:8)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "x + y, \"application/json\")\r\n"
        , "[[5,6,7,8,9],[6,7,8,9,10],[7,8,9,10,11],[8,9,10,11,12],[9,10,11,12,13]]"
    );

    public static final QueryAndOracle CONSTRUCTOR_3D = new QueryAndOracle(
        "return encode(coverage newCov\r\n" + //
                        "domain crs \"OGC:Index3D\" with\r\n" + //
                        "x index (0:2),\r\n" + //
                        "y index (0:2),\r\n" + //
                        "z index (0:2)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "x + y + z, \"application/json\")",
        "[[[0,1,2],[1,2,3],[2,3,4]],[[1,2,3],[2,3,4],[3,4,5]],[[2,3,4],[3,4,5],[4,5,6]]]"
    );

    public static final QueryAndOracle CONSTRUCTOR_4D = new QueryAndOracle(
        "return encode(coverage newCov\r\n" + //
                        "domain crs \"OGC:Index4D\" with\r\n" + //
                        "x index (0:2),\r\n" + //
                        "y index (0:2),\r\n" + //
                        "z index (0:2),\r\n" + //
                        "t index (0:2)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "x + y + z + t, \"application/json\")",
        "[[[[0,1,2],[1,2,3],[2,3,4]],[[1,2,3],[2,3,4],[3,4,5]],[[2,3,4],[3,4,5],[4,5,6]]],[[[1,2,3],[2,3,4],[3,4,5]],[[2,3,4],[3,4,5],[4,5,6]],[[3,4,5],[4,5,6],[5,6,7]]],[[[2,3,4],[3,4,5],[4,5,6]],[[3,4,5],[4,5,6],[5,6,7]],[[4,5,6],[5,6,7],[6,7,8]]]]"
    );

    public static final QueryAndOracle CONSTRUCTOR_5D = new QueryAndOracle(
        "return encode(coverage newCov\r\n" + //
                        "domain crs \"OGC:Index5D\" with\r\n" + //
                        "x index (0:1),\r\n" + //
                        "y index (0:1),\r\n" + //
                        "z index (0:1),\r\n" + //
                        "t index (0:1),\r\n" + //
                        "s index (0:1)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "x + y + z + t + s, \"application/json\")\r\n",
        "[[[[[0,1],[1,2]],[[1,2],[2,3]]],[[[1,2],[2,3]],[[2,3],[3,4]]]],[[[[1,2],[2,3]],[[2,3],[3,4]]],[[[2,3],[3,4]],[[3,4],[4,5]]]]]"
    );

    public static final QueryAndOracle OTHER_CRS_AND_AXIS_TYPES_1 = new QueryAndOracle(
        "return encode(coverage newCov\r\n" + //
                        "domain crs \"EPSG:4326\" with\r\n" + //
                        "lat regular (-2:2) resolution -0.5,\r\n" + //
                        "long regular (-2:2) resolution 1\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "1, \"application/json\")\r\n", 
        "[[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1],[1,1,1,1]]"
    );

    public static final QueryAndOracle OTHER_CRS_AND_AXIS_TYPES_2 = new QueryAndOracle(
        "return encode(coverage newCov\r\n" + //
                        "domain crs \"OGC:AnsiDate\" with\r\n" + //
                        "day irregular (\"2024-01-01\", \"2025-01-01\", \"2026-01-01\")\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "1, \"application/json\")",
        "[1,1,1]"
    );

    public static final QueryAndOracle CRS_COMBINATION = new QueryAndOracle(
        "return encode(coverage newCov\r\n" + //
                        "domain crs \"EPSG:4326+OGC:AnsiDate\" with\r\n" + //
                        "lat regular (-2:2) resolution -0.5,\r\n" + //
                        "long regular (-2:2) resolution 1,\r\n" + //
                        "day irregular (\"2024-01-01\", \"2025-01-01\", \"2026-01-01\")\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "1, \"application/json\")",
        "[[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]],[[1,1,1],[1,1,1],[1,1,1],[1,1,1]]]"
    );

    public static final QueryAndOracle MULTI_FIELD_RANGE_1 = new QueryAndOracle(
        "return encode(coverage newCov\r\n" + //
                        "domain crs \"OGC:Index2D\" with\r\n" + //
                        "x index (1:5),\r\n" + //
                        "y index (4:8)\r\n" + //
                        "range type\r\n" + //
                        "field1 quantity int,\r\n" + //
                        "field2 quantity int\r\n" + //
                        "range set\r\n" + //
                        "{field1: 1, field2: 2}, \"application/json\")\r\n", 
        "[[\"1 2\",\"1 2\",\"1 2\",\"1 2\",\"1 2\"],[\"1 2\",\"1 2\",\"1 2\",\"1 2\",\"1 2\"],[\"1 2\",\"1 2\",\"1 2\",\"1 2\",\"1 2\"],[\"1 2\",\"1 2\",\"1 2\",\"1 2\",\"1 2\"],[\"1 2\",\"1 2\",\"1 2\",\"1 2\",\"1 2\"]]"
    );

    public static final QueryAndOracle MULTI_FIELD_RANGE_2 = new QueryAndOracle(
        "return encode(coverage newCov\r\n" + //
                        "domain crs \"OGC:Index3D\" with\r\n" + //
                        "x index (0:4),\r\n" + //
                        "y index (0:4),\r\n" + //
                        "z index (0:4)\r\n" + //
                        "range type\r\n" + //
                        "field1 quantity int,\r\n" + //
                        "field2 quantity int\r\n" + //
                        "range set\r\n" + //
                        "{field1: x + y, field2: y + z}, \"application/json\")",
        "[[[\"0 0\",\"0 1\",\"0 2\",\"0 3\",\"0 4\"],[\"1 1\",\"1 2\",\"1 3\",\"1 4\",\"1 5\"],[\"2 2\",\"2 3\",\"2 4\",\"2 5\",\"2 6\"],[\"3 3\",\"3 4\",\"3 5\",\"3 6\",\"3 7\"],[\"4 4\",\"4 5\",\"4 6\",\"4 7\",\"4 8\"]],[[\"1 0\",\"1 1\",\"1 2\",\"1 3\",\"1 4\"],[\"2 1\",\"2 2\",\"2 3\",\"2 4\",\"2 5\"],[\"3 2\",\"3 3\",\"3 4\",\"3 5\",\"3 6\"],[\"4 3\",\"4 4\",\"4 5\",\"4 6\",\"4 7\"],[\"5 4\",\"5 5\",\"5 6\",\"5 7\",\"5 8\"]],[[\"2 0\",\"2 1\",\"2 2\",\"2 3\",\"2 4\"],[\"3 1\",\"3 2\",\"3 3\",\"3 4\",\"3 5\"],[\"4 2\",\"4 3\",\"4 4\",\"4 5\",\"4 6\"],[\"5 3\",\"5 4\",\"5 5\",\"5 6\",\"5 7\"],[\"6 4\",\"6 5\",\"6 6\",\"6 7\",\"6 8\"]],[[\"3 0\",\"3 1\",\"3 2\",\"3 3\",\"3 4\"],[\"4 1\",\"4 2\",\"4 3\",\"4 4\",\"4 5\"],[\"5 2\",\"5 3\",\"5 4\",\"5 5\",\"5 6\"],[\"6 3\",\"6 4\",\"6 5\",\"6 6\",\"6 7\"],[\"7 4\",\"7 5\",\"7 6\",\"7 7\",\"7 8\"]],[[\"4 0\",\"4 1\",\"4 2\",\"4 3\",\"4 4\"],[\"5 1\",\"5 2\",\"5 3\",\"5 4\",\"5 5\"],[\"6 2\",\"6 3\",\"6 4\",\"6 5\",\"6 6\"],[\"7 3\",\"7 4\",\"7 5\",\"7 6\",\"7 7\"],[\"8 4\",\"8 5\",\"8 6\",\"8 7\",\"8 8\"]]]"
    );

    // --- Induced operations test cases ---

    public static final QueryAndOracle BOOLEAN_OP_ON_COV = new QueryAndOracle(
        "return encode((coverage newCov\r\n" + //
                        "domain crs \"OGC:Index1D\" with\r\n" + //
                        "x index (1:5)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "x) > 2, \"application/json\")",
        "[false,false,true,true,true]"
    );

    public static final QueryAndOracle SCALAR_ADDITION_ON_COV = new QueryAndOracle(
        "return encode((coverage newCov\r\n" + //
                        "domain crs \"OGC:Index2D\" with\r\n" + //
                        "x index (1:5),\r\n" + //
                        "y index (4:8)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "x + y) + 10, \"application/json\")",
        "[[15,16,17,18,19],[16,17,18,19,20],[17,18,19,20,21],[18,19,20,21,22],[19,20,21,22,23]]"
    );

    // --- letExpr test cases ---

    public static final QueryAndOracle LET_SCALAR = new QueryAndOracle(
        "let $a := 2 return $a", 
        "2"
    );

    public static final QueryAndOracle LET_MUTI = new QueryAndOracle(
        "let $a := 2, $b := 10 return $a + $b",
        "12"
    );

    public static final QueryAndOracle LET_COV = new QueryAndOracle(
        "let $a := coverage newCov\r\n" + //
                        "domain crs \"OGC:Index1D\" with\r\n" + //
                        "x index (1:5)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "1\r\n" + //
                        "return encode($a, \"application/json\")",
        "[1,1,1,1,1]"
    );

    public static final QueryAndOracle LET_LATER_USAGE = new QueryAndOracle(
        "let $val := 1\r\n" + //
                        "return encode(coverage newCov\r\n" + //
                        "domain crs \"OGC:Index1D\" with\r\n" + //
                        "x index (1:5)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "$val, \"application/json\")",
        "[1,1,1,1,1]"
    );

    // --- switchExpr test cases ---

    public static final QueryAndOracle SWITCH_SCALAR = new QueryAndOracle(
        "return (switch case 1 < 0 return 4 default return 2)",
        "2"
    );

    public static final QueryAndOracle SWITCH_MULTI_CASE = new QueryAndOracle(
        "return (switch\r\n" + //
                        "case 1 < 0\r\n" + //
                        "return 4\r\n" + //
                        "case 1 > 0\r\n" + //
                        "return 5\r\n" + //
                        "default return 2)",
        "5"
    );

    public static final QueryAndOracle SWITCH_COV = new QueryAndOracle(
        "let $a := coverage newCov\r\n" + //
                        "domain crs \"OGC:Index3D\" with\r\n" + //
                        "x index (0:3),\r\n" + //
                        "y index (0:3),\r\n" + //
                        "z index (0:3)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "x + y + z\r\n" + //
                        "return encode(switch\r\n" + //
                        "case $a > 5\r\n" + //
                        "return 6\r\n" + //
                        "case $a > 3\r\n" + //
                        "return 4\r\n" + //
                        "case $a > 1\r\n" + //
                        "return 2\r\n" + //
                        "default\r\n" + //
                        "return 0, \"application/json\")",
        "[[[0,0,2,2],[0,2,2,4],[2,2,4,4],[2,4,4,6]],[[0,2,2,4],[2,2,4,4],[2,4,4,6],[4,4,6,6]],[[2,2,4,4],[2,4,4,6],[4,4,6,6],[4,6,6,6]],[[2,4,4,6],[4,4,6,6],[4,6,6,6],[6,6,6,6]]]"
    );

    public static final QueryAndOracle SWITCH_COV_OUTPUT = new QueryAndOracle(
        "let $a := coverage newCov\r\n" + //
                        "domain crs \"OGC:Index2D\" with\r\n" + //
                        "x index (1:5),\r\n" + //
                        "y index (4:8)\r\n" + //
                        "range type\r\n" + //
                        "elem quantity int\r\n" + //
                        "range set\r\n" + //
                        "x + y,\r\n" + //
                        "$b := $a + 100\r\n" + //
                        "return encode(switch case $a > 8 return $a default return $b,\r\n" + //
                        "\"application/json\")",
        "[[105,106,107,108,9],[106,107,108,9,10],[107,108,9,10,11],[108,9,10,11,12],[9,10,11,12,13]]"
    );

    public static final QueryAndOracle DUPLICATE_CONST_NAME = new QueryAndOracle(
        "let $a := 2, $a := 4 return $a"
    );

    public static final QueryAndOracle CRS_AXES_MISMATCH = new QueryAndOracle(
        "return encode(coverage newCov domain crs \"OGC:Index1D\" with x index (1:5), y index (4:8) range type elem quantity int range set x + y, \"application/json\")"
    );
}
