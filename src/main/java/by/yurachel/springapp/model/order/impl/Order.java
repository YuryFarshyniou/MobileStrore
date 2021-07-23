package by.yurachel.springapp.model.order.impl;

import by.yurachel.springapp.model.order.OrderState;
import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.model.user.impl.User;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
public class Order {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;


    private String creationDate;

    @Enumerated
    private OrderState state;

//    @ManyToOne()
//    @JoinColumn(name = "user_id", nullable = false)
//    private User user;
//
//    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
//    @JoinTable(
//            name = "orders_phones",
//            joinColumns = @JoinColumn(name = "order_id"),
//            inverseJoinColumns = @JoinColumn(name = "phones_id")
//    )
//    private List<Phone> phones = new ArrayList<>();

    public Order() {
        creationDate = new Date().toString();
    }

//    @Override
//    public String toString() {
//        return "Order{" +
//                "id=" + id +
//                ", creationDate='" + creationDate + '\'' +
//                ", state=" + state +
//                ", user=" + user +
//                ", phones=" + phones +
//                '}';
//    }
}
