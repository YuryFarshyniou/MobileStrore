package by.yurachel.springapp.util.orderUtils.impl;

import by.yurachel.springapp.model.phone.Phone;
import by.yurachel.springapp.util.orderUtils.OrderUtilsInt;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
        Map<String, Integer> order = new HashMap<>();

        for (Phone phone : phones) {
            Integer value = order.get(phone.getName());
            if (value == null) {
                order.put(phone.getName(), 1);
            } else {
                order.put(phone.getName(), ++value);
            }
        }
        return order;
    }

    @Override
    public Map<String, Map<Phone, Integer>> convertListOfPhonesIntoMap(List<Phone> userPhones) {
        Map<String, Map<Phone, Integer>> phones = new HashMap<>();
        for (Phone phone : userPhones) {
            Map<Phone, Integer> s = new HashMap<>();
            if (phones.containsKey(phone.getName())) {
                s = phones.get(phone.getName());
                Set<Phone> set = s.keySet();
                Phone p = (Phone) set.toArray()[0];
                int i = s.get(p) + 1;
                s.put(p, i);
            } else {
                s.put(phone, 1);
            }
            phones.put(phone.getName(), s);
        }
        return phones;
    }
}
