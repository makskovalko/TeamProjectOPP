package com.rhcloud.msdm.conference.domain;

import com.rhcloud.msdm.conference.domain.entities.Organizer;
import com.rhcloud.msdm.conference.domain.entities.Participant;
import com.rhcloud.msdm.conference.domain.entities.Speaker;
import com.rhcloud.msdm.conference.domain.entities.User;

public class UserFactory {
    public static User getUserByType(User user) {
        switch (user.getUserType()) {
            case "participant": return new Participant(user);
            case "speaker": return new Speaker(user);
            case "organizer": return new Organizer(user);
        }
        return null;
    }
}