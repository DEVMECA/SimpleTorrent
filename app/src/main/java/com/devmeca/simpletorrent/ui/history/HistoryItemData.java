package com.devmeca.simpletorrent.ui.history;

public class HistoryItemData {

    Integer _id;
    String RULE_DATE;
    String TOR_TITLE;
    String TOR_DISK_AMT;
    String TOR_HASH;
    String USE_YN = "Y";

    public HistoryItemData(){}

    public HistoryItemData(Integer _id, String RULE_DATE, String TOR_TITLE, String TOR_DISK_AMT, String TOR_HASH) {
        this._id = _id;
        this.RULE_DATE = RULE_DATE;
        this.TOR_TITLE = TOR_TITLE;
        this.TOR_DISK_AMT = TOR_DISK_AMT;
        this.TOR_HASH = TOR_HASH;
    }

    public Integer get_id() {
        return _id;
    }

    public void set_id(Integer _id) {
        this._id = _id;
    }

    public String getRULE_DATE() {
        return RULE_DATE;
    }

    public void setRULE_DATE(String RULE_DATE) {
        this.RULE_DATE = RULE_DATE;
    }

    public String getTOR_TITLE() {
        return TOR_TITLE;
    }

    public void setTOR_TITLE(String TOR_TITLE) {
        this.TOR_TITLE = TOR_TITLE;
    }

    public String getTOR_DISK_AMT() {
        return TOR_DISK_AMT;
    }

    public void setTOR_DISK_AMT(String TOR_DISK_AMT) {
        this.TOR_DISK_AMT = TOR_DISK_AMT;
    }

    public String getTOR_HASH() {
        return TOR_HASH;
    }

    public void setTOR_HASH(String TOR_HASH) {
        this.TOR_HASH = TOR_HASH;
    }

    public String getUSE_YN() {
        return USE_YN;
    }

    public void setUSE_YN(String USE_YN) {
        this.USE_YN = USE_YN;
    }
}
