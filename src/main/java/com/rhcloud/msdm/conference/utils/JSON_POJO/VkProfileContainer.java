package com.rhcloud.msdm.conference.utils.JSON_POJO;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * Created by Ghost on 11.10.2015.
 */
public class VkProfileContainer {

    @JsonProperty("response")
    private List<VkProfile> vkProfileList;

    public VkProfileContainer() {
    }

    public List<VkProfile> getVkProfileList() {

        return vkProfileList;
    }

    public void setVkProfileList(List<VkProfile> vkProfileList) {
        this.vkProfileList = vkProfileList;
    }
}
