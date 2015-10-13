package com.rhcloud.msdm.conference.utils.JSON_POJO;

/**
 * Created by Ghost on 13.10.2015.
 */
public class PictureData {
    private String url;

    private boolean is_silhouette;


    public PictureData() {
    }

    public boolean is_silhouette() {
        return is_silhouette;
    }

    public void setIs_silhouette(boolean is_silhouette) {
        this.is_silhouette = is_silhouette;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
