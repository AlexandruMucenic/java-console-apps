package org.productInventory.domain;

public class Product extends BaseEntity<Long> {
    private String name;
    private String brand;
    private String availability;

    public Product(String name, String brand, String availability) {
        this.name = name;
        this.brand = brand;
        this.availability = availability;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", brand='" + brand + '\'' +
                ", availability='" + availability + '\'' +
                '}' + super.toString();
    }
}
