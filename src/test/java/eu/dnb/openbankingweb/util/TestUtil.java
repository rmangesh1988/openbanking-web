package eu.dnb.openbankingweb.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * Created by rmang on 19-03-2018.
 */
public class TestUtil {

    private static ObjectMapper objectMapper = new ObjectMapper();

    public static String asJsonString(Object object) throws JsonProcessingException {
        return objectMapper.writeValueAsString(object);
    }
}
