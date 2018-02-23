package titulacion.sise.canchala10.Remote.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yzeballos on 22/02/2018.
 */

public class ReservaDetalleRequest {
    @SerializedName("id_campo")
    @Expose
    private String idCampo;

    @SerializedName("fecha")
    @Expose
    private String fecha;


    public String getIdCampo() {
        return idCampo;
    }

    public void setIdCampo(String idCampo) {
        this.idCampo = idCampo;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ReservaDetalleRequest(String idCampo, String fecha) {
        this.idCampo = idCampo;
        this.fecha = fecha;
    }
}
