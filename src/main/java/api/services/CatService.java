package api.services;
import java.io.BufferedReader;

import java.util.List;

import com.google.gson.Gson;

import api.interfaceService.InterfaceService;
import api.models.Cat;
import api.payloads.CatPayLoads;

public class CatService implements InterfaceService{
    private Cat cat;
    private Gson gson;
    
    public CatService() {
        this.cat = new Cat();
        this.gson = new Gson();
    }

    @Override
    public  List<Object> index() {
        return cat.index();
    }

    @Override
    public Object store(BufferedReader body){
        try {
            CatPayLoads catPayLoads = gson.fromJson(body, CatPayLoads.class);
            CatPayLoads AddedCat = cat.save(catPayLoads);
            return AddedCat;
        } catch (Exception e) {
            return null;
        }
    }
    
}
