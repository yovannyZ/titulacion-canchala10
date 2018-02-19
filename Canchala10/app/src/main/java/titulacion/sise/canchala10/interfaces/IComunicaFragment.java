package titulacion.sise.canchala10.interfaces;

import java.util.List;

import titulacion.sise.canchala10.entidades.Campo;
import titulacion.sise.canchala10.entidades.Horario;
import titulacion.sise.canchala10.entidades.Sede;

/**
 * Created by yzeballos on 16/02/2018.
 */

public interface IComunicaFragment {

    public void enviarSede(Sede sede);
    public void enviarResumen(Campo campo, List<Horario> horarios);


}
