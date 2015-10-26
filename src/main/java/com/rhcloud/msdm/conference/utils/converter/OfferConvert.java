package com.rhcloud.msdm.conference.utils.converter;

import com.rhcloud.msdm.conference.domain.entities.Offer;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ConferencePOJO;
import com.rhcloud.msdm.conference.utils.JSON_POJO.OfferPOJO;
import com.rhcloud.msdm.conference.utils.JSON_POJO.SpeakerPOJO;
import com.rhcloud.msdm.conference.utils.converter.ConferenceConvector;
import com.rhcloud.msdm.conference.utils.converter.SpeakerConverterToPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Created by Ghost on 25.10.2015.
 */
@Service
public class OfferConvert implements  Converter<Offer, OfferPOJO> {

    @Autowired
    private SpeakerConverterToPojo speakerConverter;

    @Autowired
    private ConferenceConvector conferenceConverter;


    @Override
    public OfferPOJO convert(Offer offer) {
        OfferPOJO res = new OfferPOJO();
        ConferencePOJO conference =  conferenceConverter.convert(offer.getConference());
        res.setConference(conference);
        SpeakerPOJO speaker = speakerConverter.convert(offer.getSpeaker());
        res.setSpeaker(speaker);
        res.setText(offer.getText());
        return  res;
    }
}
