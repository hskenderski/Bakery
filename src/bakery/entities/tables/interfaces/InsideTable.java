package bakery.entities.tables.interfaces;

public class InsideTable extends BaseTable{
    public InsideTable(int tableNumber, int capacity, double pricePerPerson) {
        super(tableNumber, capacity, pricePerPerson * 2.50);
    }
}
