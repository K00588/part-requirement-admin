package com.aerok.partreq.infra;

import com.aerok.partreq.domain.attribute.XAttribute;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class TextFileWriter {

    public static void write(String xfileName, List<List<XAttribute>> data) {
        if (data == null || data.isEmpty()) return;

        String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss"));
        Path outputPath = Paths.get("export", xfileName + "_" + timestamp + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath.toFile()))) {
            for (List<XAttribute> record : data) {
                StringBuilder line = new StringBuilder();
                for (XAttribute attr : record) {
                    line.append(attr.toString()); // XAttribute.toString()이 실제 필드값 리턴하도록
                }
                writer.write(line.toString());
                writer.newLine();
            }
            System.out.println("[OK] " + xfileName + " → " + outputPath);
        } catch (IOException e) {
            System.err.println("[ERROR] Failed to write " + xfileName + ": " + e.getMessage());
        }
    }
}
