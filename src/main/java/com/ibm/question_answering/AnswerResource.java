package com.ibm.question_answering;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import com.ibm.question_answering.elasticsearch.AskElasticService;
import com.ibm.question_answering.elasticsearch.Response;

@ApplicationScoped
@Path("")
public class AnswerResource {

    @Inject
    AskElasticService askElastic;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/query-elastic")
    public Response queryElasticAndMaaS(String query) {
        return askElastic.search(query);
    }
}