package by.yurachel.springapp.util.phoneUtils;

import by.yurachel.springapp.model.phone.Phone;
import org.springframework.stereotype.Component;

@Component(value = "phoneUtils")
public class PhoneUtils {

    public void addImage(Phone phone, String imgLink) {
        phone.getImages().add(imgLink);
    }
}
