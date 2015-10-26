package com.rhcloud.msdm.conference.service.Impl;


import com.rhcloud.msdm.conference.domain.entities.Offer;
import com.rhcloud.msdm.conference.repository.OfferRepository;
import com.rhcloud.msdm.conference.service.Interfaces.OfferAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Ghost on 18.10.2015.
 */
@Service("offerService")
public class OfferService implements OfferAction {

    @Autowired
    private OfferRepository repository;

    @Override
    public void save(Offer offer) {
        repository.save(offer);
    }

    @Override
    public void update(Offer offer) {
        repository.saveAndFlush(offer);
    }

    @Override
    public void delete(Offer offer) {
        repository.delete(offer);
    }

    @Override
    public List<Offer> getAll() {
        return repository.findAll();
    }

    @Override
    public Offer getById(Integer id) {
        return  repository.findOne(id);
    }
}
