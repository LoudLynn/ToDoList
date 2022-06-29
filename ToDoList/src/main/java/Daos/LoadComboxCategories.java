package Daos;

import Classes.Category;
import javafx.collections.ObservableList;

public interface LoadComboxCategories {

    public abstract void LoadCategoriesComBox(Category category, ObservableList<Category> categoriesList);
}
