package com.github.czarijb.objects;

import javafx.beans.property.*;

import javax.persistence.*;

/**
 * Created by aleksandr on 04.02.17.
 */
@Entity
@Table(name = "ASSETS")
public class Assets {

    private LongProperty id = new SimpleLongProperty();
    private StringProperty name = new SimpleStringProperty("");
    private IntegerProperty volume = new SimpleIntegerProperty();
    private IntegerProperty price = new SimpleIntegerProperty();
    private IntegerProperty totalPrice = new SimpleIntegerProperty();

    public Assets() {
    }

    public Assets(String name, int volume, int price) {
        this.name = new SimpleStringProperty(name);
        this.volume = new SimpleIntegerProperty(volume);
        this.price = new SimpleIntegerProperty(price);
        this.totalPrice = new SimpleIntegerProperty(volume * price);
    }

    public Assets(String name, int volume, int price, int totalPrice) {
        this.name = new SimpleStringProperty(name);
        this.volume = new SimpleIntegerProperty(volume);
        this.price = new SimpleIntegerProperty(price);
        this.totalPrice = new SimpleIntegerProperty(totalPrice);
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

    @Column(name = "volume")
    public int getVolume() {
        return volume.get();
    }

    public IntegerProperty volumeProperty() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume.set(volume);
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

    @Column(name = "total_price")
    public int getTotalPrice() {
        return totalPrice.get();
    }

    public IntegerProperty totalPriceProperty() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice.set(totalPrice);
    }

    @Override
    public String toString() {
        return "Assets{" +
                "name=" + name +
                ", volume=" + volume +
                ", price=" + price +
                ", totalPrice=" + totalPrice +
                '}';
    }
}
