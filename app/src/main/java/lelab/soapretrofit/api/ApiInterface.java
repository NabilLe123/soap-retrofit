package lelab.soapretrofit.api;

import lelab.soapretrofit.model.request.EvaluateRequestEnvelope;
import lelab.soapretrofit.model.request.SurveyRequestEnvelope;
import lelab.soapretrofit.model.response.SurveyResponseEnvelope;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers({"Content-Type: text/xml;charset=UTF-8"})
    @POST("Tasks.asmx?op=GetShopCheckList")
    Call<SurveyResponseEnvelope> getShopSurvey(@Body SurveyRequestEnvelope requestEnvelope);

    @Headers({"Content-Type: text/xml;charset=UTF-8"})
    @POST("Tasks.asmx?op=EvaluateShop")
    Call<ResponseBody> evaluateShop(@Body EvaluateRequestEnvelope requestEnvelope);
}
