package com.ibm.question_answering.elasticsearch;

import java.util.ArrayList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

@ApplicationScoped
public class AskElasticService {
    public AskElasticService() {}    

    @Inject
    ElasticServiceResource elasticResource;
   
    public Response search(String query) {
        com.ibm.question_answering.elasticsearch.Input input = new com.ibm.question_answering.elasticsearch.Input();
        MultiMatch multiMatch = new MultiMatch();
        ArrayList<String> fields = new ArrayList<String>();
        String[] fieldsAsString = fields.toArray(new String[fields.size()]);
        fields.add("text");
        multiMatch.fields = fieldsAsString; 
        multiMatch.type = "cross_fields";
        multiMatch.query = query;
        Must must = new Must();
        must.multi_match = multiMatch;
        Bool bool = new Bool();
        bool.must = must;
        Query queryInput = new Query();
        queryInput.bool = bool;
        input.query = queryInput;

        return elasticResource.search(input);
    }

    // Input for ElasticSearch
    // Note that the field name 'plainTextContent' is a variable which is why custom serialization needs to be used
    // https://stackoverflow.com/questions/76228719/jackson-serializer-not-invoked-in-quarkus
/* 
curl -X POST \
-u $ELASTIC_SEARCH_USER:$ELASTIC_SEARCH_PASSWORD \
"$ELASTIC_SEARCH_URL$ELASTIC_SEARCH_INDEX/_search" \
-H "Content-Type: application/json" \
-d '
{
    "size" : 10,
    "highlight": {
        "fields": [
        {
            "plainTextContent": {
                "type": "unified",
                "require_field_match": "true",
                "fragment_size": 300,
                "number_of_fragments":2
            }
        }
        ]
    },
    "query": {
        "bool": {
            "must": {
                "multi_match": {
                    "query": "some search query",
                    "fields": [
                        "fileTitle",
                        "plainTextContent"
                    ],
                    "type": "cross_fields"
                }
            }
        }
    }
}
'
*/
}