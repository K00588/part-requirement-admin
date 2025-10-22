package com.aerok.partreq.domain.model;

public class PartRequirementRow {

    // ========== XPARTREQ ==========
    private String partReqTitle;   // XPARTREQ.PARTREQ-TITLE
    private String partReqType;    // XPARTREQ.PARTREQ-TYPE
    private String descriptionReq; // XPARTREQ.DESCRIPTION
    private String removalReq;     // XPARTREQ.REMOVAL-REQ
    private String shelfPerf;      // XPARTREQ.SHELF-PERF
    private String hardSoft;       // XPARTREQ.HARD-SOFT
    private String proRata;
    private String ratingCat;

    // ========== XPARTREQWS ==========
    private String descriptionWs;  // XPARTREQWS.DESCRIPTION
    private String zone;           // XPARTREQWS.ZONE
    private String area;           // XPARTREQWS.AREA

    // ========== XPARTREQWSPARTS ==========
    private String partNo;         // XPARTREQWSPARTS.PARTNO
    private String serialNo;       // XPARTREQWSPARTS.SERIALNO

    // ===== Getter / Setter =====
    public String getPartReqTitle() { return partReqTitle; }
    public void setPartReqTitle(String partReqTitle) { this.partReqTitle = partReqTitle; }

    public String getPartReqType() { return partReqType; }
    public void setPartReqType(String partReqType) { this.partReqType = partReqType; }

    public String getDescriptionReq() { return descriptionReq; }
    public void setDescriptionReq(String descriptionReq) { this.descriptionReq = descriptionReq; }

    public String getRemovalReq() { return removalReq; }
    public void setRemovalReq(String removalReq) { this.removalReq = removalReq; }

    public String getShelfPerf() { return shelfPerf; }
    public void setShelfPerf(String shelfPerf) { this.shelfPerf = shelfPerf; }

    public String getHardSoft() { return hardSoft; }
    public void setHardSoft(String hardSoft) { this.hardSoft = hardSoft; }

    public String getDescriptionWs() { return descriptionWs; }
    public void setDescriptionWs(String descriptionWs) { this.descriptionWs = descriptionWs; }

    public String getZone() { return zone; }
    public void setZone(String zone) { this.zone = zone; }

    public String getArea() { return area; }
    public void setArea(String area) { this.area = area; }

    public String getPartNo() { return partNo; }
    public void setPartNo(String partNo) { this.partNo = partNo; }

    public String getSerialNo() { return serialNo; }
    public void setSerialNo(String serialNo) { this.serialNo = serialNo; }

    public String getProRata() { return proRata; }
    public void setProRata(String proRata) { this.proRata = proRata; }

    public String getRatingCat() { return ratingCat; }
    public void setRatingCat(String ratingCat) { this.ratingCat = ratingCat; }
}
