package com.aerok.partreq.domain.model;

public class PartRequirementRow {
    private String partReqTitle;
    private String partReqType;
    private String effTitle;
    private String description;
    private String removalReq;
    private String shelfPerf;
    private String hardSoft;
    private String proRata;
    private String ratingCat;
    // 추가 XFILE별 컬럼도 여기에 계속 확장

    // Getter/Setter
    public String getPartReqTitle() { return partReqTitle; }
    public void setPartReqTitle(String partReqTitle) { this.partReqTitle = partReqTitle; }

    public String getPartReqType() { return partReqType; }
    public void setPartReqType(String partReqType) { this.partReqType = partReqType; }

    public String getEffTitle() { return effTitle; }
    public void setEffTitle(String effTitle) { this.effTitle = effTitle; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getRemovalReq() { return removalReq; }
    public void setRemovalReq(String removalReq) { this.removalReq = removalReq; }

    public String getShelfPerf() { return shelfPerf; }
    public void setShelfPerf(String shelfPerf) { this.shelfPerf = shelfPerf; }

    public String getHardSoft() { return hardSoft; }
    public void setHardSoft(String hardSoft) { this.hardSoft = hardSoft; }

    public String getProRata() { return proRata; }
    public void setProRata(String proRata) { this.proRata = proRata; }

    public String getRatingCat() { return ratingCat; }
    public void setRatingCat(String ratingCat) { this.ratingCat = ratingCat; }
}
