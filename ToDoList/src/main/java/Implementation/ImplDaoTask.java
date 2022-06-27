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
            preparedStmt.setDate (4, Date.valueOf(item.getDeadline().toString()));
            preparedStmt.setInt (5, item.getIdct());
            preparedStmt.setInt (6, item.getIdur());

            System.out.println(preparedStmt);
            preparedStmt.executeUpdate();
            //cn.close();

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
            // cn.close();


        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Task item) {

    }

    @Override
    public void select(Task item) {

    }

    @Override
    public ObservableList<Task> getList() {
            ObservableList<Task> taskList = FXCollections.observableArrayList();
            cn= Connect.getConnection();
            String query="select * from Task";
            Statement st;
            ResultSet rs;
            try{
                st=cn.createStatement();
                rs=st.executeQuery(query);
                while(rs.next()){
                    Task ts =new Task(rs.getString("Title"),rs.getString("Description"),rs.getString("Status"),rs.getDate("Deadline"),rs.getInt("Idct"));
                    taskList.add(ts);
                }

            }catch(Exception e){
                e.printStackTrace();
            }
            return taskList;

        }

    @Override
    public String LoadToDoList(Task task) {
        Connection cn = Connect.getConnection();

        try {
            stm=cn.createStatement();
            String query = "select * from Task where status = To do";

            rs=stm.executeQuery(query);
            while(rs.next()){
                //task.setTitle(rs.getString("title"));

                String title=rs.getString("title");
                String deadline=rs.getString("deadline");



            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public String LoadDoingList(Task task) {
        Connection cn = Connect.getConnection();

        try {
            stm=cn.createStatement();
            String query = "select * from Task where status = Doing";

            rs=stm.executeQuery(query);
            while(rs.next()){
                String title=rs.getString("title");
                String deadline=rs.getString("deadline");


            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public String LoadDone(Task task) {
        Connection cn = Connect.getConnection();
        String ListOut ;

        try {
            stm=cn.createStatement();
            String query = "select * from Task where status = Done";

            rs=stm.executeQuery(query);
            while(rs.next()){
                String title=rs.getString("title");
                String deadline=rs.getString("deadline");
                return  title + "\""+deadline;
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public String LoadUserName(Task task){
        return null;
    }
}
