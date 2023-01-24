package org.example.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;

public class Json {
    private static ObjectMapper myObjectMapper = defaultObjectMapper();

    private static ObjectMapper defaultObjectMapper() {
        ObjectMapper om = new ObjectMapper();
//        Prevents crash if one property missing
        om.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        return om;
    }

    public static JsonNode parse(String jsonSource) throws JsonProcessingException {
        return myObjectMapper.readTree(jsonSource);
    }

    public static <A> A fromJson(JsonNode node, Class<A> clas) throws JsonProcessingException {
        return myObjectMapper.treeToValue(node, clas);
    }

    //    Get config into a json node
    public static JsonNode toJson(Object obj) {
        return myObjectMapper.valueToTree(obj);
    }


    //    Json node to string
    private static String generateJson(Object o, boolean pretty) throws JsonProcessingException {
        ObjectWriter objectWriter = myObjectMapper.writer();
        if (pretty) {
            objectWriter = objectWriter.with(SerializationFeature.INDENT_OUTPUT);
        }
        return objectWriter.writeValueAsString(o);
    }

    public static String stringify(JsonNode node) throws JsonProcessingException {
        return generateJson(node, false);
    }

    public static String stringifyPretty(JsonNode node) throws JsonProcessingException {
        return generateJson(node, true);
    }
}
