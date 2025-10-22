package com.aerok.partreq.infra;

import com.aerok.partreq.domain.model.PartRequirementRow;
import org.apache.poi.ss.usermodel.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

public class ExcelReader {

    /**
     * Excel 파일을 읽어서 PartRequirementRow 리스트를 반환
     */
    public static List<PartRequirementRow> read(String filePath) {
        List<PartRequirementRow> rows = new ArrayList<>();

        try (FileInputStream fis = new FileInputStream(filePath);
             Workbook workbook = WorkbookFactory.create(fis)) {

            Sheet sheet = workbook.getSheetAt(0);
            Iterator<Row> iterator = sheet.iterator();

            // 첫 행(헤더) 건너뛰기
            if (iterator.hasNext()) iterator.next();

            while (iterator.hasNext()) {
                Row row = iterator.next();
                PartRequirementRow record = new PartRequirementRow();

                record.setPartReqTitle(getString(row, 0));
                record.setPartReqType(getString(row, 1));
                record.setEffTitle(getString(row, 2));
                record.setDescription(getString(row, 9));

                rows.add(record);
            }

        } catch (IOException e) {
            throw new RuntimeException("❌ Excel 파일 읽기 실패: " + filePath, e);
        }

        System.out.println("✅ Excel 로드 완료 (" + rows.size() + " rows)");
        return rows;
    }

    private static String getString(Row row, int colIdx) {
        Cell cell = row.getCell(colIdx, Row.MissingCellPolicy.RETURN_BLANK_AS_NULL);
        if (cell == null) return "";
        cell.setCellType(CellType.STRING);
        return cell.getStringCellValue().trim();
    }
}
