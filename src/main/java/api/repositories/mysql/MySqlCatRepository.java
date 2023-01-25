package api.repositories.mysql;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



import api.interfaceService.InterfaceCatRepository;
import api.payloads.CatPayLoads;

public class MySqlCatRepository implements InterfaceCatRepository {

    private MysqlConection repository;
    private String table = "cats";
    
    public MySqlCatRepository() {
        this.repository = new MysqlConection();
    }

    @Override
    public ResultSet getAll() {
        try {
            Statement statement = repository.conn.createStatement();
            String mySql_select = String.format("SELECT * FROM %s", table);
            ResultSet rs = statement.executeQuery(mySql_select);
            return rs;
        } catch (Exception e) {
            System.out.println("Error in mysqlrepository:" + e.getMessage());
            return null;
        }
    }

    @Override
    public ResultSet save(CatPayLoads animal) throws SQLException {
        String mySql_insert = String.format("INSERT INTO %s VALUES (?,?)", table);
        PreparedStatement preparedStatement = repository.conn.prepareStatement(mySql_insert);
        preparedStatement.setInt(1, animal.getId());
        preparedStatement.setString(2, animal.getName());
        preparedStatement.executeUpdate();
        preparedStatement.close();

        return getOne(animal);
    }

    @Override
    public ResultSet getOne(CatPayLoads animal) {
        try {
            
            String mySql_select = String.format("SELECT * FROM %s WHERE id_cat = ?", table);
            PreparedStatement preparedStatement = repository.conn.prepareStatement(mySql_select);
            preparedStatement.setInt(1, animal.getId());
            ResultSet rs = preparedStatement.executeQuery();
            return rs;
        } catch (Exception e) {
            System.out.println("Error in mysqlrepository:" + e.getMessage());
            return null;
        }
        
    }

    @Override
    public ResultSet delete(CatPayLoads animal) throws SQLException {
        String mySql_delete = String.format("DELETE FROM %s WHERE id_cat = (?);", table);
        PreparedStatement preparedStatement = repository.conn.prepareStatement(mySql_delete);
        preparedStatement.setInt(1, animal.getId());
        preparedStatement.executeUpdate();
        preparedStatement.close();

        return getAll();
    }

    @Override
    public ResultSet update(CatPayLoads animal) throws SQLException {
        String mySql_update = String.format("UPDATE %s set id_cat = ?, name = ? WHERE id_cat= ?;", table);
        PreparedStatement preparedStatement = repository.conn.prepareStatement(mySql_update);
        preparedStatement.setInt(1, animal.getId());
        preparedStatement.setString(2, animal.getName());
        preparedStatement.setInt(3, animal.getId());
        preparedStatement.executeUpdate();

        return getOne(animal);
    }
    
    
}
