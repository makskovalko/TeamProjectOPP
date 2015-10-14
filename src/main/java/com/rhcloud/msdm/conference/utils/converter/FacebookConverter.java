package com.rhcloud.msdm.conference.utils.converter;

import com.rhcloud.msdm.conference.utils.JSON_POJO.FacebookProfile;
import com.rhcloud.msdm.conference.utils.JSON_POJO.UserRegInfo;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Ghost on 14.10.2015.
 */
@Component("FacebookConverter")
public class FacebookConverter implements Converter<FacebookProfile, UserRegInfo> {
    @Override
    public UserRegInfo convert(FacebookProfile source) {
        if (source == null) throw new IllegalArgumentException();
        UserRegInfo res = new UserRegInfo();
        res.setFirstname(source.getFirstname());
        res.setLastname(source.getLastname());
        res.setBithdate(source.getBithday());
        res.setPhoto(source.getPicture().getPhotoUrl());
        return res;
    }
}
