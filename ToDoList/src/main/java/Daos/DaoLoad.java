package Daos;

import Classes.Category;
import Classes.Task;

public interface DaoLoad {

    public abstract String LoadToDoList(Task task);
    public abstract String LoadDoingList(Task task);
    public abstract String LoadDone(Task task);

    public abstract String LoadUserName(Task task);


}
