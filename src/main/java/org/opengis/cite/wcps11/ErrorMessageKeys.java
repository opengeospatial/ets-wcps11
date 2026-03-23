package org.opengis.cite.wcps11;

/**
 * Defines keys used to access localized messages for assertion errors. The
 * messages are stored in Properties files that are encoded in ISO-8859-1
 * (Latin-1). For some languages the {@code native2ascii} tool must be used to
 * process the files and produce escaped Unicode characters.
 */
public class ErrorMessageKeys {
    /** Message key for schema validation failure. */
    public static final String NOT_SCHEMA_VALID = "NotSchemaValid";

    /** Message key indicating an empty string where content was expected. */
    public static final String EMPTY_STRING = "EmptyString";

    /** Message key for XPath result-related errors. */
    public static final String XPATH_RESULT = "XPathResult";

    /** Message key for namespace name problems. */
    public static final String NAMESPACE_NAME = "NamespaceName";

    /** Message key for local name problems. */
    public static final String LOCAL_NAME = "LocalName";

    /** Message key for generic XML processing errors. */
    public static final String XML_ERROR = "XMLError";

    /** Message key for XPath expression errors. */
    public static final String XPATH_ERROR = "XPathError";

    /** Message key when an expected infoset item is missing. */
    public static final String MISSING_INFOSET_ITEM = "MissingInfosetItem";

    /** Message key for unexpected HTTP status codes. */
    public static final String UNEXPECTED_STATUS = "UnexpectedStatus";

    /** Message key for unexpected media (MIME) types. */
    public static final String UNEXPECTED_MEDIA_TYPE = "UnexpectedMediaType";

    /** Message key for a missing entity reference. */
    public static final String MISSING_ENTITY = "MissingEntity";
}
