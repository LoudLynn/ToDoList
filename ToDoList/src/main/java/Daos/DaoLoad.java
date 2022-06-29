package Daos;

import Classes.Category;
import Classes.Task;
import javafx.collections.ObservableList;

public interface DaoLoad {

    public abstract void LoadToDoList(Task task, ObservableList<Task> tasksToDoList);
    public abstract void LoadDoingList(Task task,ObservableList<Task> tasksDoingList);
    public abstract void LoadDone(Task task,ObservableList<Task> tasksDoneList);

    public abstract String LoadUserName(Task task);


}
