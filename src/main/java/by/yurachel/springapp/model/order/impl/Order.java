package by.yurachel.springapp.model.order.impl;

import by.yurachel.springapp.model.order.OrderState;
import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.model.user.impl.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Order implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    private String creationDate;

    @Enumerated(EnumType.STRING)
    private OrderState state;

    @ManyToOne()
    private User user;

    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Phone> phones = new ArrayList<>();

    public void addPhone(Phone phone) {
        phones.add(phone);
    }

    public boolean containsPhone(long id) {
        Phone phone2 = phones.stream().filter(phone -> phone.getId() == id)
                .findFirst()
                .orElse(null);

        return phone2 != null;
    }

    public void deletePhone(long id) {
        for (Phone phone : phones) {
            if (phone.getId() == id) {
                phones.remove(phone);
                return;
            }
        }
    }

    public void deleteAllPhones(long id) {
        phones.removeIf(phone -> phone.getId() == id);
    }

    public String stringForOrder() {
        Map<String, Integer> order = new HashMap<>();

        for (Phone phone : phones) {
            Integer value = order.get(phone.getName());
            if (value == null) {
                order.put(phone.getName(), 1);
            } else {
                order.put(phone.getName(), ++value);
            }
        }
        StringBuilder stringBuilder = new StringBuilder();

        for (Map.Entry<String, Integer> entry : order.entrySet()) {
            stringBuilder.append(entry.getKey()).append(",").append(entry.getValue()).append(" ");

        }
        return stringBuilder.toString();
    }

    public Order() {
        creationDate = new Date().toString();
    }


}
