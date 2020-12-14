package bakery.core;

import bakery.common.ExceptionMessages;
import bakery.common.OutputMessages;
import bakery.core.interfaces.Controller;
import bakery.entities.bakedFoods.interfaces.BakedFood;
import bakery.entities.bakedFoods.interfaces.Bread;
import bakery.entities.bakedFoods.interfaces.Cake;
import bakery.entities.drinks.interfaces.Drink;
import bakery.entities.drinks.interfaces.Tea;
import bakery.entities.drinks.interfaces.Water;
import bakery.entities.tables.interfaces.InsideTable;
import bakery.entities.tables.interfaces.OutsideTable;
import bakery.entities.tables.interfaces.Table;
import bakery.repositories.interfaces.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static bakery.common.ExceptionMessages.FOOD_OR_DRINK_EXIST;
import static bakery.common.ExceptionMessages.TABLE_EXIST;
import static bakery.common.OutputMessages.*;

public class ControllerImpl implements Controller {
    private List<BakedFood> foods;
    private List<Drink> drinks;
    private List<Table> tables;
    private List<Double>payedBills;

    public ControllerImpl(FoodRepository<BakedFood> foodRepository, DrinkRepository<Drink> drinkRepository, TableRepository<Table> tableRepository) {
        this.foods = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.tables = new ArrayList<>();
        this.payedBills=new ArrayList<>();
    }


    @Override
    public String addFood(String type, String name, double price) {
        for (BakedFood baked : foods) {
            if (baked.getName().equalsIgnoreCase(name)) {
                throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
            }
        }
            BakedFood bakedFood = null;
            if (type.equals("Bread")) {
                bakedFood = new Bread(name, price);
            } else if (type.equals("Cake")) {
                bakedFood = new Cake(name, price);
            }
            foods.add(bakedFood);
            return String.format(FOOD_ADDED, name, type);

    }

    @Override
    public String addDrink(String type, String name, int portion, String brand) {
        for (Drink drink : drinks) {
            if (drink.getName().equalsIgnoreCase(name)) {
                throw new IllegalArgumentException(String.format(FOOD_OR_DRINK_EXIST, type, name));
            }
        }
            Drink drink1 = null;
            if (type.equals("Tea")) {
                drink1  = new Tea(name,portion,brand);
            } else if (type.equals("Water")) {
                drink1  = new Water(name,portion,brand);
            }
            drinks.add(drink1);
            return String.format(DRINK_ADDED, name, brand);

    }

    @Override
    public String addTable(String type, int tableNumber, int capacity) {
        for (Table table : tables) {
            if (table.getTableNumber()==tableNumber) {
                throw new IllegalArgumentException(String.format(TABLE_EXIST,tableNumber));
            }
        }
        Table table = null;
        if (type.equals("InsideTable")) {
            table  = new InsideTable(tableNumber,capacity,1);
        } else if (type.equals("OutsideTable")) {
            table  = new OutsideTable(tableNumber,capacity,1);
        }
        tables.add(table);
        return String.format(TABLE_ADDED, tableNumber);

    }


    @Override
    public String reserveTable(int numberOfPeople) {
        boolean isTrue = false;
        String str = "";
        for (Table tables:tables) {
            if(!tables.isReserved()){
                tables.reserve(numberOfPeople);
                isTrue=true;
               str = String.format(TABLE_RESERVED,tables.getTableNumber(),numberOfPeople);
               break;
            }
        }

        if(isTrue=false){
            return String.format(RESERVATION_NOT_POSSIBLE,numberOfPeople);
        }else{
           return str;
        }

    }

    @Override
    public String orderFood(int tableNumber, String foodName) {
        boolean isTable =false;
        Table table = null;
        for (Table tables:tables) {
            if(tables.getTableNumber()==tableNumber&&tables.isReserved()==true){
                table = tables;
                isTable=true;
            }
        }
        if(isTable==false){
            return String.format(WRONG_TABLE_NUMBER,tableNumber);
        }

        boolean isFood = false;
        BakedFood food = null;
        for (BakedFood bakedFood:foods) {
            if(bakedFood.getName().equalsIgnoreCase(foodName)){
                food = bakedFood;
                isFood=true;
            }
        }
        if(isFood==false){
            return String.format(NONE_EXISTENT_FOOD,foodName);
        }

        table.orderFood(food);
        return String.format(FOOD_ORDER_SUCCESSFUL,tableNumber,foodName);


    }

    @Override
    public String orderDrink(int tableNumber, String drinkName, String drinkBrand) {

        boolean isTable =false;
        Table table = null;
        for (Table tables:tables) {
            if(tables.getTableNumber()==tableNumber){
                table = tables;
                isTable=true;
            }
        }
        if(isTable==false){
            return String.format(WRONG_TABLE_NUMBER,tableNumber);
        }

        boolean isDrink = false;
        Drink drink = null;
        for (Drink drink1:drinks) {
            if(drink1.getName().equalsIgnoreCase(drinkName)&&drink1.getBrand().equalsIgnoreCase(drinkBrand)){
                drink = drink1;
                isDrink=true;
            }
        }
        if(isDrink==false){
            return String.format(NON_EXISTENT_DRINK,drinkName,drinkBrand);
        }

        table.orderDrink(drink);
        return String.format(DRINK_ORDER_SUCCESSFUL,tableNumber,drinkName,drinkBrand);

    }

    @Override
    public String leaveTable(int tableNumber) {
        boolean isTable =false;
        Table table = null;
        for (Table tables:tables) {
            if(tables.getTableNumber()==tableNumber){
                table = tables;
                isTable=true;
            }
        }
        if(isTable==false){
            return String.format(WRONG_TABLE_NUMBER,tableNumber);
        }

        double bill =table.getBill();
        payedBills.add(bill);
        table.clear();

        return String.format(BILL,tableNumber,bill);


    }

    @Override
    public String getFreeTablesInfo() {
        List<Table>notReserved = new ArrayList<>();
        for (Table table:tables) {
            if(table.isReserved()==false){
                notReserved.add(table);
            }
        }
        String freeTableInfo = "";
        for (Table table:notReserved) {
            freeTableInfo+=table.getFreeTableInfo();
        }

        return freeTableInfo;

    }

    @Override
    public String getTotalIncome() {
        double sum =0;
        for (Double bill:payedBills) {
            sum+=bill;
        }
        return String.format(TOTAL_INCOME,sum);






    }
}
