package idao;

import models.Administrativo;

import java.util.List;

public interface IAdministrativoDAO {


    boolean registrar(Administrativo administrativo);

    boolean eliminar(Administrativo administrativo);

    boolean modificar(Administrativo administrativo);

    List<Administrativo> listarAdministrativos();
}
