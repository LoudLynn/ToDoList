module brief8.todolist {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires fontawesomefx;


    opens brief8.todolist to javafx.fxml;
    exports brief8.todolist;
}