package bakery.entities.bakedFoods.interfaces;

import static bakery.common.ExceptionMessages.*;

public abstract class BaseFood implements BakedFood{
    private String name;
    private double portion;
    private double price;

    protected BaseFood(String name, double portion, double price){
        this.setName(name);
        this.setPortion(portion);
        this.setPrice(price);
    }

    private void setName(String name) {
        if(name == null || name.equals(" ")){
            throw new IllegalArgumentException(String.format(INVALID_NAME));
        }
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    private void setPortion(double portion) {
        if(portion <= 0){
            throw new IllegalArgumentException(String.format(INVALID_PORTION));
        }
        this.portion = portion;
    }

    @Override
    public double getPortion() {
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

    @Override
    public String toString(){
        return String.format("%s: %.2fg - %.2f",this.name,this.portion, this.price);
    }
}
