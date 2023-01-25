package api.interfaceService;


import java.sql.ResultSet;
import java.sql.SQLException;

import api.payloads.CatPayLoads;

public interface InterfaceCatRepository {
    public ResultSet getAll();
    public ResultSet save(CatPayLoads animal) throws SQLException;
    public ResultSet delete(CatPayLoads animal) throws SQLException;
    public ResultSet update(CatPayLoads animal) throws SQLException;
    public ResultSet getOne(CatPayLoads animal);
}
