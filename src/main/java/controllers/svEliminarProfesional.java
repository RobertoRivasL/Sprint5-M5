package controllers;

import dao.ProfesionalDAOimpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Profesional;

import java.io.IOException;

import static java.lang.System.out;

public class svEliminarProfesional extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        boolean eliminado = false;

        int identificadorProfesional = Integer.parseInt(request.getParameter("identificadorProfesional"));

        ProfesionalDAOimpl profesionalDAO = new ProfesionalDAOimpl();
        Profesional profesional = new Profesional();
        profesional.setId(identificadorProfesional);
        eliminado = profesionalDAO.eliminar(profesional);


        if (eliminado) {
            response.sendRedirect("listaUsuarios.jsp");
        } else {
            out.print("No se pudo eliminar");
        }

    }


}
