package titulacion.sise.canchala10.Utils;

import android.app.Application;

import java.util.Date;

/**
 * Created by yzeballos on 19/02/2018.
 */

public class Global extends Application{

    private String fechaReserva;

    public String getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(String fechaReserva) {
        this.fechaReserva = fechaReserva;
    }
}
