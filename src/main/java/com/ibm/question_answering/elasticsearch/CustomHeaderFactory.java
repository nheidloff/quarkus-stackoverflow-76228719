package com.ibm.question_answering.elasticsearch;

import org.eclipse.microprofile.rest.client.ext.ClientHeadersFactory;
import java.util.Base64;
import javax.enterprise.context.ApplicationScoped;
import javax.ws.rs.core.MultivaluedHashMap;
import javax.ws.rs.core.MultivaluedMap;

@ApplicationScoped
public class CustomHeaderFactory implements ClientHeadersFactory {

    private String user;
    private String password;

    @Override
    public MultivaluedMap<String, String> update(MultivaluedMap<String, String> incomingHeaders, MultivaluedMap<String, String> clientOutgoingHeaders) {
        MultivaluedMap<String, String> result = new MultivaluedHashMap<>();
        String toBeEncoded = user + ":" + password;
        result.add("Authorization", "Basic " + Base64.getEncoder().encodeToString(toBeEncoded.getBytes()));
        return result;
    }
}