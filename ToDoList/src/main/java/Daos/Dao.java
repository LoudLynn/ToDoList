package Daos;

import Classes.Task;
import javafx.collections.ObservableList;

public interface Dao <t> {
    public abstract void insert(t item);

    public abstract void delete(t item);

    public abstract void update(t item);
    public abstract void select(t item);
    public ObservableList<t> getList();
}
