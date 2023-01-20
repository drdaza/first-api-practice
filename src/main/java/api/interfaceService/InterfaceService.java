package api.interfaceService;

import java.io.BufferedReader;
import java.util.List;

public interface InterfaceService {
    public List<Object> index();
    public Object store(BufferedReader body);
    public List<Object> delete(BufferedReader body);
    public  Object upload(BufferedReader body);
}
