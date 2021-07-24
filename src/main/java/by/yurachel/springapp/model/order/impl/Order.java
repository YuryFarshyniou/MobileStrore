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
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "orders")
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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
    private List<Phone> phonesInOrder = new ArrayList<>();

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
