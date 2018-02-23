package titulacion.sise.canchala10.Remote;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import titulacion.sise.canchala10.Remote.Data.CampoResponse;
import titulacion.sise.canchala10.Remote.Data.HorarioResponse;
import titulacion.sise.canchala10.Remote.Data.PostResponse;
import titulacion.sise.canchala10.Remote.Data.ReservaDetalleRequest;
import titulacion.sise.canchala10.Remote.Data.ReservaDetalleResponse;
import titulacion.sise.canchala10.Remote.Data.ReservaResponse;
import titulacion.sise.canchala10.Remote.Data.SedeResponse;
import titulacion.sise.canchala10.Remote.Data.TarifaResponse;
import titulacion.sise.canchala10.entidades.Reserva;
import titulacion.sise.canchala10.entidades.ReservaDetalle;

/**
 * Created by yovanny on 14/02/2018.
 */

public interface SOService {

    @GET("sede")
    Call<SedeResponse> getSedes();

    @GET("campo")
    Call<CampoResponse> getCampos();

    @GET("sede/{idSede}/campos")
    Call<CampoResponse> getCamposBySede(@Path("idSede") String idSede);

    @GET("horario")
    Call<HorarioResponse> getHorarios();

    @GET("campo/{idCampo}/{fecha}/horarios")
    Call<HorarioResponse> getHorariosByCampoFecha(@Path("idCampo") String idCampo, @Path("fecha") String fecha);

    @GET("campo/{idCampo}/tarifas")
    Call<TarifaResponse> getTarifasByCampo(@Path("idCampo") String idCampo);

    @GET("usuario/{correo}/reservas")
    Call<ReservaResponse> getReservasByUsuario(@Path("correo") String correo);

    @POST("reserva")
    Call<PostResponse> addReserva(@Body Reserva reserva);

    @POST("reserva_detalle")
    Call<PostResponse> addReservaDetalle(@Body ReservaDetalle reservaDetalle);

    @POST("reserva/reserva_detalle/campo")
    Call<ReservaDetalleResponse> getHorasByCampoFecha(@Body ReservaDetalleRequest request);
}
