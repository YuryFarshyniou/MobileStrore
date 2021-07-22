package by.yurachel.springapp.model.phone.impl;

import by.yurachel.springapp.model.user.impl.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "phones")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Phone {
    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @NotEmpty(message = "Phone name should not be empty")
    @Size(min = 2, max = 50, message = "Name should be between 2 and 50 characters")
    private String name;
    @Min(value = 1, message = "Price should be greater than one.")
    @NotNull(message = "Price shouldn't be null.")
    private double price;
    @NotEmpty(message = "Processor should not be empty")
    private String processor;
    @NotEmpty(message = "Img link should not be empty")
    private String img;

    @ManyToMany(mappedBy = "phones", fetch = FetchType.EAGER, cascade = CascadeType.REFRESH)
    @ToString.Exclude
    private List<User> users = new ArrayList<>();

    private static final long serialVersionUID = 6295618226040646585L;

    public boolean addUser(User user) {
        return users.add(user);
    }

    public boolean deleteUser(long id) {
        return users.removeIf(user -> user.getId() == id);
    }

    public Phone(String name, double price, String processor, String img) {
        this.name = name;
        this.price = price;
        this.processor = processor;
        this.img = img;

    }

    public Phone(long id, String name, double price, String processor) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.processor = processor;
    }

    public Phone(String name, double price, String processor) {
        this.name = name;
        this.price = price;
        this.processor = processor;
    }



}
