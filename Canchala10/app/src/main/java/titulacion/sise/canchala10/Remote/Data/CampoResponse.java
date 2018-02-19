package titulacion.sise.canchala10.Remote.Data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

import titulacion.sise.canchala10.entidades.Campo;

/**
 * Created by yzeballos on 15/02/2018.
 */

public class CampoResponse {
    @SerializedName("status")
    @Expose
    private Boolean status;
    @SerializedName("response")
    @Expose
    private List<Campo> campos;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public List<Campo> getResponse() {
        return campos;
    }

    public void setResponse(List<Campo> campos) {
        this.campos = campos;
    }
}

