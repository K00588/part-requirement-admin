package com.aerok.partreq.infra;
import com.aerok.partreq.domain.model.PartRequirementRow;
import com.aerok.partreq.domain.xfile.XFileManager;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelReader {

    public static List<PartRequirementRow> read(String filePath) {
        List<PartRequirementRow> rows = new ArrayList<>();

        try (Workbook workbook = WorkbookFactory.create(new File(filePath))) {
            Sheet sheet = workbook.getSheetAt(0); // 첫 번째 시트
            Row headerRow = sheet.getRow(0);

            // 엑셀 헤더 이름 읽기
            List<String> headers = new ArrayList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue().trim());
            }

            // 데이터 행 반복
            for (int i = 1; i <= sheet.getLastRowNum(); i++) {
                Row dataRow = sheet.getRow(i);
                if (dataRow == null) continue;

                // XFILE별로 필드 분류
                Map<String, Map<String, String>> groupedByXfile = new HashMap<>();

                for (int j = 0; j < headers.size(); j++) {
                    String header = headers.get(j);
                    String value = getCellValueAsString(dataRow.getCell(j));

                    // "XPARTREQ.PARTREQ-TITLE" → ["XPARTREQ", "PARTREQ-TITLE"]
                    String[] parts = header.split("\\.");
                    if (parts.length != 2) continue;

                    String xfileName = parts[0].trim();
                    String fieldName = parts[1].trim();

                    groupedByXfile
                        .computeIfAbsent(xfileName, k -> new HashMap<>())
                        .put(fieldName, value);
                }

                // XFILE별 PartRequirementRow 추가
                for (String xfileName : groupedByXfile.keySet()) {
                    PartRequirementRow row = mapToRow(groupedByXfile.get(xfileName));
                    XFileManager.addRow(row, xfileName);  // 나중에 TXT로 뽑을 관리 클래스
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return rows;
    }

    private static String getCellValueAsString(Cell cell) {
        if (cell == null) return "";
        return switch (cell.getCellType()) {
            case STRING -> cell.getStringCellValue().trim();
            case NUMERIC -> String.valueOf((int) cell.getNumericCellValue());
            case BOOLEAN -> String.valueOf(cell.getBooleanCellValue());
            default -> "";
        };
    }

    private static PartRequirementRow mapToRow(Map<String, String> data) {
        PartRequirementRow row = new PartRequirementRow();
        // 여기서는 JSON의 "source"명과 동일한 setter만 있으면 됨
        row.setPartReqTitle(data.getOrDefault("PARTREQ-TITLE", ""));
        row.setPartReqType(data.getOrDefault("PARTREQ-TYPE", ""));
        row.setDescriptionReq(data.getOrDefault("DESCRIPTION", ""));
        row.setRemovalReq(data.getOrDefault("REMOVAL-REQ", ""));
        row.setShelfPerf(data.getOrDefault("SHELF-PERF", ""));
        row.setHardSoft(data.getOrDefault("HARD-SOFT", ""));
        return row;
    }
}
