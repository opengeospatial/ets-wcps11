package org.opengis.cite.wcps11.util;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

// import org.w3c.dom.Document;

import com.sun.jersey.api.client.Client;
public class WCPSWrapper {
    URI serviceEndpoint;
    Client client;
    public WCPSWrapper(URI serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
        client = ClientUtils.buildClient();
    }

    public String sendQueryKVP(String query) {
        Map<String, String> params = new HashMap<>();
        params.put("query", query);
        return client.handle(ClientUtils.buildGetRequest(serviceEndpoint, params)).getEntity(String.class);
    }
}
