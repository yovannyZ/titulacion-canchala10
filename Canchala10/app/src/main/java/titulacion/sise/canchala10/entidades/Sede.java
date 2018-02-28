package titulacion.sise.canchala10.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by yovanny on 14/02/2018.
 */

public class Sede implements Serializable {


    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("implementos")
    @Expose
    private String implementos;
    @SerializedName("vestidores")
    @Expose
    private String vestidores;
    @SerializedName("snack")
    @Expose
    private String snack;
    @SerializedName("estacionamiento")
    @Expose
    private String estacionamiento;
    @SerializedName("url_imagen")
    @Expose
    private String urlImagen;
    @SerializedName("estado")
    @Expose
    private String estado;
    @SerializedName("longitud")
    @Expose
    private double longitud;
    @SerializedName("latitud")
    @Expose
    private double latitud;
    @SerializedName("telefono")
    @Expose
    private String telefono;
    @SerializedName("hora_atencion")
    @Expose
    private String hora_atencion;

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

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImplementos() {
        return implementos;
    }

    public void setImplementos(String implementos) {
        this.implementos = implementos;
    }

    public String getVestidores() {
        return vestidores;
    }

    public void setVestidores(String vestidores) {
        this.vestidores = vestidores;
    }

    public String getSnack() {
        return snack;
    }

    public void setSnack(String snack) {
        this.snack = snack;
    }

    public String getEstacionamiento() {
        return estacionamiento;
    }

    public void setEstacionamiento(String estacionamiento) {
        this.estacionamiento = estacionamiento;
    }

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public double getLongitud() {
        return longitud;
    }

    public void setLongitud(double longitud) {
        this.longitud = longitud;
    }

    public double getLatitud() {
        return latitud;
    }

    public void setLatitud(double latitud) {
        this.latitud = latitud;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getHora_atencion() {
        return hora_atencion;
    }

    public void setHora_atencion(String hora_atencion) {
        this.hora_atencion = hora_atencion;
    }
}
