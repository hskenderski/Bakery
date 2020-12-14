package bakery.entities.bakedFoods.interfaces;

public class Bread extends BaseFood{
    public static final double DEFAULT_PORTION = 200;
    public Bread(String name, double price) {
        super(name, DEFAULT_PORTION , price);
    }
}
