package titulacion.sise.canchala10.Remote;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import titulacion.sise.canchala10.Remote.Data.CampoResponse;
import titulacion.sise.canchala10.Remote.Data.HorarioResponse;
import titulacion.sise.canchala10.Remote.Data.ReservaPostResponse;
import titulacion.sise.canchala10.Remote.Data.ReservaResponse;
import titulacion.sise.canchala10.Remote.Data.SedeResponse;
import titulacion.sise.canchala10.Remote.Data.TarifaResponse;
import titulacion.sise.canchala10.entidades.Reserva;

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

    @GET("campo/{idCampo}/tarifas")
    Call<TarifaResponse> getTarifasByCampo(@Path("idCampo") String idCampo);

    @POST("reserva")
    Call<ReservaPostResponse> addReserva(@Body Reserva reserva);
}
