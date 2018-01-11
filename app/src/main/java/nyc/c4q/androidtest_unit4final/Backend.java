package nyc.c4q.androidtest_unit4final;

import retrofit2.Call;
import retrofit2.http.GET;

public interface Backend {

        @GET("operable/cog/master/priv/css-color-names.json.")
        Call<Color> getNames();

}
