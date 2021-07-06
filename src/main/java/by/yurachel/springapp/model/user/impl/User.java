package by.yurachel.springapp.model.user.impl;

import by.yurachel.springapp.model.user.Role;
import by.yurachel.springapp.model.user.Status;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Objects;


@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(generator = "increment")
    @GenericGenerator(name = "increment", strategy = "increment")
    private long id;

    @NotEmpty(message = "UserName name should not be empty")
    @Size(min = 2, max = 50, message = "UserName should be between 2 and 50 characters")
    private String userName;

    @Size(min = 4, message = "Password should be longer than 4 characters.")
    private String password;

    @Email
    private String email;

    @Enumerated(value = EnumType.STRING)
    private Role role = Role.USER;

    @Enumerated(value = EnumType.STRING)
    private Status status = Status.ACTIVE;

    public User(long id, String userName, String password, String email, Role role, Status status) {
        this.id = id;
        this.userName = userName;
        this.password = password;
        this.email = email;
        this.role = role;
        this.status = status;
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

    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return id == user.id && Objects.equals(userName, user.userName) && Objects.equals(password, user.password) && Objects.equals(email, user.email) && role == user.role && status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, password, email, role, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", role=" + role +
                ", status=" + status +
                '}';
    }
}
