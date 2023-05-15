## Quarkus StackOverflow Question 76228719

[StackOverflow Question 76228719](https://stackoverflow.com/questions/76228719/jackson-serializer-not-invoked-in-quarkus)

```
Works:
http://localhost:8080/hello/highlight

Does not work:
http://localhost:8080/query-elastic

See logs - highlight is null and 'serialize' is not invoked:
Body:
{"query":{"bool":{"must":{"multi_match":{"query":"","type":"cross_fields","fields":[]}}}},"size":5,"highlight":null}
```

Optionally to run ElasticSearch:

```
docker run --name elasticsearch  -e "discovery.type=single-node" -e "ES_JAVA_OPTS=-Xms512m -Xmx512m"\
       --rm -p 9200:9200 docker.io/elastic/elasticsearch:7.16.3
```

