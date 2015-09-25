/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlet;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import db.dbFacade;
import entity.Person;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import utils.JSONConverter;

/**
 *
 * @author AlexanderSteen
 */
@WebServlet(name = "RestServlet", urlPatterns = {"/api/person/*"})
public class RestServlet extends HttpServlet {

    private dbFacade facade = new dbFacade();

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        String[] parts = request.getRequestURI().split("/");
        String parameter = null;
        if (parts.length == 4) {
            parameter = parts[3];
            int id = Integer.parseInt(parameter);

            Person p = facade.getPerson(id);

            response.getWriter().println(JSONConverter.getJSONFromPerson(p));
        } else {
            response.getWriter().println(JSONConverter.getJSONFromPerson(facade.getPersons()));
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        Scanner jsonScanner = new Scanner(request.getInputStream());
        String json = "";
        while (jsonScanner.hasNext()) {
            json += jsonScanner.nextLine();
        }

        Person p = JSONConverter.getPersonFromJson(json);

        p = facade.addPerson(p);

        response.getWriter().println(JSONConverter.getJSONFromPerson(p));
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        Scanner jsonScanner = new Scanner(req.getInputStream());
        String json = "";
        while (jsonScanner.hasNext()) {
            json += jsonScanner.nextLine();
        }

        Person p = JSONConverter.getPersonFromJson(json);

        p = facade.editPerson(p);

        resp.getWriter().println(JSONConverter.getJSONFromPerson(p));
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("application/json");

        String[] parts = req.getRequestURI().split("/");
        String parameter = null;
        if (parts.length == 4) {
            parameter = parts[3];
        }
        int id = Integer.parseInt(parameter);
        
        
        Person p = facade.deletePerson(id);
        
        resp.getWriter().println(JSONConverter.getJSONFromPerson(p));
    }

    
    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
