package org.opengis.cite.wcps11.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

// import org.w3c.dom.Document;

import com.sun.jersey.api.client.Client;
public class WCPSWrapper {
    
    private URI serviceEndpoint;
    
    private Client client;
    
    private String forClause;

    public WCPSWrapper(URI serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
        client = ClientUtils.buildClient();
        forClause = "for __cov__ in ( " + discoverCoverage() + " )\n";
    }

    public String sendQueryKVP(String query) {
        Map<String, String> params = new HashMap<>();
        params.put("query", forClause + query);
        return client.handle(ClientUtils.buildGetRequest(serviceEndpoint, params)).getEntity(String.class);
    }

    private String discoverCoverage() {
        // TODO replace with actual discovery of coverage
        return "AvgTemperatureColorScaled";
    }
}
