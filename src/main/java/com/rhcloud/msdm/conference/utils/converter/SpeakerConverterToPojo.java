package com.rhcloud.msdm.conference.utils.converter;


import com.rhcloud.msdm.conference.domain.entities.Speaker;
import com.rhcloud.msdm.conference.utils.JSON_POJO.SpeakerPOJO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Created by Ghost on 25.10.2015.
 */
@Service
public class SpeakerConverterToPojo implements Converter<Speaker, SpeakerPOJO>{


    @Override
    public SpeakerPOJO convert(Speaker speaker) {
        SpeakerPOJO res = new SpeakerPOJO();
        res.setAdditionalInfo(speaker.getAdditionalInfo());
        res.setPhoneNumber(speaker.getPhoneNumber());
        res.setDateOfBirth(speaker.getDateOfBirth());
        res.setUserName(speaker.getUserName());
        res.setFirstName(speaker.getFirstName());
        res.setLastName(speaker.getLastName());
        res.setJobPosition(speaker.getJobPosition());
        res.setProfileImage(speaker.getProfileImage());
        return res;
    }
}
