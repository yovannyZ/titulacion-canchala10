package titulacion.sise.canchala10.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yzeballos on 15/02/2018.
 */

public class Campo implements Serializable{
    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("cantidad_jugadores")
    @Expose
    private String cantidadJugadores;
    @SerializedName("imagen")
    @Expose
    private String imagen;
    @SerializedName("id_sede")
    @Expose
    private String idSede;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("sede")
    @Expose
    private Sede sede;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getCantidadJugadores() {
        return cantidadJugadores;
    }

    public void setCantidadJugadores(String cantidadJugadores) { this.cantidadJugadores = cantidadJugadores; }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    public String getIdSede() {
        return idSede;
    }

    public void setIdSede(String idSede) {
        this.idSede = idSede;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Sede getSede() {
        return sede;
    }

    public void setSede(Sede sede) {
        this.sede = sede;
    }
}
