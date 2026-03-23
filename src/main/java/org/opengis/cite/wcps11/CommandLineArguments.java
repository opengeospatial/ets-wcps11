package org.opengis.cite.wcps11;

import com.beust.jcommander.Parameter;
import java.io.File;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

/**
 * Declares supported command line arguments that are parsed using the
 * JCommander library. All arguments are optional. The default values are as
 * follows:
 * <ul>
 * <li>XML properties file: ${user.home}/test-run-props.xml</li>
 * <li>outputDir: ${user.home}</li>
 * <li>deleteSubjectOnFinish: false</li>
 * </ul>
 *
 * <p>
 * <strong>Synopsis</strong>
 * </p>
 *
 * <pre>
 * ets-${ets-code}-${version}-aio.jar [-o|--outputDir $TMPDIR] [-d|--deleteSubjectOnFinish] [test-run-props.xml]
 * </pre>
 */
public class CommandLineArguments {

    @Parameter(description = "Properties file")
    private final List<String> xmlProps;

    @Parameter(names = {"-o", "--outputDir"}, description = "Output directory")
    private String outputDir;

    @Parameter(names = {"-d", "--deleteSubjectOnFinish"}, description = "Delete file containing representation of test subject when finished")
    private boolean deleteSubjectOnFinish = false;
    /**
     * Constructs a new {@code CommandLineArguments} instance with default
     * values.
     */
    public CommandLineArguments() {
        this.xmlProps = new ArrayList<>();
    }

    /**
     * Returns the properties file specified on the command line or the
     * default properties file located in the user's home directory.
     *
     * @return a {@code File} referencing the properties file to use
     */
    public File getPropertiesFile() {
        File fileRef;
        if (xmlProps.isEmpty()) {
            fileRef = new File(System.getProperty("user.home"), "test-run-props.xml");
        } else {
            String propsFile = xmlProps.get(0);
            fileRef = (propsFile.startsWith("file:")) ? new File(URI.create(propsFile)) : new File(propsFile);
        }
        return fileRef;
    }
    /**
     * Returns the output directory specified on the command line or the
     * user's home directory when no output directory was provided.
     *
     * @return the output directory path as a {@code String}
     */
    public String getOutputDir() {
        return (null != outputDir) ? outputDir : System.getProperty("user.home");
    }

    /**
     * Indicates whether the file containing a representation of the test
     * subject should be deleted when the test run finishes.
     *
     * @return {@code true} if the subject file should be deleted; {@code false}
     *         otherwise
     */
    public boolean doDeleteSubjectOnFinish() {
        return deleteSubjectOnFinish;
    }
}
