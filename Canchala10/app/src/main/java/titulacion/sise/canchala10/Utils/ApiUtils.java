package titulacion.sise.canchala10.Utils;

import titulacion.sise.canchala10.Remote.RetrofitClient;
import titulacion.sise.canchala10.Remote.SOService;

/**
 * Created by yovanny on 14/02/2018.
 */

public class ApiUtils {

    public static String baseUrl = "http://canchala10.com/canchala10/api/";

    public static String imgUrl = "http://canchala10.com/uploads/";
    public static SOService getSOService(){
        return RetrofitClient.getClient(baseUrl)
                .create(SOService.class);
    }
}
