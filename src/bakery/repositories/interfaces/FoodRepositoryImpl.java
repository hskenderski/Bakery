package bakery.repositories.interfaces;

import bakery.entities.bakedFoods.interfaces.BaseFood;

import java.util.ArrayList;
import java.util.Collection;

public class FoodRepositoryImpl implements FoodRepository{
    private Collection<Object> models;

    public FoodRepositoryImpl(){
        this.models = new ArrayList<>();
    }

    @Override
    public Object getByName(String name) {
        return name;
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
