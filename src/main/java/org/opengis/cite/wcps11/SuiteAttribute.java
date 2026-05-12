package org.opengis.cite.wcps11;

import org.opengis.cite.wcps11.util.WCPSWrapper;

/**
 * An enumerated type defining ISuite attributes that may be set to constitute a
 * shared test fixture.
 */
@SuppressWarnings("rawtypes")
public enum SuiteAttribute {

    /**
     * A wrapper for the WCPS endpoint
     */
    WCPS_WRAPPER("wcpsEndpoint", WCPSWrapper.class);
    private final Class attrType;
    private final String attrName;

    private SuiteAttribute(String attrName, Class attrType) {
        this.attrName = attrName;
        this.attrType = attrType;
    }

    /**
     * Returns the Java type of the attribute value.
     *
     * @return the {@code Class} used for the attribute's value
     */
    public Class getType() {
        return attrType;
    }

    /**
     * Returns the attribute name as used in the suite context.
     *
     * @return the attribute name
     */
    public String getName() {
        return attrName;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder(attrName);
        sb.append('(').append(attrType.getName()).append(')');
        return sb.toString();
    }
}
