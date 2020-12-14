package bakery.entities.bakedFoods.interfaces;

public class Cake extends BaseFood{
    public static final double DEFAULT_PORTION = 245;
    public Cake(String name, double price) {
        super(name, DEFAULT_PORTION, price);
    }
}
