package com.aerok.partreq.infra;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.InputStream;
import java.util.*;

public class XFileDefinitionLoader {
    private static final Map<String, List<Map<String, Object>>> cache = new HashMap<>();

    static {
        try (InputStream in = XFileDefinitionLoader.class.getResourceAsStream("/config/xfile_fields.json")) {
            if (in == null) {
                throw new RuntimeException("❌ xfile_fields.json not found under /resources/config");
            }

            ObjectMapper mapper = new ObjectMapper();
            Map<String, List<Map<String, Object>>> data = mapper.readValue(in, Map.class);
            cache.putAll(data);
            System.out.println("✅ XFile field definitions loaded: " + cache.keySet());
        } catch (Exception e) {
            throw new RuntimeException("Failed to load xfile_fields.json", e);
        }
    }

    public static List<Map<String, Object>> getFields(String xfileName) {
        return cache.getOrDefault(xfileName.toUpperCase(), Collections.emptyList());
    }
}
