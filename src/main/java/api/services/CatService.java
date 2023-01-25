package api.services;
import java.io.BufferedReader;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;

import api.interfaceService.InterfaceCatRepository;
import api.interfaceService.InterfaceService;
import api.models.Cat;
import api.payloads.CatPayLoads;

public class CatService implements InterfaceService{
    private Cat cat;
    private Gson gson;
    private InterfaceCatRepository repository;
    
    public CatService(InterfaceCatRepository repository) {
        this.cat = new Cat();
        this.gson = new Gson();
        this.repository = repository;
    }

    @Override
    public  List<Object> index() {
        return cat.index(repository.getAll());
    }

    @Override
    public Object store(BufferedReader body){
        try {
            CatPayLoads catPayload = gson.fromJson(body, CatPayLoads.class);
            ResultSet rs = repository.save(catPayload);
            CatPayLoads cat = new CatPayLoads();
            while (rs.next()) {
              
              cat.setName(rs.getString("name"));
              cat.setId(rs.getInt("id_cat"));
              
            }
            
            return cat;
          } catch (Exception e) {
              System.out.println("error en el servicio" + e.getMessage());
              return null;
          }     
    }

    @Override
    public List<Object> delete(BufferedReader body) {
        try {
            CatPayLoads catPayload = gson.fromJson(body, CatPayLoads.class);
            ResultSet rs = repository.delete(catPayload);
            List<Object> cats = new ArrayList<>(); 
            while (rs.next()) {
              CatPayLoads cat = new CatPayLoads();
              cat.setName(rs.getString("name"));
              cat.setId(rs.getInt("id_cat"));
              cats.add(cat);
            }
            return cats;
          } catch (Exception e) {
              System.out.println("error en el servicio" + e.getMessage());
              return null;
          } 
        
    }

    @Override
    public Object upload(BufferedReader body) {
        try {
            CatPayLoads catPayload = gson.fromJson(body, CatPayLoads.class);
            ResultSet rs = repository.update(catPayload);
            List<Object> cats = new ArrayList<>(); 
            while (rs.next()) {
              CatPayLoads cat = new CatPayLoads();
              cat.setName(rs.getString("name"));
              cat.setId(rs.getInt("id_cat"));
              cats.add(cat);
            }
            return cats;
          } catch (Exception e) {
              System.out.println("error en el servicio" + e.getMessage());
              return null;
          } 
    }

    
    
    
}
