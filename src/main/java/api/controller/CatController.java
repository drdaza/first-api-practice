package api.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import api.interfaceService.InterfaceService;

import api.models.Message;

import api.services.CatService;
import api.views.View;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(urlPatterns = "/api/cats")
public class CatController extends HttpServlet {
    private InterfaceService catService;
    public CatController(){
        this.catService = new CatService();
    }
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        resp.setContentType("application/json; charset= utf-8");

        PrintWriter out = resp.getWriter();

        

        
        
        try {
          List<Object> cats =  catService.index();
          out.println(View.show(cats));  
          resp.setStatus(HttpServletResponse.SC_OK);
          out.close();
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Message message = new Message();
            message.setMessage("Error en el traspaso de datos: " + e.getMessage());
            out.println(View.show(message));
        }
        
    }
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();

        BufferedReader reader = req.getReader();
        
        try {
            Object cat = catService.store(reader);
            out.println(View.show(cat));
            resp.setStatus(HttpServletResponse.SC_CREATED);
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            System.out.println("Error: " + e.getMessage());
        }
        


    }
    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();

        BufferedReader reader = req.getReader();
        try {
            List<Object> cats =  catService.delete(reader);
            out.println(View.show(cats));
            resp.setStatus(HttpServletResponse.SC_ACCEPTED);
            
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Message message = new Message();
            message.setMessage("Error al eliminar " + e.getMessage());
            out.println(View.show(message));
        }
    }
    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json;charset=utf-8");
        PrintWriter out = resp.getWriter();

        BufferedReader reader = req.getReader();

        try {
            
            Object cat = catService.upload(reader);
            System.out.println(cat);
            out.println(View.show(cat));
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            Message message = new Message();
            message.setMessage("Error al actualizar " + e.getMessage());
            out.println(View.show(message));
        }
    }
}
