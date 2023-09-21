package controllers;

import dao.AdministrativoDAOimpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Administrativo;

import java.io.IOException;

import static java.lang.System.out;

public class svEliminarAdministrativo extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        boolean eliminado = false;

        int identificadorAdministrativo = Integer.parseInt(request.getParameter("identificadorAdministrativo"));

        AdministrativoDAOimpl administrativoDAO = new AdministrativoDAOimpl();
        Administrativo administrativo = new Administrativo();
        administrativo.setId(identificadorAdministrativo);
        eliminado = administrativoDAO.eliminar(administrativo);

        if (eliminado) {
            response.sendRedirect("listaUsuarios.jsp");
        } else {
            out.print("No se pudo eliminar");
        }

    }


}
