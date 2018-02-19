package titulacion.sise.canchala10.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yovanny on 18/02/2018.
 */

public class ReservaDetalle implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_reserva")
    @Expose
    private String id_reserva;
    @SerializedName("id_tarifa")
    @Expose
    private String id_tarifa;
    @SerializedName("precio")
    @Expose
    private String precio;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_reserva() {
        return id_reserva;
    }

    public void setId_reserva(String id_reserva) {
        this.id_reserva = id_reserva;
    }

    public String getId_tarifa() {
        return id_tarifa;
    }

    public void setId_tarifa(String id_tarifa) {
        this.id_tarifa = id_tarifa;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
