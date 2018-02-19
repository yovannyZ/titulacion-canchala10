package titulacion.sise.canchala10.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

/**
 * Created by yovanny on 18/02/2018.
 */

public class Reserva implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;

    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("fecha")
    @Expose
    private String fecha;

    @SerializedName("items")
    @Expose
    private List<ReservaDetalle> items;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public List<ReservaDetalle> getItems() {
        return items;
    }

    public void setItems(List<ReservaDetalle> items) {
        this.items = items;
    }
}
