package com.rhcloud.msdm.conference.utils.JSON_POJO;

/**
 * Created by Ghost on 12.10.2015.
 */
public class FacebookPicture {

    private PictureData data;

    public FacebookPicture() {
    }

    public PictureData getData() {
        return data;
    }

    public void setData(PictureData data) {
        this.data = data;
    }

    public String getPhotoUrl(){
        return data.getUrl();
    }
}
