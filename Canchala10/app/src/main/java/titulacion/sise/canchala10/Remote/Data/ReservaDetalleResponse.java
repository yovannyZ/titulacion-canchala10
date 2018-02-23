package titulacion.sise.canchala10.Remote.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import titulacion.sise.canchala10.entidades.ReservaDetalle;

/**
 * Created by yzeballos on 22/02/2018.
 */

public class ReservaDetalleResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("response")
    @Expose
    private List<ReservaDetalle> detalles = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<ReservaDetalle> geDetalles() {
        return detalles;
    }

    public void setDetalles(List<ReservaDetalle> detalles) {
        this.detalles = detalles;
    }

}
