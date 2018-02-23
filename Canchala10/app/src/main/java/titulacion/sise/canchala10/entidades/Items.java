package titulacion.sise.canchala10.entidades;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by yzeballos on 23/02/2018.
 */

public class Items {

    @SerializedName("descripcion")
    @Expose
    private String descripcion;
    @SerializedName("sede_descripcion")
    @Expose
    private String sedeDescripcion;
    @SerializedName("direccion")
    @Expose
    private String direccion;
    @SerializedName("hora_inicio")
    @Expose
    private String horaInicio;
    @SerializedName("hora_fin")
    @Expose
    private String horaFin;
    @SerializedName("precio")
    @Expose
    private String precio;

    @SerializedName("url_imagen")
    @Expose
    private String urlImagen;

    public String getUrlImagen() {
        return urlImagen;
    }

    public void setUrlImagen(String urlImagen) {
        this.urlImagen = urlImagen;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getSedeDescripcion() {
        return sedeDescripcion;
    }

    public void setSedeDescripcion(String sedeDescripcion) {
        this.sedeDescripcion = sedeDescripcion;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String getPrecio() {
        return precio;
    }

    public void setPrecio(String precio) {
        this.precio = precio;
    }
}
