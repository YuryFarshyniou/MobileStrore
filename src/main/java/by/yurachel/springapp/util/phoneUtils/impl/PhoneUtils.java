package by.yurachel.springapp.util.phoneUtils.impl;

import by.yurachel.springapp.model.phone.Phone;
import by.yurachel.springapp.util.phoneUtils.PhoneUtilsInt;
import org.springframework.stereotype.Component;

@Component(value = "phoneUtils")
public class PhoneUtils implements PhoneUtilsInt {
    @Override
    public void addImage(Phone phone, String imgLink) {
        phone.getImages().add(imgLink);
    }
}
