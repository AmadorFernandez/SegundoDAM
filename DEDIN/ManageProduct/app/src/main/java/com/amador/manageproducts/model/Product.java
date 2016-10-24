package com.amador.manageproducts.model;

import java.util.UUID;

/**
 * Class Product, created by Amador (2016-10-19)
 */
public class Product {

    private int id;
    private String name;
    private String description;
    private String dosage;
    private String brand;
    private double price;
    private int stock;
    private int image;



    public Product(String name, String description, String brand, String dosage, double price, int stock, int image) {

        this.name = name;
        this.description = description;
        this.brand = brand;
        this.price = price;
        this.stock = stock;
        this.image = image;
        this.dosage = dosage;
    }

    /* Dos productos son iguales cuando tienen la misma marca, concentración y nombre */

    @Override
    public boolean equals(Object o) {

        boolean result = false;
        Product p;

        if(o != null){

            if(o instanceof Product){

                p = (Product)o;

                if(this.name.equals(p.name) && this.dosage.equals(p.dosage) && this.brand.equals(p.brand)){

                    result = true;
                }
            }

        }

        return result;
    }

    @Override
    public int hashCode() {
        int result = name.hashCode();
        result = 31 * result + dosage.hashCode();
        result = 31 * result + brand.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return this.name + " " + this.dosage;
    }

    //Id
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    //Name
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //Description
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    //Brand
    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    //Price
    public double getPrice() {
        return price;
    }

    public String getPriceFormat(){return String.format("$%s", price);}

    public void setPrice(double price) {
        this.price = price;
    }

    //Stock
    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    //Image
    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
