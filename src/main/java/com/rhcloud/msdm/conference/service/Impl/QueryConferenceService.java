package com.rhcloud.msdm.conference.service.Impl;

import com.rhcloud.msdm.conference.domain.entities.QueryConference;
import com.rhcloud.msdm.conference.repository.QueryConferenceRepository;
import com.rhcloud.msdm.conference.service.Interfaces.QueryConferenceAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ghost on 24.10.2015.
 */
@Service
public class QueryConferenceService implements QueryConferenceAction {

    @Autowired
    private QueryConferenceRepository repository;



    @Override
    public void save(QueryConference queryConference) {
            repository.save(queryConference);
    }

    @Override
    public void delete(QueryConference queryConference) {
        repository.delete(queryConference);
    }

    @Override
    public void update(QueryConference queryConference) {
        repository.saveAndFlush(queryConference);
    }

    @Override
    public QueryConference getById(Integer id) {
        return repository.findOne(id);
    }

    @Override
    public List<QueryConference> getAll() {
        return repository.findAll();
    }
}
