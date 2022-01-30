package com.devmeca.simpletorrent.ui.site;

import com.google.gson.annotations.SerializedName;

public class SiteLinkItemData {

    @SerializedName("_id")
    Long _id;

    @SerializedName("tor_site_seq")
    Long tor_site_seq;

    @SerializedName("tor_site_nation_type")
    String tor_site_nation_type;

    @SerializedName("tor_genre")
    String tor_genre;

    @SerializedName("tor_site_nm")
    String tor_site_nm;

    @SerializedName("tor_site_url")
    String tor_site_url;

    @SerializedName("tor_order")
    String tor_order;

    @SerializedName("tor_site_img")
    String tor_site_img;

    public SiteLinkItemData(){}

    public Long get_id() {
        return _id;
    }

    public void set_id(Long _id) {
        this._id = _id;
    }

    public Long getTor_site_seq() {
        return tor_site_seq;
    }

    public void setTor_site_seq(Long tor_site_seq) {
        this.tor_site_seq = tor_site_seq;
    }

    public String getTor_site_nation_type() {
        return tor_site_nation_type;
    }

    public void setTor_site_nation_type(String tor_site_nation_type) {
        this.tor_site_nation_type = tor_site_nation_type;
    }

    public String getTor_genre() {
        return tor_genre;
    }

    public void setTor_genre(String tor_genre) {
        this.tor_genre = tor_genre;
    }

    public String getTor_site_nm() {
        return tor_site_nm;
    }

    public void setTor_site_nm(String tor_site_nm) {
        this.tor_site_nm = tor_site_nm;
    }

    public String getTor_site_url() {
        return tor_site_url;
    }

    public void setTor_site_url(String tor_site_url) {
        this.tor_site_url = tor_site_url;
    }

    public String getTor_order() {
        return tor_order;
    }

    public void setTor_order(String tor_order) {
        this.tor_order = tor_order;
    }

    public String getTor_site_img() {
        return tor_site_img;
    }

    public void setTor_site_img(String tor_site_img) {
        this.tor_site_img = tor_site_img;
    }

    @Override
    public String toString() {
        return "SiteLinkItemData{" +
                "tor_site_seq=" + tor_site_seq +
                ", tor_site_nation_type='" + tor_site_nation_type + '\'' +
                ", tor_genre='" + tor_genre + '\'' +
                ", tor_site_nm='" + tor_site_nm + '\'' +
                ", tor_site_url='" + tor_site_url + '\'' +
                ", tor_order='" + tor_order + '\'' +
                ", tor_site_img='" + tor_site_img + '\'' +
                '}';
    }
}
