package Implementation;

import Classes.Category;
import Classes.Task;
import Connexion.Connect;
import Daos.Dao;
import Daos.DaoLoad;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.*;

public class ImplDaoTask implements Dao <Task>, DaoLoad {

    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    Statement stm;
    @Override
    public void insert(Task item) {
        Connection cn = Connect.getConnection();
        try {
            String sql = "Insert into Task (Title, Description, Status, Deadline, Idct,idur) values  (?,?,?,?,?)";
            PreparedStatement preparedStmt = cn.prepareStatement(sql);


            preparedStmt.setString (1, item.getTitle());
            preparedStmt.setString (2, item.getDescription());
            preparedStmt.setString (3, item.getStatus());
            preparedStmt.setDate (4, Date.valueOf(item.getDeadline()));
            preparedStmt.setInt (5, item.getIdct());
            preparedStmt.setInt (6, item.getIdur());

            System.out.println(preparedStmt);
            preparedStmt.executeUpdate();
            preparedStmt.close();

            System.out.println ("Task inserted successfully");

        } catch (Exception e) {
            e.printStackTrace ();
        }
    }

    @Override
    public void delete(Task item) {
        Connection cn = Connect.getConnection();

        try {
            String query = "delete from Task where Title = ?";
            PreparedStatement preparedStmt = cn.prepareStatement(query);


            preparedStmt.setString(1, item.getTitle());
            preparedStmt.execute();
            preparedStmt.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Task item) {

        Connection cn = Connect.getConnection();

        try {
            String query = "update Task set Title=?, Description=?, Status=?, Deadline=?, Idct=?,idur=? where IdTask = ?";
            PreparedStatement preparedStmt = cn.prepareStatement(query);

            preparedStmt.setString (1, item.getTitle());
            preparedStmt.setString (2, item.getDescription());
            preparedStmt.setString (3, item.getStatus());
            preparedStmt.setDate (4, Date.valueOf(item.getDeadline()));
            preparedStmt.setInt (5, item.getIdct());
            preparedStmt.setInt (6, item.getIdur());

            preparedStmt.setString(7, item.getTitle());
            preparedStmt.execute();
            preparedStmt.close();


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void select(Task item) {
        Connection cn = Connect.getConnection();

        try {
            String query = "Select max(idtask) from task";

            stm = (Statement) cn.createStatement();
            rs= stm.executeQuery(query);

            while (rs.next()) {
                item.setIdTask(rs.getInt("max") + 1);
            }

            rs.close();
        }catch(Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Task> getList() {
            ObservableList<Task> GetList = FXCollections.observableArrayList();
            cn= Connect.getConnection();
            String query="select * from Task";
            Statement st;
            ResultSet rs;
            try{
                st=cn.createStatement();
                rs=st.executeQuery(query);
                while(rs.next()){
                    Task ts =new Task(rs.getString("Title"),rs.getString("Description"),rs.getString("Status"),rs.getString("Deadline"),rs.getInt("Idct"));
                    GetList.add(ts);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            return GetList;


        }
    @Override
    public void LoadToDoList(Task task,ObservableList<Task> tasksToDoList) {
        Connection cn = Connect.getConnection();
        String query = "select * from Task where status = To do";
        try {
            stm=cn.createStatement();
            rs=stm.executeQuery(query);
            while(rs.next()){
                task =new Task(rs.getString("title"),rs.getString("deadline"));
                tasksToDoList.add(task);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void LoadDoingList(Task task,ObservableList<Task> tasksDoingList) {
        Connection cn = Connect.getConnection();
        String query = "select * from Task where status = Doing";

        try {
            stm=cn.createStatement();

            rs=stm.executeQuery(query);
            while(rs.next()){
                task =new Task(rs.getString("title"),rs.getString("deadline"));
                tasksDoingList.add(task);


            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void LoadDone(Task task,ObservableList<Task> tasksDoneList) {
        Connection cn = Connect.getConnection();
        String query = "select * from Task where status = Done";


        try {
            stm=cn.createStatement();
            rs=stm.executeQuery(query);
            while(rs.next()){
                task =new Task(rs.getString("title"),rs.getString("deadline"));
                tasksDoneList.add(task);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public String LoadUserName(Task task){
        return null;
    }
}
