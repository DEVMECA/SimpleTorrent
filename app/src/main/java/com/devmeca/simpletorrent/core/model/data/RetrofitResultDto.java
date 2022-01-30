package com.devmeca.simpletorrent.core.model.data;

import com.devmeca.simpletorrent.ui.site.SiteLinkItemData;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetrofitResultDto {

    @SerializedName("STATUS")
    String STATUS;
    @SerializedName("TORRENT_DATA")
    List<SiteLinkItemData> TORRENT_DATA;

    public String getSTATUS() {
        return STATUS;
    }

    public void setSTATUS(String STATUS) {
        this.STATUS = STATUS;
    }

    public List<SiteLinkItemData> getTORRENT_DATA() {
        return TORRENT_DATA;
    }

    public void setTORRENT_DATA(List<SiteLinkItemData> TORRENT_DATA) {
        this.TORRENT_DATA = TORRENT_DATA;
    }
}
