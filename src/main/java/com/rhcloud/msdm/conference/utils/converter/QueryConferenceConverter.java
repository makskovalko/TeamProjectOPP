package com.rhcloud.msdm.conference.utils.converter;



import com.rhcloud.msdm.conference.domain.entities.QueryConference;
import com.rhcloud.msdm.conference.utils.JSON_POJO.ConferencePOJO;
import com.rhcloud.msdm.conference.utils.JSON_POJO.QueryConferencePOJO;
import com.rhcloud.msdm.conference.utils.JSON_POJO.SpeakerPOJO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Service;

/**
 * Created by Ghost on 25.10.2015.
 */
@Service
public class QueryConferenceConverter implements Converter<QueryConference, QueryConferencePOJO> {

    @Autowired
    private  ConferenceConvector conferenceConvector;

    @Autowired
    private  SpeakerConverterToPojo speakerConverter;

    @Override

    public QueryConferencePOJO convert(QueryConference queryConference) {
        QueryConferencePOJO res = new QueryConferencePOJO();
        ConferencePOJO conference = conferenceConvector.convert(queryConference.getConference());
        res.setConference(conference);
        SpeakerPOJO speaker = speakerConverter.convert(queryConference.getSpeaker());
        res.setSpeaker(speaker);
        res.setText(queryConference.getText());
        return res;
    }
}
