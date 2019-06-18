package com.example.omaradel.productviewer;

import java.io.Serializable;

/**
 * Created by omar adel on 7/10/2018.
 */


public class product implements Serializable {
    private String productid;
    private String name;//that
    private String description;//that
    private double price;//that
    private String unit_price;
    private String product_type;
    private String image_url;//that
    private String shopping_list_item;
    private String shopping_cart;

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public product()
{

}

    public product(String productid, String name, String description, double price, String unit_price, String product_type, String image_url, String shopping_list_item,String shopping_cart) {
        this.productid = productid;
        this.name = name;
        this.description = description;
        this.price = price;
        this.unit_price = unit_price;
        this.product_type = product_type;
        this.image_url = image_url;
        this.shopping_list_item=shopping_list_item;
        this.shopping_cart=shopping_cart;

    }

    public String getProductid() {
        return productid;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public String getUnit_price() {
        return unit_price;
    }

    public String getProduct_type() {
        return product_type;
    }

    public String getImage_url() {
        return image_url;
    }

    public String getShopping_list_item() {
        return shopping_list_item;
    }

    public String getShopping_cart() {
        return shopping_cart;
    }
}