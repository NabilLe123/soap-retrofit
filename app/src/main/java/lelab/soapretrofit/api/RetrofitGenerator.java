package lelab.soapretrofit.api;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.convert.AnnotationStrategy;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.strategy.Strategy;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.scalars.ScalarsConverterFactory;
import retrofit2.converter.simplexml.SimpleXmlConverterFactory;

public class RetrofitGenerator {
    public final static String BASE_URL = "http://stgapp.leader.com.sa/MomraInspectionService/";
    public static ApiInterface apiInterface;

    private static Strategy strategy = new AnnotationStrategy();
    private static Serializer serializer = new Persister(strategy);

    private static OkHttpClient.Builder okHttpClient = new OkHttpClient.Builder();

    private static Retrofit.Builder retrofitBuilder = new Retrofit.Builder()
            .addConverterFactory(SimpleXmlConverterFactory.create(serializer))
            .addConverterFactory(ScalarsConverterFactory.create())//To get result as a string
            .addConverterFactory(GsonConverterFactory.create());

    public static <S> S createService(Class<S> serviceClass, String baseUrl) {
        /*okHttpClient.interceptors().add(new Interceptor() {
            @Override
            public okhttp3.Response intercept(Chain chain) throws IOException {
                Request original = chain.request();

                Request.Builder requestBuilder = original.newBuilder()
                        .header("Content-Type", "text/xml;charset=UTF-8")   // 对于SOAP 1.1， 如果是soap1.2 应是Content-Type: application/soap+xml; charset=utf-8
                        .method(original.method(), original.body());

                Request request = requestBuilder.build();
                return chain.proceed(request);
            }
        });*/

        OkHttpClient client = okHttpClient.connectTimeout(2, TimeUnit.MINUTES)
                .writeTimeout(2, TimeUnit.MINUTES)
                .readTimeout(2, TimeUnit.MINUTES)
                .build();
        Retrofit retrofit = retrofitBuilder.baseUrl(baseUrl).client(client).build();
        return retrofit.create(serviceClass);
    }

    public static ApiInterface getApiInterface() {
        if (apiInterface == null) {
            apiInterface = createService(ApiInterface.class, BASE_URL);
        }
        return apiInterface;

    }
}
