package serializer;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Serializer {
    private final ObjectMapper objectMapper;

    public Serializer() {
        this.objectMapper = new ObjectMapper();
    }

    public String fromObjectTotString(Object o) throws JsonProcessingException {
        return objectMapper.writeValueAsString(o);
    }
}
