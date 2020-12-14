package bakery.entities.tables.interfaces;

import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.bakedFoods.interfaces.BaseFood;
import bakery.entities.drinks.interfaces.Drink;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import static bakery.common.ExceptionMessages.INVALID_NUMBER_OF_PEOPLE;
import static bakery.common.ExceptionMessages.INVALID_TABLE_CAPACITY;

public abstract class BaseTable implements Table{
    private Collection<BakedFood> foodOrders;
    private Collection<Drink> drinkOrders;
    private int tableNumber;
    private int capacity;
    private int numberOfPeople;
    private double pricePerPerson;
    private boolean isReserved=false;
    private double price;

    public Collection<Drink> getDrinkOrders() {
        return drinkOrders;
    }

    public Collection<BakedFood> getFoodOrders() {
        return foodOrders;
    }

    protected BaseTable(int tableNumber, int capacity, double pricePerPerson){
        this.tableNumber = tableNumber;

        setCapacity(capacity);
        setPricePerPerson(pricePerPerson);
        this.foodOrders = new ArrayList<>();
        this.drinkOrders = new ArrayList<>();
    }



    private void setPricePerPerson(double pricePerPerson) {
        if(capacity < 0){
            throw new IllegalArgumentException(INVALID_TABLE_CAPACITY);
        }
        this.pricePerPerson = pricePerPerson;
    }

    @Override
    public int getTableNumber() {
        return this.tableNumber;
    }

    private void setCapacity(int capacity) {
        if(capacity < 0){
            throw new IllegalArgumentException(String.format(INVALID_TABLE_CAPACITY));
        }
        this.capacity = capacity;
    }

    @Override
    public int getCapacity() {
        return this.capacity;
    }

    private void setNumberOfPeople(int numberOfPeople) {
        if(numberOfPeople <= 0){
            throw new IllegalArgumentException(String.format(INVALID_NUMBER_OF_PEOPLE));
        }
        this.numberOfPeople = numberOfPeople;
    }

    @Override
    public int getNumberOfPeople() {
        return this.numberOfPeople;
    }

    @Override
    public double getPricePerPerson() {
        return this.pricePerPerson;
    }

    @Override
    public boolean isReserved() {
        return this.isReserved;
    }

    private void setReserved(boolean reserved) {
        isReserved = reserved;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public void reserve(int numberOfPeople) {
       if(isReserved==false&&getCapacity()>=numberOfPeople){
           setReserved(true);
           setNumberOfPeople(numberOfPeople);
       }
    }

    @Override
    public void orderFood(BakedFood food) {
       foodOrders.add(food);
    }

    @Override
    public void orderDrink(Drink drink) {
        drinkOrders.add(drink);
    }

    @Override
    public double getBill() {
        double endPrice = 0;

        for(BakedFood bakedFood : foodOrders){
            endPrice += bakedFood.getPrice();
        }
        for(Drink drink : drinkOrders){
            endPrice += drink.getPrice();
        }

            endPrice+=numberOfPeople*pricePerPerson;

        return endPrice;
    }

    @Override
    public void clear() {
        this.drinkOrders.clear();
        this.foodOrders.clear();
        this.tableNumber = 0;
        this.numberOfPeople = 0;
        this.price = 0;
    }

    @Override
    public String getFreeTableInfo() {
        return "Table: " + this.tableNumber + System.lineSeparator()
                + "Type: " + this.getClass().getSimpleName() + System.lineSeparator()
                + "Capacity: " + this.capacity + System.lineSeparator()
                + String.format("Price per Person: %.2f%n",this.pricePerPerson);
    }
}
