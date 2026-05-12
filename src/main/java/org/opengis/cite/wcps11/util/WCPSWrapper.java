package org.opengis.cite.wcps11.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import javax.management.RuntimeErrorException;
import javax.xml.transform.Source;


import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;

import net.sf.saxon.s9api.XdmValue;

public class WCPSWrapper {
    
    private URI serviceEndpoint;
    
    private Client client;
    
    private String forClause;

    public WCPSWrapper(URI serviceEndpoint, Client client) {
        this.serviceEndpoint = serviceEndpoint;
        this.client = client;
    }

    public ClientResponse sendQueryKVP(String query) {
        if (null == forClause) {
            try {
                forClause = "for __cov__ in ( " + discoverCoverage() + " )\n";
            } catch (Exception e) {
                throw new RuntimeErrorException(new Error("Failed to discover a coverage from the server. Make sure the server is live and has at least one coverage"));
            }
            
        }
        Map<String, String> params = new HashMap<>();
        params.put("service", "WCS");
        params.put("request", "ProcessCoverages");
        params.put("version", "2.0.1");
        params.put("query", forClause + query);

        return client.handle(ClientUtils.buildGetRequest(serviceEndpoint, params));
    }

    private String discoverCoverage() {
        Map<String, String> params = new HashMap<>();
        params.put("service", "WCS");
        params.put("request", "GetCapabilities");
        try {
            // Extract the first coverage ID from the capabilities document.
            ClientResponse response = client.handle(ClientUtils.buildGetRequest(serviceEndpoint, params));
            Source body = ClientUtils.getResponseEntityAsSource(response, null);
            String xpath = "(//*[local-name()='CoverageId'])[1]";
            XdmValue v = XMLUtils.evaluateXPath2(body, xpath, null);
            return v.itemAt(0).getStringValue();
        } catch (Exception e) {
            throw new RuntimeException("Failed to discover coverage in the WCS service: " + e.getMessage(), e);
        }
    }
}
