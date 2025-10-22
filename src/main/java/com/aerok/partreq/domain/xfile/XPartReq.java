package com.aerok.partreq.domain.xfile;

import com.aerok.partreq.domain.attribute.XAttribute;
import com.aerok.partreq.domain.model.PartRequirementRow;
import java.util.ArrayList;
import java.util.List;

public class XPartReq extends XFile {

    @Override
    public void addRow(PartRequirementRow row, String xfileName) {
        List<XAttribute> record = new ArrayList<>();

        record.add(new XAttribute("PARTREQ-TITLE", row.getPartReqTitle(), 38, true));
        record.add(new XAttribute("PARTREQ-TYPE", row.getPartReqType(), 2, true));
        record.add(new XAttribute("DESCRIPTION", row.getDescription(), 36, false));
        record.add(new XAttribute("REMOVAL-REQ", row.getRemovalReq(), 1, false));
        record.add(new XAttribute("SHELF-PERF", row.getShelfPerf(), 1, false));
        record.add(new XAttribute("HARD-SOFT", row.getHardSoft(), 4, false));
        record.add(new XAttribute("PRO-RATA", row.getProRata(), 1, false));
        record.add(new XAttribute("RATING CAT", row.getRatingCat(), 10, false));

        data.add(record);
    }
}
