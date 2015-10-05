package com.rhcloud.msdm.conference.service.Interfaces;

import com.rhcloud.msdm.conference.domain.entities.User;

public interface Registration {
    public boolean checkData(User user);
    public void registerUser(User user);
    public boolean validateData(User user);
    public boolean confirmRegistration(String user, String userName, String confirmKey);
}