package by.yurachel.springapp.util;

import org.springframework.stereotype.Component;

import java.util.Base64;


public class UserUtils {
    public String getAvatarData(byte[] byteDate) {
        return Base64.getMimeEncoder().encodeToString(byteDate);
    }
}
