package com.ibm.question_answering.elasticsearch;

public class Input {
    public Query query;
    public int size = 5;
    
    // https://stackoverflow.com/questions/76228719/jackson-serializer-not-invoked-in-quarkus
    //public Highlight highlight;
    public org.acme.Highlight highlight;
}
