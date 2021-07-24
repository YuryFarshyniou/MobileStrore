package by.yurachel.springapp.model.user.impl;

import by.yurachel.springapp.model.order.impl.Order;
import by.yurachel.springapp.model.phone.impl.Phone;
import by.yurachel.springapp.model.user.Role;
import by.yurachel.springapp.model.user.Status;
import lombok.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.transaction.Transactional;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
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

    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;

    @ManyToMany(fetch = FetchType.EAGER)
    @Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    @ToString.Exclude
    private List<Phone> phones = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<Order> orders = new ArrayList<>();


    private boolean hasImage;

    public void addPhone(Phone phone) {
         phones.add(phone);
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

    @Transactional
    public boolean containsPhone(long id) {
        return phones.stream().anyMatch(phone -> phone.getId() == id);
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

