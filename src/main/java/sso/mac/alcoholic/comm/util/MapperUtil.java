package sso.mac.alcoholic.comm.util;

import com.fasterxml.jackson.databind.ObjectMapper;

public class MapperUtil {
    private MapperUtil() {}

    public static ObjectMapper getInstance() {
        return Mapper.INSTANCE;
    }

    private static class Mapper {
        private static final ObjectMapper INSTANCE = new ObjectMapper();
    }
}
