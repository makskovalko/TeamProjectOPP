package com.rhcloud.msdm.conference.utils.converter;


import com.rhcloud.msdm.conference.domain.entities.Speaker;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ProfileData;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;


/**
 * Created by Ghost on 18.10.2015.
 */
@Service
public class SpeakerConverter implements Converter<ProfileData,Speaker> {

    @Override
    public Speaker convert(ProfileData profileData) {
        Speaker res = new Speaker();
        res.setAdditionalInfo(profileData.getAdditionalInfo());
        res.setPhoneNumber(profileData.getPhoneNumber());
        res.setDateOfBirth(profileData.getDateOfBirth());
        res.setUserName(profileData.getUserName());
        res.setFirstName(profileData.getFirstName());
        res.setLastName(profileData.getLastName());
        res.setJobPosition(profileData.getJobPosition());
        res.setProfileImage(profileData.getProfileImage());
        return res;
    }
}
