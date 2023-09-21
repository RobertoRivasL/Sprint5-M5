package dao;

import conexion.Conexion;
import idao.ICapacitacionDAO;
import models.Capacitacion;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CapacitacionDAOimpl implements ICapacitacionDAO {
    @Override
    public boolean registrar(Capacitacion capacitacion) {
        boolean registrar = false;
        Statement stm = null;
        Connection con = null;

        String sql = "INSERT INTO capacitacion (rutCliente,dia,hora,lugar,duracion,cantidadAsistentes) values('" +
                capacitacion.getRutCliente() + "','" + capacitacion.getDia() + "','" + capacitacion.getHora() + "','" + capacitacion.getLugar()
                + "','" + capacitacion.getDuracion() + "','" + capacitacion.getCantidadAsistentes() + "')";
        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: clase CapacitacionDAOimpl en el metodo registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public boolean eliminar(Capacitacion capacitacion) {
        boolean eliminar = false;
        Statement stm = null;
        Connection con = null;

        String sql = "DELETE from capacitacion where id = '" + capacitacion.getIdentificador() + "'";

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            stm.execute(sql);
            eliminar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: clase CapacitacionDAOimpl en el metodo eliminar");
            e.printStackTrace();
        }
        return eliminar;
    }

    @Override
    public boolean modificar(Capacitacion capacitacion) {
        boolean modificar = false;
        Statement stm = null;
        Connection con = null;

        //String sql = "UPDATE users SET username='"+usuario.getUsername()+"' WHERE id ='"+usuario.getId()+"'";

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            //stm.execute(sql);
            modificar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: clase CapacitacionDAOimpl en el metodo modificar");
            e.printStackTrace();
        }
        return modificar;
    }

    @Override
    public List<Capacitacion> listarCapacitacion() {
        List<Capacitacion> listaCapacitaciones = new ArrayList<>();
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;

        String sql = "SELECT * from capacitacion ORDER BY id";

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);


            while (rs.next()) {

                Capacitacion capacitacion = new Capacitacion();
                capacitacion.setIdentificador(rs.getString(1));
                capacitacion.setRutCliente(rs.getString(2));
                capacitacion.setDia(rs.getString(3));
                capacitacion.setHora(rs.getString(4));
                capacitacion.setLugar(rs.getString(5));
                capacitacion.setDuracion(rs.getString(6));
                capacitacion.setCantidadAsistentes(rs.getString(7));
                capacitacion.setCreated_at(rs.getDate(8));

                listaCapacitaciones.add(capacitacion);

            }
            rs.close();
            stm.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: clase CapacitacionDAOimpl en el metodo listar");
            e.printStackTrace();
        }

        return listaCapacitaciones;
    }

}
