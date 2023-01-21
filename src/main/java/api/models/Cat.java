package api.models;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import api.payloads.CatPayLoads;
import api.repositories.mysql.MysqlConection;

public class Cat {
    private int id;
    private String name;

    MysqlConection repostory = new MysqlConection();
    String table = "cats";

    public Cat(){

    }

    public Cat(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Cat [id=" + id + ", name=" + name + "]";
    }
    
    public List<Object> index(){

        try {
          Statement statement = repostory.conn.createStatement();
          String sql = String.format("SELECT * FROM %s;", table); 
          
          ResultSet rs = statement.executeQuery(sql);

          List<Object> cats = new ArrayList<>(); 
          while (rs.next()) {
            CatPayLoads cat = new CatPayLoads();
            cat.setName(rs.getString("name"));
            cat.setId(rs.getInt("id_cat"));
            cats.add(cat);
          }
          System.out.println(cats);
          return cats;
        } catch (Exception e) {
            System.out.println("error" + e.getMessage());
            return null;
        }
        

        
    }

    public CatPayLoads save(CatPayLoads catPayLoads) throws SQLException {
        String mySql_insert= String.format("INSERT INTO %s (id_cat,name) VALUES (?,?);", table);
        PreparedStatement preparedStatement = repostory.conn.prepareStatement(mySql_insert);
        preparedStatement.setInt(1, catPayLoads.getId());
        preparedStatement.setString(2, catPayLoads.getName());
        preparedStatement.executeUpdate();
        preparedStatement.close();

        Statement statement = repostory.conn.createStatement();
        String mySql_select = String.format("SELECT * FROM %s ORDER BY id_cat DESC LIMIT 1;", table);
        
        ResultSet rs = statement.executeQuery(mySql_select);
        while(rs.next()){
            catPayLoads.setId(rs.getInt("id_cat"));
            catPayLoads.setName(rs.getString("name"));
        }

        return catPayLoads;
    }
    public List<Object> delete(CatPayLoads catPayLoads) throws SQLException{
        
        String mySql_delete = String.format("DELETE FROM %s WHERE id_cat = (?);", table);
        PreparedStatement preparedStatement = repostory.conn.prepareStatement(mySql_delete);
        preparedStatement.setInt(1, catPayLoads.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();


        return index();
    }
    public Object upload(CatPayLoads catPayLoads) throws SQLException{
        
        String mySql_update = String.format("UPDATE %s set id_cat = ?, name = ? WHERE id_cat= ?;", table);
        PreparedStatement preparedStatement = repostory.conn.prepareStatement(mySql_update);
        preparedStatement.setInt(1, catPayLoads.getId());
        preparedStatement.setString(2, catPayLoads.getName());
        preparedStatement.setInt(3, catPayLoads.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();

        return getOne(catPayLoads);
        
    }
    public Object getOne(CatPayLoads catPayLoads) throws SQLException{
        try {
            Statement statement = repostory.conn.createStatement();
            String mySql_select = String.format("SELECT * FROM %s WHERE id_cat = %s;",table, catPayLoads.getId());
        
            ResultSet rs = statement.executeQuery(mySql_select);
            CatPayLoads cat = new CatPayLoads();
        while (rs.next()) {
            cat.setId(rs.getInt("id_cat"));
            cat.setName(rs.getString("name")); 
        }
        return cat;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
    
}
