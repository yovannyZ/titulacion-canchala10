package titulacion.sise.canchala10.Remote.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import titulacion.sise.canchala10.entidades.Sede;

/**
 * Created by yovanny on 14/02/2018.
 */

public class SedeResponse {

    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("response")
    @Expose
    private List<Sede> sedes = null;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Sede> getResponse() {
        return sedes;
    }

    public void setResponse(List<Sede> sedes) {
        this.sedes = sedes;
    }
}
