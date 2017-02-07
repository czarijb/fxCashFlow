package com.github.czarijb.objects;

import javafx.beans.property.*;

import javax.persistence.*;

/**
 * Created by aleksandr on 04.02.17.
 */
@Entity
@Table(name = "ASSETS")
public class Income {
    private LongProperty id = new SimpleLongProperty();
    private StringProperty name = new SimpleStringProperty("");
    private IntegerProperty price = new SimpleIntegerProperty();

    public Income() {
    }

    public Income(String name, int price) {
        this.name = new SimpleStringProperty(name);
        this.price = new SimpleIntegerProperty(price);
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    public long getId() {
        return id.get();
    }

    public LongProperty idProperty() {
        return id;
    }

    public void setId(long id) {
        this.id.set(id);
    }

    @Column(name = "name")
    public String getName() {
        return name.get();
    }

    public StringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Column(name = "price")
    public int getPrice() {
        return price.get();
    }

    public IntegerProperty priceProperty() {
        return price;
    }

    public void setPrice(int price) {
        this.price.set(price);
    }

    @Override
    public String toString() {
        return "Income{" +
                "name=" + name +
                ", price=" + price +
                '}';
    }
}
