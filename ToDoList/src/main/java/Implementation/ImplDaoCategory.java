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
            String sql="insert into category (namecat) values (?)";
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
            stm = (Statement) cn.createStatement();
            rs= stm.executeQuery(sql);
            while(rs.next()){
                item.setIdCat(rs.getInt("max") + 1);

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public ObservableList<Category> getList() {
        ObservableList<Category> GetList = FXCollections.observableArrayList();
        cn= Connect.getConnection();
        Category category;
        try{
            rs = cn.createStatement().executeQuery("select * from Category");

            while(rs.next()){
                category =new Category(rs.getInt("idCat"),rs.getString("nameCat"));
                GetList.add(category);
            }

        }catch(Exception e){
            e.printStackTrace();
        }
        return GetList;
    }

    @Override
    public void LoadCategoriesComBox(Category category,ObservableList<Category> categoriesList) {
        String query="select * from Category";
        cn= Connect.getConnection();

        try{
            rs = cn.createStatement().executeQuery(query);
            while(rs.next()){
                category =new Category(rs.getInt("idcat"),rs.getString("nameCat"));
                categoriesList.add(category);
            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }
}
