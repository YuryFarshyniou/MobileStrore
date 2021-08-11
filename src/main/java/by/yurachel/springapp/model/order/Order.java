package by.yurachel.springapp.model.order;


import by.yurachel.springapp.model.order.OrderState;
import by.yurachel.springapp.model.phone.Phone;
import by.yurachel.springapp.model.user.User;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

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
@ToString
public class Order implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @Temporal(TemporalType.DATE)
    private Date creationDate;


    @Enumerated(EnumType.STRING)
    private OrderState state;

    @ManyToOne()
    private User user;

    @ManyToMany()
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Phone> phones = new ArrayList<>();

    public Order() {
        creationDate = new Date();
    }


}
