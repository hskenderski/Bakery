package bakery.repositories.interfaces;

import bakery.entities.tables.interfaces.Table;

import java.util.ArrayList;
import java.util.Collection;

public class TableRepositoryImpl implements TableRepository{
    Collection<Object> models;

    public TableRepositoryImpl(){
        this.models = new ArrayList<>();
    }

    @Override
    public Object getByNumber(int number) {
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
