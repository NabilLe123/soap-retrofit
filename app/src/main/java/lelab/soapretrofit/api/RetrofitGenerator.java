package lelab.soapretrofit.api;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitGenerator {
    private static final String BASE_URL = "http://stgapp.leader.com.sa/MomraInspectionService/";
    private static Retrofit retrofit = null;
    private static ApiInterface apiInterface = null;

    public static ApiInterface getApiInterface() {
        if (apiInterface == null) {
            apiInterface = getClient().create(ApiInterface.class);
        }
        return apiInterface;
    }

    private static Retrofit getClient() {
        if (retrofit == null) {
            Strategy strategy = new AnnotationStrategy();
            Serializer serializer = new Persister(strategy);

            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(SimpleXmlConverterFactory.create(serializer))//To get result as XML
                    .addCallAdapterFactory(RxJava2CallAdapterFactory.create())//necessary for rx java retrofit api calls, else error pops up
                    .addConverterFactory(ScalarsConverterFactory.create())//To get result as a string
                    .addConverterFactory(GsonConverterFactory.create())//To get result as such in a model class (POJO)
                    .build();
        }
        return retrofit;
    }
}
