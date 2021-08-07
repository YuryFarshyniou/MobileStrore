package by.yurachel.springapp.util.orderUtils.impl;

import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.util.orderUtils.OrderUtilsInt;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Component(value = "orderUtils")
public class OrderUtils implements OrderUtilsInt {
    @Override
    public double orderSum(List<Phone> phones) {
        return phones.stream().mapToDouble(Phone::getPrice).sum();
    }

    @Override
    public void addPhone(List<Phone> phones, Phone phone) {
        phones.add(phone);
    }

    @Override
    public boolean containsPhone(List<Phone> phones, long id) {
        Phone phone2 = phones.stream().filter(phone -> phone.getId() == id)
                .findFirst()
                .orElse(null);

        return phone2 != null;
    }

    @Override
    public void deletePhone(List<Phone> phones, long id) {
        for (Phone phone : phones) {
            if (phone.getId() == id) {
                phones.remove(phone);
                return;
            }
        }
    }

    @Override
    public void deleteAllPhones(List<Phone> phones, long id) {
        phones.removeIf(phone -> phone.getId() == id);

    }

    @Override
    public Map<String, Integer> orderInformation(List<Phone> phones) {
        return null;
    }
}
