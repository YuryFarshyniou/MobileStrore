package by.yurachel.springapp.model.phone.impl;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "phones")
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

    private static final long serialVersionUID = 6295618226040646585L;

    public Phone(String name, double price, String processor, String img) {
        this.name = name;
        this.price = price;
        this.processor = processor;
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProcessor() {
        return processor;
    }

    public void setProcessor(String processor) {
        this.processor = processor;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public Phone(long id, String name, double price, String processor, String img) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.processor = processor;
        this.img = img;
    }

    public Phone() {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Phone phone = (Phone) o;
        return id == phone.id && Double.compare(phone.price, price) == 0
                && Objects.equals(name, phone.name) && Objects.equals(processor, phone.processor)
                && Objects.equals(img, phone.img);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, price, processor, img);
    }

    @Override
    public String toString() {
        return "id: " + id + ", name: " + name + ", price: " + price + ", processor: " + processor;
    }
}
