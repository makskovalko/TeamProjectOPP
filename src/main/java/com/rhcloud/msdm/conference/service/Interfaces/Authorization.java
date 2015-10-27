package com.rhcloud.msdm.conference.service.Interfaces;

import com.rhcloud.msdm.conference.domain.entities.User;

public interface Authorization {
    public boolean validateData(User user);
    public User authorizeUser(User user);
}