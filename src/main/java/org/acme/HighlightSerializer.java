package org.acme;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import io.quarkus.arc.Unremovable;

import java.io.IOException;
import javax.inject.Singleton;

@Singleton
@Unremovable
public class HighlightSerializer extends StdSerializer<Highlight> {

  public HighlightSerializer() {
    this(null);
  }

  public HighlightSerializer(Class<Highlight> t) {
    super(t);
  }

  // invoked for curl http://localhost:8080/hello/highlight
  // not invoked for curl http://localhost:8080/query-elastic
  // https://stackoverflow.com/questions/76228719/jackson-serializer-not-invoked-in-quarkus
  @Override
  public void serialize(Highlight value, JsonGenerator jsonGenerator, SerializerProvider provider)
      throws IOException {
    System.out.println("serialize invoked");
    jsonGenerator.writeStartObject();
    jsonGenerator.writeStringField("foo_bar", "fooobar");
    jsonGenerator.writeStringField("niklas", "niklas");
  }
}