package by.yurachel.springapp.util.orderUtils;

import by.yurachel.springapp.model.phone.Phone;

import java.util.List;
import java.util.Map;

public interface OrderUtilsInt {
    double orderSum(List<Phone> phones);

    void addPhone(List<Phone> phones, Phone phone);

    boolean containsPhone(List<Phone> phones, long id);

    void deletePhone(List<Phone> phones, long id);

    void deleteAllPhones(List<Phone> phones, long id);

    Map<String, Integer> orderInformation(List<Phone> phones);

    Map<String, Map<Phone, Integer>> convertListOfPhonesIntoMap(List<Phone> userPhones);
}
