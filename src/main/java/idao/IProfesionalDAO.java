package idao;

import models.Profesional;

import java.util.List;

public interface IProfesionalDAO {


    boolean registrar(Profesional profesional);

    boolean eliminar(Profesional profesional);

    boolean modificar(Profesional Profesional);

    List<Profesional> listarProfesionales();


}
