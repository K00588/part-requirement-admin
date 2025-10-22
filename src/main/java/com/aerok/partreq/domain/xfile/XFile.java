package com.aerok.partreq.domain.xfile;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.aerok.partreq.domain.attribute.XAttribute;
import com.aerok.partreq.domain.model.PartRequirementRow;
import com.aerok.partreq.infra.XFileDefinitionLoader;

public abstract class XFile {
    protected final List<List<XAttribute>> data = new ArrayList<>();

    public void addRow(PartRequirementRow row, String xfileName) {
        List<Map<String, Object>> fields = XFileDefinitionLoader.getFields(xfileName);
        List<XAttribute> record = new ArrayList<>();

        for (Map<String, Object> def : fields) {
            String source = def.get("source") != null ? def.get("source").toString() : "";
            Object value = getFieldValue(row, source);
            int length = def.get("length") != null ? ((Number) def.get("length")).intValue() : 0;
            String field = def.get("field") != null ? def.get("field").toString() : "";

            record.add(new XAttribute(field, value, length, false));
        }


        data.add(record);
    }

    private Object getFieldValue(Object obj, String fieldName) {
        try {
            var method = obj.getClass().getMethod("get" + capitalize(fieldName));
            return method.invoke(obj);
        } catch (Exception e) {
            return null;
        }
    }

    private String capitalize(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public void toText(Path outputDir) throws IOException {
        String name = this.getClass().getSimpleName().toUpperCase();
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String ts = LocalDateTime.now().format(fmt);

        Path file = outputDir.resolve(name + "_" + ts + ".txt");
        try (BufferedWriter bw = Files.newBufferedWriter(file)) {
            for (List<XAttribute> row : data) {
                for (XAttribute a : row) {
                    String value = a.getValue() == null ? "" : a.getValue().toString();
                    int length = a.getLength();

                    bw.write(String.format("%-" + length + "s", value));
                }
                bw.newLine();
            }
        }
        System.out.println("✅ " + name + " exported: " + file.getFileName());
    }

    public List<List<XAttribute>> getData() {
        return data;
    }   

}
