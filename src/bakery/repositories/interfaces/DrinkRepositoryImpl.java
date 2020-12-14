package bakery.repositories.interfaces;



import java.util.Collection;

public class DrinkRepositoryImpl implements DrinkRepository{
    private Collection<Object> models;

    @Override
    public Object getByNameAndBrand(String drinkName, String drinkBrand) {
        return getClass().getSimpleName();
    }

    @Override
    public Collection getAll() {
        return models;
    }

    @Override
    public void add(Object o) {
        models.add(o);
    }
}
