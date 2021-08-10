package by.yurachel.springapp.model.user.impl;

import by.yurachel.springapp.model.order.impl.Order;
import by.yurachel.springapp.model.user.Role;
import by.yurachel.springapp.model.user.Status;
import by.yurachel.springapp.util.userUtils.impl.UserUtilsInt;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
public class User implements Serializable {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @NotEmpty(message = "UserName name should not be empty")
    @Size(min = 2, max = 50, message = "UserName should be between 2 and 50 characters")
    private String userName;

    @Size(min = 4, message = "Password should be longer than 4 characters.")
    private String password;

    @Email(message = "Please,check your email.")
    @NotEmpty(message = "Email shouldn't be empty.")
    private String email;

    private String firstName;

    private String lastName;

    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;

    @OneToMany(mappedBy = "user")
    @LazyCollection(LazyCollectionOption.FALSE)
    private List<Order> orders = new ArrayList<>();

    @Lob
    private byte[] avatar;

    @Temporal(TemporalType.DATE)
    private Date registrationDate;

    private String address;


    public UserUtilsInt getUserUtils() {
        return UserUtilsInt.getUserUtils();
    }

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public User(long id, String userName, String password, String email) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
    }


}

