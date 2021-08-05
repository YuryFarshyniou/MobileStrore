package by.yurachel.springapp.model.phone.impl;

import by.yurachel.springapp.model.order.impl.Order;
import by.yurachel.springapp.model.phone.OperatingSystem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "phones")
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Phone implements Serializable {
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
    @Enumerated(value = EnumType.STRING)
    private OperatingSystem os;
    @Temporal(TemporalType.DATE)
    private Date dateOfAdded;
    @NotNull(message = "Screen size shouldn't be null")
    private double screenSize;
    @NotNull(message = "RAM shouldn't be null")
    private int randomAccessMemory;
    @NotNull(message = "Number of cameras shouldn't be null")
    private int numberOfMainCameras;
    @NotNull(message = "Number of matrix points shouldn't be null")
    private int numberOfMatrixPoints;
    @NotNull(message = "Number of sim cards shouldn't be null")
    private int numberOfSimCards;
    @NotNull(message = "CPU clock speed shouldn't be null")
    private int cpuClockSpeed;
    @NotNull(message = "Graphics accelerator shouldn't be null")
    private String graphicsAccelerator;
    @NotNull(message = "Body material shouldn't be null")
    private String bodyMaterial;
    @NotNull(message = "back cover material shouldn't be null")
    private String backCoverMaterial;
    @NotNull(message = "Body color shouldn't be null")
    private String bodyColor;
    @NotNull(message = "Length shouldn't be null")
    private double length;
    @NotNull(message = "Width shouldn't be null")
    private double width;
    @NotNull(message = "Thickness shouldn't be null")
    private double thickness;
    @NotNull(message = "Weight shouldn't be null")
    private int weight;
    @NotNull(message = "Screen technology shouldn't be null")
    private String screenTechnology;
    @NotNull(message = "Screen refresh rate shouldn't be null")
    private int screenRefreshRate;
    @NotNull(message = "Accumulator capacity shouldn't be null")
    private int accumulatorCapacity;
    @NotNull(message = "Accumulator type shouldn't be null")
    private String accumulatorType;
    private String wifi;
    @NotNull(message = "Connection connector shouldn't be null")
    private String connectionConnector;

    private static final long serialVersionUID = 6295618226040646585L;

    @ManyToMany(mappedBy = "phones")
    @ToString.Exclude
    private List<Order> orders = new ArrayList<>();

    @ElementCollection
    private List<String> images = new ArrayList<>();

    public void addImage(String imageLink) {
        images.add(imageLink);
    }

    public Phone() {
        dateOfAdded = new Date();
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
