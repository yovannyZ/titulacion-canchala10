package titulacion.sise.canchala10.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yzeballos on 27/02/2018.
 */

public class Usuario {
    @SerializedName("correo")
    @Expose
    private String correo;
    @SerializedName("clave")
    @Expose
    private String clave;
    @SerializedName("nombres")
    @Expose
    private String nombres;
    @SerializedName("id_tipo_usuario")
    @Expose
    private String idTipoUsuario;

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getIdTipoUsuario() {
        return idTipoUsuario;
    }

    public void setIdTipoUsuario(String idTipoUsuario) {
        this.idTipoUsuario = idTipoUsuario;
    }
}
