package com.rhcloud.msdm.conference.utils;

import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.security.MessageDigest;

/**
 * Created by Ghost on 07.10.2015.
 */

@Service("Md5Custom")
public class Md5Custom {

    public static String CustomToMd5(String str){
        StringBuilder res = new StringBuilder();
        MessageDigest messageDigest = null;
        byte[] md5Byte = new byte[0];
        try {
            messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(str.getBytes());
            md5Byte =  messageDigest.digest();
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
        BigInteger integer = new BigInteger(1 , md5Byte);
        String md5hex = integer.toString(16);
        res.append(md5hex);

        for(int i =0 ; res.length()< 32 ; i++){
            res.insert(0 , 0);
        }
        return  res.toString();
    }

}
