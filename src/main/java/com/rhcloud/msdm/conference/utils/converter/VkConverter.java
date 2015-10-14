package com.rhcloud.msdm.conference.utils.converter;


import com.rhcloud.msdm.conference.utils.JSON_POJO.UserRegInfo;
import com.rhcloud.msdm.conference.utils.JSON_POJO.VkProfile;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

/**
 * Created by Ghost on 14.10.2015.
 */
@Component("VkConverter")
public class VkConverter implements Converter<VkProfile, UserRegInfo> {

        @Override
        public UserRegInfo convert(VkProfile vkProfile) {
                UserRegInfo res = new UserRegInfo();
                res.setBithdate(vkProfile.getBithDay());
                res.setLastname(vkProfile.getLastname());
                res.setFirstname(vkProfile.getFirstname());
                res.setPhoto(vkProfile.getPhoto());
                return res;
        }

}
