package com.aerok.partreq.domain.xfile;

import com.aerok.partreq.domain.model.PartRequirementRow;

public class XPartReqWsParts extends XFile {
    public void addRow(PartRequirementRow row) {
        super.addRow(row, "XPartReqWsParts");
    }
}
