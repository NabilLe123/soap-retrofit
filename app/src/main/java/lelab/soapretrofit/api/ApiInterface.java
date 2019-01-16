package lelab.soapretrofit.api;

import lelab.soapretrofit.model.request.SurveyRequestEnvelope;
import lelab.soapretrofit.model.response.SurveyResponseEnvelope;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface ApiInterface {
    @Headers({"Content-Type: text/xml;charset=UTF-8"})
    @POST("Tasks.asmx?op=GetShopCheckList")
    Call<SurveyResponseEnvelope> getShopSurvey(@Body SurveyRequestEnvelope requestEnvelope);
}
