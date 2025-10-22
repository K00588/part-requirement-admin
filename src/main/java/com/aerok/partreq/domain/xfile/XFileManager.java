package com.aerok.partreq.domain.xfile;
import com.aerok.partreq.infra.TextFileWriter;

import com.aerok.partreq.domain.model.PartRequirementRow;
import java.util.HashMap;
import java.util.Map;

public class XFileManager {

    // 모든 XFILE 객체를 보관하는 맵
    private static final Map<String, XFile> xfiles = new HashMap<>();

    static {
        // 사용할 XFILE별 인스턴스 등록
        xfiles.put("XPARTREQ", new XPartReq());
        xfiles.put("XPARTREQWS", new XPartReqWs());
        xfiles.put("XPARTREQWSPARTS", new XPartReqWsParts());
        // 필요하면 XEFF, XEFFSER 등 계속 추가
    }

    // 엑셀 행(row) 하나를 해당 XFILE에 추가
    public static void addRow(PartRequirementRow row, String xfileName) {
        XFile xfile = xfiles.get(xfileName.toUpperCase());
        if (xfile != null) {
            xfile.addRow(row, xfileName);
        } else {
            System.err.println("[WARN] Unknown XFILE name: " + xfileName);
        }
    }

    // 모든 XFILE을 TXT로 내보내기 (예: export/ 폴더)
    public static void writeAll() {
    for (Map.Entry<String, XFile> entry : xfiles.entrySet()) {
        String xfileName = entry.getKey();
        XFile file = entry.getValue();

        // XFile 내부의 data를 꺼내서 TextFileWriter로 저장
        TextFileWriter.write(xfileName, file.getData());
    }
    }
}
