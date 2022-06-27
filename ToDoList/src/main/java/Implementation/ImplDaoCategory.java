package Implementation;

import Classes.Category;
import Connexion.Connect;
import Daos.Dao;
import Daos.LoadComboxCategories;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ImplDaoCategory implements Dao<Category>, LoadComboxCategories {
    Connection cn;
    ResultSet rs;
    PreparedStatement ps;
    Statement stm;
    @Override
    public void insert(Category item) {
        cn= Connect.getConnection();

        try {
            String sql="insert into Users (namecat) values (?)";
            ps =cn.prepareStatement(sql);
            ps.setString(1,item.getNameCat());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(Category item) {
        cn= Connect.getConnection();

        try {
            String sql="delete from category where where idcat =?";
            ps =cn.prepareStatement(sql);
            ps.setInt(1, item.getIdCat());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void update(Category item) {
        cn= Connect.getConnection();

        try {
            String sql="update category set namecat=? where idcat =?";
            ps =cn.prepareStatement(sql);
            ps.setString(1,item.getNameCat());
            ps.setInt(2, item.getIdCat());
            ps.executeUpdate();

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    @Override
    public void select(Category item) {
        cn= Connect.getConnection();

        try {
            String sql="select * from Category ";
            ps =cn.prepareStatement(sql);
            ps.executeQuery();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Category> getList() {
        ObservableList<Category> CategoryList = FXCollections.observableArrayList();
        cn= Connect.getConnection();
        try{
            /*String sql="select * from Category ";
            ps =cn.prepareStatement(sql);
            rs=ps.executeQuery();*/
            rs = cn.createStatement().executeQuery("select * from Category");
            Category category;
            while(rs.next()){
                category =new Category(rs.getInt("idCat"),rs.getString("nameCat"));
                CategoryList.add(category);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return CategoryList;
    }

    @Override
    public void LoadCategoriesComBox(Category category) {
        String query="select nameCat from Category";
        try{
            rs = cn.createStatement().executeQuery(query);
            while(rs.next()){
                category =new Category(rs.getString("nameCat"));
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
