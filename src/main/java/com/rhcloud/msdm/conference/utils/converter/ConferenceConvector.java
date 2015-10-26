package com.rhcloud.msdm.conference.utils.converter;


import com.rhcloud.msdm.conference.domain.entities.Conference;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ConferencePOJO;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;


/**
 * Created by Ghost on 24.10.2015.
 */
@Service
public class ConferenceConvector implements Converter<Conference, ConferencePOJO> {

    @Override
    public ConferencePOJO convert(Conference conference) {
        ConferencePOJO res = new ConferencePOJO();
        res.setCity(conference.getCity());
        res.setAddress(conference.getAddress());
        res.setName(conference.getName());
        res.setCountry(conference.getCountry());
        return res;
    }
}
