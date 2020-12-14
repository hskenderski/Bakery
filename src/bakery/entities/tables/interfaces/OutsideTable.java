package bakery.entities.tables.interfaces;

public class OutsideTable extends BaseTable{
    public OutsideTable(int tableNumber, int capacity, double pricePerPerson) {
        super(tableNumber, capacity, pricePerPerson * 3.50);
    }
}
