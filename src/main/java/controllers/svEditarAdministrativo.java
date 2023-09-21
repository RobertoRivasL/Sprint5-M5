package controllers;

import dao.AdministrativoDAOimpl;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Administrativo;

import java.io.IOException;

public class svEditarAdministrativo extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        String nuevoID = request.getParameter("nuevoID");
        int nuevoIDx = Integer.parseInt(nuevoID);
        String nuevoUsername = request.getParameter("nuevoUsuarioMOD");
        String nuevoPassword = request.getParameter("nuevoPasswordMOD");
        String nuevoNombre = request.getParameter("nombreMOD");
        String nuevaFecha = request.getParameter("fechaNacimientoMOD");
        String nuevoTipo = request.getParameter("TipoMOD");
        String nuevaArea = request.getParameter("areaMOD");
        String nuevaExpPrevia = request.getParameter("expPreviaMOD");


        Administrativo administrativo = new Administrativo(nuevoIDx, nuevoUsername, nuevoPassword, nuevoNombre, nuevaFecha, nuevoTipo,
                nuevaArea, nuevaExpPrevia);

        AdministrativoDAOimpl administrativoDAO = new AdministrativoDAOimpl();

        administrativoDAO.modificar(administrativo);

        RequestDispatcher rd = request.getRequestDispatcher("/listaUsuarios.jsp");
        rd.forward(request, response);

    }
}
