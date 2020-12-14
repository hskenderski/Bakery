package bakery.entities.drinks.interfaces;

import bakery.entities.bakedFoods.interfaces.BaseFood;

import static bakery.common.ExceptionMessages.*;

public abstract class BaseDrink implements Drink{
    private String name;
    private int portion;
    private double price;
    private String brand;

    protected BaseDrink(String name, int portion, double price, String  brand){
        this.setName(name);
        this.setPortion(portion);
        this.setPrice(price);
         this.setBrand(brand);
    }

    private void setName(String name) {
        if(name == null || name.equals(" ")){
            throw new IllegalArgumentException(String.format(INVALID_NAME));
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    private void setPortion(int portion) {
        if(portion <= 0){
            throw new IllegalArgumentException(String.format(INVALID_PORTION));
        }
        this.portion = portion;
    }

    @Override
    public int getPortion() {
        return this.portion;
    }

    private void setPrice(double price) {
        if(price <= 0){
            throw new IllegalArgumentException(String.format(INVALID_PRICE));
        }
        this.price = price;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    private void setBrand(String brand) {
        if(brand == null || brand.equals(" ")){
            throw new IllegalArgumentException(String.format(INVALID_BRAND));
        }
        this.brand = brand;
    }

    @Override
    public String getBrand() {
        return this.brand;
    }

    @Override
    public String toString(){
        return String.format("%s %s - %dml - %.2flv",this.name, this.brand, this.portion, this.price);
    }
}
