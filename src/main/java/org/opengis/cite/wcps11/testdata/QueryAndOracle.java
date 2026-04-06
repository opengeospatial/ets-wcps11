package org.opengis.cite.wcps11.testdata;

/**
 * A simple data structure to hold a query and its corresponding oracle, along with an optional file path for any required upload.
 */
public class QueryAndOracle {
    public final String query;
    public final String oracle;
    public final String uploadFilePath;

    public QueryAndOracle(String query, String oracle, String uploadFilePath) {
        this.query = query;
        this.oracle = oracle;
        this.uploadFilePath = uploadFilePath;
    }

    public QueryAndOracle(String query, String oracle) {
        this(query, oracle, null);
    }

    public QueryAndOracle(String query) {
        this(query, null, null);
    }
}
