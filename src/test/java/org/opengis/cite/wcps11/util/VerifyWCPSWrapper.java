package org.opengis.cite.wcps11.util;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.get;
import static com.github.tomakehurst.wiremock.client.WireMock.getRequestedFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlPathEqualTo;
import static com.github.tomakehurst.wiremock.client.WireMock.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.nio.charset.StandardCharsets;

import javax.ws.rs.core.MediaType;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import com.github.tomakehurst.wiremock.junit.WireMockRule;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

/**
 * Verifies the behavior of the WCPSWrapper class.
 */
public class VerifyWCPSWrapper {

    private static String capabilitiesXml;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(0);

    @BeforeClass
    public static void loadCapabilities() throws IOException {
        try (InputStream is = VerifyWCPSWrapper.class
                .getResourceAsStream("/wcs-capabilities.xml")) {
            capabilitiesXml = new String(is.readAllBytes(), StandardCharsets.UTF_8);
        }
    }

    /**
     * Builds a simple Jersey 1.x client (no special filters needed for the
     * test).
     */
    private Client buildTestClient() {
        return Client.create();
    }

    /**
     * Verifies that {@code sendQueryKVP} first issues a GetCapabilities request
     * to discover a coverage, then sends a ProcessCoverages request whose
     * {@code query} parameter is prefixed with the discovered for-clause.
     */
    @Test
    public void sendQueryKVP_prependsDiscoveredForClause() {
        // Stub the GetCapabilities request
        wireMockRule.stubFor(get(urlPathEqualTo("/wcs"))
                .withQueryParam("service", equalTo("WCS"))
                .withQueryParam("request", equalTo("GetCapabilities"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_XML)
                        .withBody(capabilitiesXml)));

        // Stub the ProcessCoverages request (return a simple response)
        wireMockRule.stubFor(get(urlPathEqualTo("/wcs"))
                .withQueryParam("service", equalTo("WCS"))
                .withQueryParam("request", equalTo("ProcessCoverages"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_XML)
                        .withBody("<result>OK</result>")));

        URI serviceEndpoint = URI.create(
                "http://localhost:" + wireMockRule.port() + "/wcs");
        Client client = buildTestClient();
        WCPSWrapper wrapper = new WCPSWrapper(serviceEndpoint, client);

        String userQuery = "return encode(__cov__, \"image/png\")";

        ClientResponse response = wrapper.sendQueryKVP(userQuery);

        assertNotNull("Response must not be null", response);
        assertEquals("HTTP status must be 200", 200, response.getStatus());

        // Verify the GetCapabilities request was made exactly once
        wireMockRule.verify(1, getRequestedFor(urlPathEqualTo("/wcs"))
                .withQueryParam("service", equalTo("WCS"))
                .withQueryParam("request", equalTo("GetCapabilities")));

        // Verify the ProcessCoverages request was made with the correct query
        // parameter that includes the injected for-clause prepended to the
        // user's query.
        String expectedQuery = "for __cov__ in ( AverageChloroColor )\n"
                + userQuery;

        wireMockRule.verify(1, getRequestedFor(urlPathEqualTo("/wcs"))
                .withQueryParam("service", equalTo("WCS"))
                .withQueryParam("request", equalTo("ProcessCoverages"))
                .withQueryParam("version", equalTo("2.0.1"))
                .withQueryParam("query", equalTo(expectedQuery)));
    }

    /**
     * Verifies that a second call to {@code sendQueryKVP} reuses the
     * for-clause discovered during the first call and does NOT issue another
     * GetCapabilities request.
     */
    @Test
    public void sendQueryKVP_cachedForClause_noSecondGetCapabilities() {
        wireMockRule.stubFor(get(urlPathEqualTo("/wcs"))
                .withQueryParam("service", equalTo("WCS"))
                .withQueryParam("request", equalTo("GetCapabilities"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_XML)
                        .withBody(capabilitiesXml)));

        wireMockRule.stubFor(get(urlPathEqualTo("/wcs"))
                .withQueryParam("service", equalTo("WCS"))
                .withQueryParam("request", equalTo("ProcessCoverages"))
                .willReturn(aResponse()
                        .withStatus(200)
                        .withHeader("Content-Type", MediaType.APPLICATION_XML)
                        .withBody("<result>OK</result>")));

        URI serviceEndpoint = URI.create(
                "http://localhost:" + wireMockRule.port() + "/wcs");
        Client client = buildTestClient();
        WCPSWrapper wrapper = new WCPSWrapper(serviceEndpoint, client);

        wrapper.sendQueryKVP("return 1");
        wrapper.sendQueryKVP("return 2");

        // GetCapabilities should have been called only once (cached)
        wireMockRule.verify(1, getRequestedFor(urlPathEqualTo("/wcs"))
                .withQueryParam("request", equalTo("GetCapabilities")));

        // Both ProcessCoverages requests should include the for-clause
        String expectedQuery1 = "for __cov__ in ( AverageChloroColor )\nreturn 1";
        String expectedQuery2 = "for __cov__ in ( AverageChloroColor )\nreturn 2";

        wireMockRule.verify(1, getRequestedFor(urlPathEqualTo("/wcs"))
                .withQueryParam("request", equalTo("ProcessCoverages"))
                .withQueryParam("query", equalTo(expectedQuery1)));

        wireMockRule.verify(1, getRequestedFor(urlPathEqualTo("/wcs"))
                .withQueryParam("request", equalTo("ProcessCoverages"))
                .withQueryParam("query", equalTo(expectedQuery2)));
    }
}