package com.aerok.partreq.domain.xfile;

import com.aerok.partreq.domain.model.PartRequirementRow;

public class XPartReqWt extends XFile {
    public void addRow(PartRequirementRow row) {
        super.addRow(row, "XPartReqWt");
    }
}
