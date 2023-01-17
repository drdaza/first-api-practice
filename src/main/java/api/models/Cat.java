package api.models;

import java.sql.ResultSet;
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
    
    public List<CatPayLoads> index(){

        try {
          Statement statement = repostory.conn.createStatement();
          String sql = String.format("SELECT * FROM %s;", table); 
          
          ResultSet rs = statement.executeQuery(sql);

          List<CatPayLoads> cats = new ArrayList<>(); 
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
    
}
