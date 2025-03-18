package Infrastucure;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonParser {
    private final ObjectMapper objectMapper = new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL);;

    public <T> T deserialize(String json, Class<T> clazz) throws Exception {
        return objectMapper.readValue(json, clazz);
    }

    public String serialize(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (Exception e) {
            return "{\"success\": false, \"message\": \"Serialization error\"}";
        }
    }
}
