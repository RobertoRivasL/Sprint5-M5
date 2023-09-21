package dao;

import conexion.Conexion;
import idao.IAdministrativoDAO;
import models.Administrativo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AdministrativoDAOimpl implements IAdministrativoDAO {
    @Override
    public boolean registrar(Administrativo administrativo) {
        boolean registrar = false;
        Statement stm = null;
        Connection con = null;

        String sql = "INSERT INTO administrativos (username,password,nombre,fechaNacimiento,tipo,area,expPrevia) values('" +
                administrativo.getUsername() + "','" + administrativo.getPassword() + "','" + administrativo.getNombre() + "','" + administrativo.getFechaNacimiento() + "'" +
                ",'" + administrativo.getTipo() + "','" + administrativo.getArea() + "','" + administrativo.getExpPrevia() + "')";

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            stm.execute(sql);
            registrar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: clase AdministrativoDAOimpl en el metodo registrar");
            e.printStackTrace();
        }
        return registrar;
    }

    @Override
    public boolean eliminar(Administrativo administrativo) {
        boolean eliminar = false;
        Statement stm = null;
        Connection con = null;

        String sql = "DELETE from administrativos where id = '" + administrativo.getId() + "'";

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            stm.execute(sql);
            eliminar = true;
            stm.close();
            con.close();
        } catch (SQLException e) {
            System.out.println("Error: clase AdministrativoDAOimpl en el metodo eliminar");
            e.printStackTrace();
        }
        return eliminar;
    }

    @Override
    public boolean modificar(Administrativo administrativo) {
        boolean modificar = false;
        Connection con = null;
        PreparedStatement preparedStatement = null;

        String sql = "UPDATE administrativos SET " +
                "username = ?, " +
                "password = ?, " +
                "nombre = ?, " +
                "fechaNacimiento = ?, " +
                "tipo = ?, " +
                "area = ?, " +
                "expPrevia = ? " +
                "WHERE id = ?";

        try {
            con = Conexion.conectar();
            preparedStatement = con.prepareStatement(sql);

            preparedStatement.setString(1, administrativo.getUsername());
            preparedStatement.setString(2, administrativo.getPassword());
            preparedStatement.setString(3, administrativo.getNombre());
            preparedStatement.setString(4, administrativo.getFechaNacimiento());
            preparedStatement.setString(5, administrativo.getTipo());
            preparedStatement.setString(6, administrativo.getArea());
            preparedStatement.setString(7, administrativo.getExpPrevia());
            preparedStatement.setInt(8, administrativo.getId());

            int rowsAffected = preparedStatement.executeUpdate();

            if (rowsAffected > 0) {
                modificar = true;
                System.out.println("Actualizacion exitosa");
            } else {
                System.out.println("Ninguna fila se ha Actualizado.");

            }

        } catch (SQLException e) {
            System.out.println("Error: clase AdministrativoDAOImpl en el metodo modificar");
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
    public List<Administrativo> listarAdministrativos() {
        List<Administrativo> listaAdministrativos = new ArrayList<>();
        Statement stm = null;
        Connection con = null;
        ResultSet rs = null;

        String sql = "SELECT * from administrativos ORDER BY id";

        try {
            con = Conexion.conectar();
            stm = con.createStatement();
            rs = stm.executeQuery(sql);


            while (rs.next()) {

                Administrativo administrativo = new Administrativo();

                administrativo.setId(rs.getInt(1));
                administrativo.setUsername(rs.getString(2));
                administrativo.setPassword(rs.getString(3));
                administrativo.setNombre(rs.getString(4));
                administrativo.setFechaNacimiento(rs.getString(5));
                administrativo.setTipo(rs.getString(6));
                administrativo.setArea(rs.getString(7));
                administrativo.setExpPrevia(rs.getString(8));
                administrativo.setCreated_at(rs.getDate(9));

                listaAdministrativos.add(administrativo);

            }
            rs.close();
            stm.close();
            con.close();

        } catch (SQLException e) {
            System.out.println("Error: clase AdministrativoDAOimpl en el metodo listar");
            e.printStackTrace();
        }

        return listaAdministrativos;
    }
}
