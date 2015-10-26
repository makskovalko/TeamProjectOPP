package com.rhcloud.msdm.conference.service.Interfaces;




import com.rhcloud.msdm.conference.domain.entities.QueryConference;

import java.util.List;

/**
 * Created by Ghost on 24.10.2015.
 */
public interface QueryConferenceAction {


     void save(QueryConference queryConference);

     void delete(QueryConference queryConference);

     void update(QueryConference queryConference);

     QueryConference getById(Integer id);

     List<QueryConference> getAll();
}
