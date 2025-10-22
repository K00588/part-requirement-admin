package com.aerok.partreq.infra;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

public class XFileDefinitionLoader {
    private static final Map<String, List<Map<String, Object>>> cache = new java.util.HashMap<>();

    static {
        try (InputStream in = XFileDefinitionLoader.class.getResourceAsStream("/config/xfile_fields.json")) {
            if (in == null) {
                throw new RuntimeException("xfile_fields.json not found");
            }

            ObjectMapper mapper = new ObjectMapper();

            // ✅ TypeReference로 타입 안정성 확보
            Map<String, List<Map<String, Object>>> data =
                mapper.readValue(in, new TypeReference<Map<String, List<Map<String, Object>>>>() {});

            cache.putAll(data);
            System.out.println("✅ XFile field definitions loaded: " + cache.keySet());

        } catch (Exception e) {
            throw new RuntimeException("Failed to load xfile_fields.json", e);
        }
    }

    public static List<Map<String, Object>> getFields(String xfileName) {
        return cache.getOrDefault(xfileName, java.util.Collections.emptyList());
    }
}
