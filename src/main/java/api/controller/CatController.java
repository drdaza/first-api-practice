package api.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import api.models.Cat;
import api.models.Message;
import api.payloads.CatPayLoads;
import api.views.View;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api/cats")
public class CatController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("application/json; charset= utf-8");

        PrintWriter out = resp.getWriter();

        Cat cat = new Cat();

        List<CatPayLoads> cats =  cat.index();
        
        try {
          out.println(View.show(cats));  
          resp.setStatus(HttpServletResponse.SC_OK);
          out.close();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Message message = new Message();
            message.setMessage("error en la carga de datos" + e.getMessage());
            out.println(View.show(message));
        }
        
    }
}
