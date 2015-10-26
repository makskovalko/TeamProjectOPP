package com.rhcloud.msdm.conference.service.Interfaces;




import com.rhcloud.msdm.conference.domain.entities.Offer;

import java.util.List;

/**
 * Created by Ghost on 18.10.2015.
 */
public interface OfferAction
{

    void save(Offer offer);

    void update(Offer offer);

    void delete(Offer offer);

    List<Offer> getAll();

    Offer  getById(Integer id);

}
