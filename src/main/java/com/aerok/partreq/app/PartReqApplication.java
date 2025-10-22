package com.aerok.partreq.app;

import com.aerok.partreq.domain.model.PartRequirementRow;
import com.aerok.partreq.domain.xfile.*;
import com.aerok.partreq.infra.ExcelReader;

import java.nio.file.*;
import java.util.List;

public class PartReqApplication {

    public static void main(String[] args) {
        String excelPath = "data/partreq_input.xlsx";

        List<PartRequirementRow> rows = ExcelReader.read(excelPath);
        System.out.println("📊 읽은 데이터 개수: " + rows.size());

        XPartReq xpartreq = new XPartReq();
        XEff xeff = new XEff();
        XEffSer xeffser = new XEffSer();

        for (PartRequirementRow row : rows) {
            xpartreq.addRow(row, "XPARTREQ");
            xeff.addRow(row, "XEFF");
            xeffser.addRow(row, "XEFFSER");
        }

        try {
            Path exportDir = Paths.get("export");
            Files.createDirectories(exportDir);

            xpartreq.toText(exportDir);
            xeff.toText(exportDir);
            xeffser.toText(exportDir);

            System.out.println("✅ Export 완료!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
