package controllers;

import dao.ClienteDAOimpl;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import models.Cliente;

import java.io.IOException;

import static java.lang.System.out;

public class svEliminarCliente extends HttpServlet {


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub

        boolean eliminado = false;

        int identificadorCliente = Integer.parseInt(request.getParameter("identificadorCliente"));

        ClienteDAOimpl clienteDAO = new ClienteDAOimpl();
        Cliente cliente = new Cliente();
        cliente.setId(identificadorCliente);
        eliminado = clienteDAO.eliminar(cliente);

        if (eliminado) {
            response.sendRedirect("listaUsuarios.jsp");
        } else {
            out.print("No se pudo eliminar");
        }

    }


}
