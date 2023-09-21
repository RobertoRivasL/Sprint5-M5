package dao;

import conexion.Conexion;
import idao.IProfesionalDAO;
import models.Profesional;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfesionalDAOimpl implements IProfesionalDAO {
    @Override
    public boolean registrar(Profesional profesional) {
        boolean registrar = false;
        Statement stm = null;
        Connection con = null;

        String sql = "INSERT INTO profesionales (username,password,nombre,fechaNacimiento,tipo,titulo,fechaIngreso) values('" +
                profesional.getUsername() + "','" + profesional.getPassword() + "','" + profesional.getNombre() + "','" + profesional.getFechaNacimiento() + "'" +
                ",'" + profesional.getTipo() + "','" + profesional.getTitulo() + "','" + profesional.getFechaIngreso() + "')";

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: clase ProfesionalDAOimpl en el metodo registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public boolean eliminar(Profesional profesional) {
        boolean eliminar = false;
        Statement stm = null;
        Connection con = null;

        String sql = "DELETE from profesionales where id = '" + profesional.getId() + "'";

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            stm.execute(sql);
            eliminar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: clase ProfesionalDAOimpl en el metodo eliminar");
            e.printStackTrace();
        }
        return eliminar;
    }

    @Override
    public boolean modificar(Profesional profesional) {
        boolean modificar = false;
        Connection con = null;
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE profesionales SET " +
                "username = ?, " +
                "password = ?, " +
                "nombre = ?, " +
                "fechaNacimiento = ?, " +
                "tipo = ?, " +
                "titulo = ?, " +
                "fechaIngreso = ? " +
                "WHERE id = ?";

        try {
            con = Conexion.conectar();
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, profesional.getUsername());
            preparedStatement.setString(2, profesional.getPassword());
            preparedStatement.setString(3, profesional.getNombre());
            preparedStatement.setString(4, profesional.getFechaNacimiento());
            preparedStatement.setString(5, profesional.getTipo());
            preparedStatement.setString(6, profesional.getTitulo());
            preparedStatement.setString(7, profesional.getFechaIngreso());
            preparedStatement.setInt(8, profesional.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                modificar = true;
                System.out.println("Actualizacion exitosa");
            } else {
                System.out.println("Ninguna fila se ha Actualizado.");

            }

        } catch (SQLException e) {
            System.out.println("Error: clase ProfesionalDAOimpl en el metodo modificar");
            e.printStackTrace();
        } finally {
            // Close resources in the finally block to ensure they are always closed.
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (con != null) {
                    con.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return modificar;
    }

    @Override
    public List<Profesional> listarProfesionales() {
        List<Profesional> listaProfesionales = new ArrayList<>();
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;

        String sql = "SELECT * from profesionales ORDER BY id";

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);


            while (rs.next()) {

                Profesional profesional = new Profesional();

                profesional.setId(rs.getInt(1));
                profesional.setUsername(rs.getString(2));
                profesional.setPassword(rs.getString(3));
                profesional.setNombre(rs.getString(4));
                profesional.setFechaNacimiento(rs.getString(5));
                profesional.setTipo(rs.getString(6));
                profesional.setTitulo(rs.getString(7));
                profesional.setFechaIngreso(rs.getString(8));
                profesional.setCreated_at(rs.getDate(9));

                listaProfesionales.add(profesional);

            }
            rs.close();
            stm.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: clase ProfesionalDAOimpl en el metodo listar");
            e.printStackTrace();
        }

        return listaProfesionales;
    }
}
