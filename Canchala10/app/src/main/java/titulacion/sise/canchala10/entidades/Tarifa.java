package titulacion.sise.canchala10.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yovanny on 18/02/2018.
 */

public class Tarifa implements Serializable {

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("id_horario")
    @Expose
    private String idHorario;
    @SerializedName("id_campo")
    @Expose
    private String idCampo;
    @SerializedName("precio")
    @Expose
    private String precio;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(String idHorario) {
        this.idHorario = idHorario;
    }

    public String getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(String idCampo) {
        this.idCampo = idCampo;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
